package com.scene;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Redis 全局ID生成
 *
 * @author Created by lx_068
 * @date 2022/01/16
 */
public class GenerateId {

    private enum EnumSingleton {
        /**
         * 懒汉枚举单例
         */
        INSTANCE;
        private GenerateId instance;

        EnumSingleton() {
            instance = new GenerateId();
        }

        public GenerateId getSingleton() {
            return instance;
        }
    }

    public static GenerateId getInstance() {
        return EnumSingleton.INSTANCE.getSingleton();
    }

    private JedisPool jedisPool = new JedisPool();

    private String generateOrderId() {
        LocalDateTime now = LocalDateTime.now();
        String orderIdPrefix = getOrderIdPrefix(now);
        return orderIdPrefix + String.format("%1$06d", generate(orderIdPrefix, 5));
    }

    private String getOrderIdPrefix(LocalDateTime now) {
        return now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    private long generate(String key, int timeout) {
        try (Jedis jedis = jedisPool.getResource()) {
            Long id = jedis.incr(key);
            if (timeout > 0) {
                jedis.expire(key, timeout);
            }
            return id;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(GenerateId.getInstance().generateOrderId());
        }
    }
}