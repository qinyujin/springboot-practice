package com.aimer;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * @author :覃玉锦
 * @create :2023-12-04 21:37:00
 * <p>
 * 主从+哨兵模式,连接哨兵机器.如果出现连接不上的情况，有可能是linux防火墙端口没开放
 */
public class JedisSentinelTest {
    public static void main(String[] args) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(10);
        config.setMinIdle(5);

        String masterName = "mymaster";
        Set<String> sentinels = new HashSet<>();
        sentinels.add(new HostAndPort("192.168.48.144", 26379).toString());
        sentinels.add(new HostAndPort("192.168.48.144", 26380).toString());
        sentinels.add(new HostAndPort("192.168.48.144", 26381).toString());

        //JedisSentinelPool其实本质跟JedisPool类似，都是与redis主节点建立的连接池
        //JedisSentinelPool并不是说与sentinel建立的连接池，而是通过sentinel发现redis主节点并与其建立连接
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(masterName, sentinels, config
                , 3000, null);

        Jedis jedis = null;

        try {
            jedis = jedisSentinelPool.getResource();
            System.out.println(jedis.set("sentinel", "yujin"));
            System.out.println(jedis.get("sentinel"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

    }
}
