package com.scene;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 基于Bitmap实现id去重
 *
 * @author Created by lx_068
 * @date 2022/01/16
 */
public class BitMap {

    public static void main(String[] args) {
        try (JedisPool jedisPool = new JedisPool(); Jedis jedis = jedisPool.getResource()) {
            String key = "deduplication";
            for (int i = 0; i < 1000; i++) {
                jedis.setbit(key, i, true);
            }
            assert jedis.getbit(key, 100);
            assert !jedis.getbit(key, 10000);
        }
    }
}
