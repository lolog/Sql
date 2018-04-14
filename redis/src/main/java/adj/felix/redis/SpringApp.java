package adj.felix.redis;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import adj.felix.redis.spring.AppContext;
import adj.felix.redis.spring.dao.RedisDao;

public class SpringApp 
{
	private AppContext appContext = AppContext.getInstance();
	private RedisDao redisDao = appContext.getBean("redisDao", RedisDao.class);
	
	@Test
	public void hset() {
		redisDao.hset("hset-01", "key-01", "value-01");
	}
	@Test
	public void hget() {
		Object val = redisDao.hget("hset-01", "key-01");
		System.out.println(val);
	}
	@Test
	public void hmget() {
		Map<Object, Object> val = redisDao.hmget("hset-01");
		System.out.println(val);
	}
	@Test
	public void hdel() {
		redisDao.hdel("hset-01", "key-01");
	}
	@Test
	public void hhaskey() {
		boolean exist = redisDao.hhaskey("hset-01", "key-01");
		System.out.println(exist);
	}
	
	// ================= Set =====================//
	@Test
	public void sset() {
		redisDao.sset("sset-01", "val-01", "val-02", "val-03");
	}
	@Test
	public void shas() {
		boolean exist = redisDao.shas("sset-01", "val-01");
		System.out.println(exist);
	}
	@Test
	public void sget() {
		Set<Object> vals = redisDao.sget("sset-01");
		System.out.println(vals);
	}
	@Test
	public void sgetSize() {
		long count = redisDao.sgetSize("sset-01");
		System.out.println(count);
	}
	@Test
	public void sremove() {
		long count = redisDao.sremove("sset-01", "val-01");
		System.out.println(count);
	}
	
	// =======================List======================//
	@Test
	public void lset() {
		redisDao.lset("lset-01", Arrays.asList(new String[]{"lset-01", "lset-02", "lset-01"}));
	}
	@Test
	public void lgetSize() {
		long count = redisDao.lgetSize("lset-01");
		System.out.println(count);
	}
	@Test
	public void lgetIndex() {
		Object val = redisDao.lgetIndex("lset-01", 2);
		System.out.println(val);
	}
	@Test
	public void lget() {
		List<Object> vals = redisDao.lget("lset-01", 0,5);
		System.out.println(vals);
	}
	@Test
	public void llpush() {
		Long count = redisDao.llpush("lset-01", "lset-0");
		System.out.println(count);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
