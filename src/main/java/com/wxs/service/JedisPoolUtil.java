package com.wxs.service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName: JedisPoolUtil
 * @Author: WuXiangShuai
 * @Time: 15:46 2019/8/26.
 * @Description:
 */
public class JedisPoolUtil {
    private static volatile JedisPool jedisPool = null;

    private JedisPoolUtil() {
    }

    public static JedisPool getJedisPoolInstance() {
        if (null == jedisPool) {
            synchronized (JedisPoolUtil.class) {
                if (null == jedisPool) {
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    poolConfig.setMaxTotal(200);
                    poolConfig.setMaxIdle(32);
                    poolConfig.setMaxWaitMillis(100 * 1000);
                    poolConfig.setBlockWhenExhausted(true);
                    poolConfig.setTestOnBorrow(true);
                    jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379, 60000);
                }
            }
        }
        return jedisPool;
    }

    // 重新了close，自动归还线程
//    public static void release(JedisPool jedisPool, Jedis jedis) {
//        if (null != jedis) {
//            jedisPool.returnResource(jedis);
//        }
//    }
}
