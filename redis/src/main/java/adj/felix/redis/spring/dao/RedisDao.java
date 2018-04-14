package adj.felix.redis.spring.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;

public class RedisDao {

    protected RedisTemplate<String, Object> redisTemplate;
    
    /**指定缓存失效时间**/
    public void setExpire(String key, long seconds) {
    	redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }
    
    /**根据key获取缓存过期时间**/
    public Long getExpire(String key) {
    	return redisTemplate.getExpire(key);
    }
    /**判断key是否存在**/
    public boolean hasKey(String key) {
    	return redisTemplate.hasKey(key);
    }
    
    /**删除keys**/
    public boolean delKeys(String... keys) {
    	if (keys == null || keys.length == 0) {
    		return false;
    	}
    	if (keys.length == 1) {
    		redisTemplate.delete(keys[0]);
    	}
    	else {
    		redisTemplate.delete(Arrays.asList(keys));
    	}
    	return true;
    }
    
    public Object get(String key) {
    	return redisTemplate.opsForValue().get(key);
    }
    
    public boolean set(String key, Object val) {
    	redisTemplate.opsForValue().set(key, val);
    	return true;
    }
    
    public boolean set(String key, Object val, long seconds) {
    	redisTemplate.opsForValue().set(key, val, seconds, TimeUnit.SECONDS);
    	return true;
    }
    
    // =======================HashSet======================//
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }
    public Map<Object, Object> hmget(String hashKey) {
        return redisTemplate.opsForHash().entries(hashKey);
    }
    public boolean hmset(String key, Map<String, Object> map) {
    	redisTemplate.opsForHash().putAll(key, map);
    	return true;
    }
    public boolean hset(String key, String item, Object value) {
    	redisTemplate.opsForHash().put(key, item, value);
    	return true;
    }
    public boolean hdel(String key, Object... hashKeys) {
        redisTemplate.opsForHash().delete(key, hashKeys);
        return true;
    }
    public boolean hhaskey(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }
    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }
    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }
    
    // =======================Set======================//
    public Set<Object> sget(String key) {
    	return redisTemplate.opsForSet().members(key);
    }
    public boolean shas(String key, Object value) {
    	return redisTemplate.opsForSet().isMember(key, value);
    }
    public long sset(String key, Object... values) {
    	return redisTemplate.opsForSet().add(key, values);
    }
    public long ssetExpire(String key, long time, Object... values) {
    	Long count = redisTemplate.opsForSet().add(key, values);
    	setExpire(key, time);
    	return count;
    }
    public long sgetSize(String key) {
    	return redisTemplate.opsForSet().size(key);
    }
    public long sremove(String key, Object... values) {
    	Long count = redisTemplate.opsForSet().remove(key, values);
    	return count;
    }
    
    // =======================List======================//
    public List<Object> lget(String key, long start, long end) {
    	return redisTemplate.opsForList().range(key, start, end);
    }
    public Object lrpop(String key) {
    	return redisTemplate.opsForList().rightPop(key);
    }
    public long lgetSize(String key) {
    	return redisTemplate.opsForList().size(key);
    }
    public Object lgetIndex(String key, long index) {
    	return redisTemplate.opsForList().index(key, index);
    }
    public boolean lset(String key, Object value) {
    	redisTemplate.opsForList().rightPush(key, value);
    	return true;
    }
    public boolean lset(String key, List<Object> value) {
    	redisTemplate.opsForList().rightPushAll(key, value);
    	return true;
    }
    public long llpush(String key, Object value) {
    	return redisTemplate.opsForList().leftPush(key, value);
    }
    public long lrpush(String key, Object value) {
    	return redisTemplate.opsForList().rightPush(key, value);
    }
    public boolean lUpdateIndex(String key, long index, Object value) {
    	redisTemplate.opsForList().set(key, index, value);
    	return true;
    }
    public long lRemove(String key, long count, Object value) {
    	Long remove = redisTemplate.opsForList().remove(key, count, value);
    	return remove;
    }
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
}