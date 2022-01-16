package com.scene;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Random;

/**
 * 基于HLL实现点击量计数
 *
 * @author Created lx_068
 * @date 2022/01/16
 */
public class HyperLogLog {

    public static void main(String[] args) {
        try (JedisPool jedisPool = new JedisPool(); Jedis jedis = jedisPool.getResource()) {
            String key = "HyperLogLog";
            jedis.del(key);

            for (int i = 0; i < 10000; i++) {
                long userId = new Random(System.currentTimeMillis()).nextInt(100000000);
                jedis.pfadd(key, String.valueOf(userId));
            }

            System.out.println("click amount: " + jedis.pfcount(key));
        }
    }
}
