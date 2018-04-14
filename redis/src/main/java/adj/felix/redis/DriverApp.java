package adj.felix.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import adj.felix.redis.driver.RedisPool;

public class DriverApp {
	private Jedis jedis = RedisPool.getInstance().getJedis();
	
	@Test
	public void string() {
		String value = jedis.set("string-01", "string-001");
		System.out.println(value);
	}
	
	
	@Test
	public void hset() {
		jedis.hset("hash", "hash-key-01", "hash-val-01");
		jedis.hset("hash", "hash-key-02", "hash-val-02");
		jedis.hset("hash", "hash-key-03", "hash-val-03");
		jedis.hset("hash", "hash-key-04", "hash-val-04");
		jedis.hset("hash", "hash-key-05", "hash-val-05");
		
		Map<String, String> map = jedis.hgetAll("hash");
		
		System. out.println(map);
	}
	
	@Test
	public void lset() {
		jedis.lpush("list-01", "list-val-01");
		jedis.lpush("list-01", "list-val-02");
		jedis.lpush("list-01", "list-val-03");
		jedis.lpush("list-01", "list-val-04");
		
		List<String> list = jedis.lrange("list-01", 0 ,2);
		
		for (String val: list) {
			System.out.println(val);
		}
	}
	
	@Test
	public void sset() {
		jedis.sadd("set", "set-val-01");
		jedis.sadd("set", "set-val-02");
		jedis.sadd("set", "set-val-03");
		jedis.sadd("set", "set-val-04");
		
		Set<byte[]> list = jedis.smembers("set".getBytes());
		
		for (byte[] val: list) {
			System.out.println(new String(val));
		}
	}
	
	
}
