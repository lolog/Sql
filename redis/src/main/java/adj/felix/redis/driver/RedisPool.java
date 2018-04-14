package adj.felix.redis.driver;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {
    private static String HOST = "localhost";
    private static int PORT = 6379;
    private static String PASSWORD = null;
 
    // 最多空闲的jedis实例,默认值也是8
    private static int MAX_IDLE = 200;
    private static int MIN_IDLE = 10;
    // 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。
    // 如果超过等待时间，则直接抛出JedisConnectionException
    private static int MAX_WAIT_MILLIS = 2000;
    // 链接超时时间
    private static int TIMEOUT = 10000;
    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true,则得到的jedis实例均是可用的
    private static boolean TEST_ON_BORROW = true;
    
    private static JedisPool jedisPool;
    
    private static final RedisPool INSTANCE = new RedisPool();
    
    private RedisPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(MAX_IDLE);
        config.setMinIdle(MIN_IDLE);
        config.setTestOnBorrow(TEST_ON_BORROW);
        config.setMaxWaitMillis(MAX_WAIT_MILLIS);
        jedisPool = new JedisPool(config, HOST, PORT, TIMEOUT, PASSWORD);
	}
    
    public static RedisPool getInstance() {
    	return INSTANCE;
    }
    
    public synchronized Jedis getJedis() {
    	if (jedisPool == null) {
    		return null;
    	}
    	return jedisPool.getResource();
    }
    
    public void close(final Jedis jedis) {
    	if (jedis == null) {
    		return;
    	}
    	jedis.close();
    }
}
