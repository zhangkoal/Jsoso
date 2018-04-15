package cn.cfg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import redis.clients.jedis.Jedis;

import java.io.Serializable;
import java.util.Set;

/**
 * session业务
 */
@Component
public class CacheDataFactory implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(CacheDataFactory.class);

	@Autowired
	private RedisPool redisPool;
	private CacheDataFactory() {

	}

	private static class SingletonHolder {
		private static final CacheDataFactory INSTANCE = new CacheDataFactory();
	}

	public static final CacheDataFactory getInstance() {
		return SingletonHolder.INSTANCE;
	}

	/**
	 * @param key
	 * @return
	 */
	public String getKey(String key) {
		return key;
	}

	/**
	 *
	 * @param key
	 * @param value
	 */
	public void updateCacheData(String key, Object value) {
		Jedis jedis = redisPool.getJedis();
		updateCacheData(jedis, key, value, null);
		RedisPool.closeConn(jedis);
	}

	public void updateCacheData(String key, Object value, Integer seconds) throws Exception {
		Jedis jedis = redisPool.getJedis();
		if(seconds == null) {
			updateCacheData(jedis, key, value, null);
		} else {
			updateCacheData(jedis, key, value, seconds);
		}
		RedisPool.closeConn(jedis);
	}

	/**
	 *
	 * @param jedis
	 * @param key
	 * @param value
	 */
	public void updateCacheData(Jedis jedis, String key, Object value, Integer seconds) {
		if (value == null) {
			value = "0";
		}
		if(seconds == null) {
			jedis.set(key, value.toString());
		} else {
			jedis.setex(key, seconds.intValue(), value.toString());
		}
	}

	/**
	 * @param key
	 */
	public void deleteCacheDataByKey(String key) {
		Jedis jedis = redisPool.getJedis();
		if(jedis != null) {
			jedis.del(key);
			RedisPool.closeConn(jedis);
		}
	}

	/**
	 *
	 * @param key
	 * @return
	 */
	public Object getCacheDataByKey(String key) {
		Jedis jedis = redisPool.getJedis();
		if(jedis != null) {
			Object value = jedis.get(key);
			RedisPool.closeConn(jedis);
			return value;
		}
		return null;
	}
	public Set<String> getCacheDataKeys() {
		Jedis jedis = null;
		jedis = redisPool.getJedis();
		if(jedis != null) {
			Set<String> sets = jedis.keys("*");
			RedisPool.closeConn(jedis);
			return sets;
		}
		return null;
	}
}
