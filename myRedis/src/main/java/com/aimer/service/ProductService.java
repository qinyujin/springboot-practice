package com.aimer.service;

import com.aimer.common.RedisKeyPrefixConst;
import com.aimer.common.RedisUtil;
import com.aimer.dao.ProductDao;
import com.aimer.model.Product;
import com.alibaba.fastjson.JSON;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author :覃玉锦
 * @create :2023-12-20 20:11:00
 */
@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private Redisson redisson;

    public static final Integer PRODUCT_CACHE_TIMEOUT = 60 * 60 * 24;
    public static final String EMPTY_CACHE = "{}";
    public static final String LOCK_PRODUCT_HOT_CACHE_PREFIX = "lock:product:hot_cache:";
    public static final String LOCK_PRODUCT_UPDATE_PREFIX = "lock:product:update:";
    public static Map<String, Product> productMap = new ConcurrentHashMap<>();

    @Transactional
    public Product create(Product product) {
        Product productResult = productDao.create(product);
        redisUtil.set(RedisKeyPrefixConst.PRODUCT_CACHE + productResult.getId(), JSON.toJSONString(productResult),
                genProductCacheTimeout(), TimeUnit.SECONDS); // 操作缓存时尽量加上超时时间
        return productResult;
    }

    @Transactional
    public Product update(Product product) {
        Product productResult = null;
        //RLock updateProductLock = redisson.getLock(LOCK_PRODUCT_UPDATE_PREFIX + product.getId()); // 分布式锁
        RReadWriteLock readWriteLock = redisson.getReadWriteLock(LOCK_PRODUCT_UPDATE_PREFIX + product.getId()); // 数据库缓存双写不一致问题
        RLock writeLock = readWriteLock.writeLock();
        writeLock.lock();
        try {
            productResult = productDao.update(product);
            redisUtil.set(RedisKeyPrefixConst.PRODUCT_CACHE + productResult.getId(), JSON.toJSONString(productResult),
                    genProductCacheTimeout(), TimeUnit.SECONDS);
            productMap.put(RedisKeyPrefixConst.PRODUCT_CACHE + productResult.getId(), product);
        } finally {
            writeLock.unlock();
        }
        return productResult;
    }

    public Product get(Long productId) {
        Product product = null;
        String productCacheKey = RedisKeyPrefixConst.PRODUCT_CACHE + productId;

        product = getProductFromCache(productCacheKey);
        if (product != null) {
            return product;
        }
        //DCL
        RLock hotCacheLock = redisson.getLock(LOCK_PRODUCT_HOT_CACHE_PREFIX + productId); // 缓存穿透热key，分布式锁放行一个线程，设置好缓存的key后，让其他线程请求缓存
        hotCacheLock.lock();
        //boolean result = hotCacheLock.tryLock(3, TimeUnit.SECONDS);  // 这个方法，3秒钟之后尝试获取锁，并不阻塞，如果能够保证3s内key能够缓存成功的话，可以用这个，就不会拿锁了
        try {
            product = getProductFromCache(productCacheKey);
            if (product != null) {
                return product;
            }

            //RLock updateProductLock = redisson.getLock(LOCK_PRODUCT_UPDATE_PREFIX + productId);
            RReadWriteLock readWriteLock = redisson.getReadWriteLock(LOCK_PRODUCT_UPDATE_PREFIX + productId); // 解决db和缓存双写不一致问题，锁粒度是db和redis圈一起
            RLock rLock = readWriteLock.readLock();
            rLock.lock();
            try {
                product = productDao.get(productId);
                if (product != null) {
                    redisUtil.set(productCacheKey, JSON.toJSONString(product),
                            genProductCacheTimeout(), TimeUnit.SECONDS);
                    productMap.put(productCacheKey, product);
                } else {
                    redisUtil.set(productCacheKey, EMPTY_CACHE, genEmptyCacheTimeout(), TimeUnit.SECONDS); // 缓存穿透问题，存一个空值
                }
            } finally {
                rLock.unlock();
            }
        } finally {
            hotCacheLock.unlock();
        }
        return product;
    }


    private Integer genProductCacheTimeout() {
        return PRODUCT_CACHE_TIMEOUT + new Random().nextInt(5) * 60 * 60;
    }

    private Integer genEmptyCacheTimeout() {
        return 60 + new Random().nextInt(30); // 随机过期时间，避免缓存雪崩
    }

    private Product getProductFromCache(String productCacheKey) {
        Product product = productMap.get(productCacheKey);
        if (product != null) {
            return product;
        }

        String productStr = redisUtil.get(productCacheKey);
        if (!StringUtils.isEmpty(productStr)) {
            if (EMPTY_CACHE.equals(productStr)) { // 缓存穿透
                redisUtil.expire(productCacheKey, genEmptyCacheTimeout(), TimeUnit.SECONDS);
                return new Product();
            }
            product = JSON.parseObject(productStr, Product.class);
            redisUtil.expire(productCacheKey, genProductCacheTimeout(), TimeUnit.SECONDS); //读延期
        }
        return product;
    }

}
