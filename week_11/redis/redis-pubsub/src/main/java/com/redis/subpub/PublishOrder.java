package com.redis.subpub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import java.util.Random;

/**
 * @author Created by lx_068
 * @date 2022/01/16
 */
public class PublishOrder {

    public PublishOrder(JedisPool jedisPool, String channelName) {
        System.out.println("Start publisher order");
        try (Jedis jedis = jedisPool.getResource()) {
            int sleepTime = 0;
            for (int i = 0; i < 10; i++) {
                sleepTime = new Random().nextInt(10) + 1;

                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                jedis.publish(channelName, "order sleep " + sleepTime);
            }
            jedis.publish(channelName, "");
        }
    }
}
