package cn.cache;

import cn.cfg.RedisPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author chengchenrui
 * @version Id: JedisUtil.java, v 0.1 2018/6/29 1:13 chengchenrui Exp $$
 */
@Component
public class JedisUtil {

    @Autowired
    private RedisPool redisPool;

    private Jedis getResource() {
        return redisPool.getJedis();
    }

    public byte[] set(byte[] key, byte[] value) {
        Jedis jedis = getResource();
        try {
            jedis.set(key, value);
            return value;
        } finally {
            jedis.close();
        }
    }


    public String set(String key, String value, int expire) {
        Jedis jedis = getResource();
        try {
            jedis.set(key, value, "NX", "EX", expire);
            return value;
        } finally {
            jedis.close();
        }
    }



    public void expire(byte[] key, int i) {
        Jedis jedis = getResource();
        try {
            jedis.expire(key, i);
        } finally {
            jedis.close();
        }
    }

    public byte[] get(byte[] key) {
        Jedis jedis = getResource();
        try {
            return jedis.get(key);
        } finally {
            jedis.close();
        }
    }

    public void del(byte[] key) {
        Jedis jedis = getResource();
        try {
            jedis.del(key);
        } finally {
            jedis.close();
        }
    }

    public Set<byte[]> keys(String sHIRO_SESSION_PREFIX) {
        Jedis jedis = getResource();
        try {
            return jedis.keys((sHIRO_SESSION_PREFIX + "*").getBytes());
        } finally {
            jedis.close();
        }
    }
}