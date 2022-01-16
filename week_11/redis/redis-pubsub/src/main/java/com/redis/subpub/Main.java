package com.redis.subpub;

import redis.clients.jedis.JedisPool;

/**
 * @author Created by lx_068
 * @date 2022/01/16
 */
public class Main {

    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool();
        String channelName = "ORDER";

        SubscribeOrder subscribeOrder = new SubscribeOrder(jedisPool, channelName);
        PublishOrder publishOrder = new PublishOrder(jedisPool, channelName);
    }
}