package com.scene;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Random;
import java.util.Set;

/**
 * 分数排名或者排行榜
 *
 * @author Created by lx_068
 * @date 2022/01/16
 */
public class RankingList {

    public static void main(String[] args) {
        try (JedisPool jedisPool = new JedisPool(); Jedis jedis = jedisPool.getResource()) {
            String key = "rankingList";
            jedis.del(key);

            for (int i = 0; i < 1000; i++) {
                String user = "user:" + new Random(System.currentTimeMillis()).nextInt(10);
                jedis.zincrby(key, 1, user);

                Set<String> users = jedis.zrevrange(key, 0, 2);
                System.out.print("Top 3:");
                for (String item : users) {
                    System.out.printf("%s -- %f  ", item, jedis.zscore(key, item));
                }
                System.out.println();
            }
        }
    }
}
