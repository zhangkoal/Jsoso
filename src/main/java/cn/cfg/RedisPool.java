package cn.cfg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class RedisPool {

    private static final Logger logger = LoggerFactory.getLogger(RedisPool.class);

    private JedisPool jedisPool = null;

    @Autowired
    private JedisCfg jedisCfg;

    /**
     * 获取Jedis实例
     *
     * @return
     */
    public Jedis getJedis(){
        try {
            if (jedisPool != null) {
                Jedis jedis = jedisPool.getResource();
                return jedis;
            } else {
               return getJedisPool().getResource();
            }
        } catch (Exception e) {
            logger.error("Redis数据库连接失败！", e);
            return null;
        }
    }

    public JedisPool getJedisPool() {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            /*
             * 注意： 在高版本的jedis
             * jar包，比如本版本2.9.0，JedisPoolConfig没有setMaxActive和setMaxWait属性了
             * 这是因为高版本中官方废弃了此方法，用以下两个属性替换。 maxActive ==> maxTotal maxWait==>
             * maxWaitMillis
             */
            config.setMaxTotal(jedisCfg.MAX_TOTAL);
            config.setMaxIdle(jedisCfg.MAX_IDLE);
            config.setMaxWaitMillis(jedisCfg.MAX_WAIT_MILLIS);
            config.setTestOnBorrow(jedisCfg.TEST_ON_BORROW);
            jedisPool = new JedisPool(config, jedisCfg.IP, jedisCfg.PORT, jedisCfg.TIME_OUT, jedisCfg.AUTH);
            return jedisPool;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public static void closeConn(final Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
