package com.aimer;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author :覃玉锦
 * @create :2023-12-11 21:24:00
 * cluster集群架构
 */
public class JedisClusterTest {
    public static void main(String[] args) throws IOException {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(10);
        config.setMinIdle(5);

        Set<HostAndPort> jedisClusterNodes = new HashSet<>();
        jedisClusterNodes.add(new HostAndPort("192.168.48.144", 8001));
        jedisClusterNodes.add(new HostAndPort("192.168.48.144", 8004));
        jedisClusterNodes.add(new HostAndPort("192.168.48.145", 8002));
        jedisClusterNodes.add(new HostAndPort("192.168.48.145", 8005));
        jedisClusterNodes.add(new HostAndPort("192.168.48.146", 8003));
        jedisClusterNodes.add(new HostAndPort("192.168.48.146", 8006));

        JedisCluster jedisCluster = null;
        try {
            //connectionTimeout：指的是连接一个url的连接等待时间
            //soTimeout：指的是连接上一个url，获取response的返回等待时间
            jedisCluster = new JedisCluster(jedisClusterNodes
                    , 6000
                    , 5000
                    , 10
                    , "yujin", config);
            System.out.println(jedisCluster.set("cluster", "yujin"));
            System.out.println(jedisCluster.get("cluster"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedisCluster != null) {
                jedisCluster.close();
            }
        }
    }
}
