package com.redis.subpub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

/**
 * @author Created by lx_068
 * @date 2022/01/16
 */
public class SubscribeOrder {

    /**
     * 订单消费
     *
     * @param jedisPool 缓存
     * @param channelName 渠道名称
     */
    public SubscribeOrder(final JedisPool jedisPool, final String channelName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("start subscriber order");
                try (Jedis jedis = jedisPool.getResource()) {
                    jedis.subscribe(setupSubscriber(), channelName);
                }
            }
        }, "subscriberThread").start();
    }

    /**
     * 消费步骤
     *
     * @return redis.clients.jedis.JedisPubSub
     */
    private JedisPubSub setupSubscriber() {
        return new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                if (message.isEmpty()) {
                    System.out.println("SubPub End");
                    System.exit(0);
                }
                System.out.printf("Receive message from %s :: %s\n", channel, message);
            }
        };
    }
}
