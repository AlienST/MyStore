package com.store.rest.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.store.rest.dao.JedisClient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
/**
 * 单击版JedisClient
 * @author 麦苗
 *
 */
public class JedisClientSingle implements JedisClient {
	public JedisClientSingle(){
		System.out.println("JedisClientSingle初始化成功！");
	}
	
	//从Spring容器中注入redisPool对象
	@Autowired
	private JedisPool redisPool;
	
	@Override
	public String set(String key, String value) {
		Jedis jedis = redisPool.getResource();
		jedis.auth("admin");
		String string = jedis.set(key, value);
		jedis.close();
		return string;
	}

	@Override
	public String get(String key) {
		Jedis jedis = redisPool.getResource();
		jedis.auth("admin");
		String strKey = jedis.get(key);
		jedis.close();
		return strKey;
	}

	@Override
	public long hset(String hkey, String key, String value) {
		Jedis jedis = redisPool.getResource();
		jedis.auth("admin");
		Long result = jedis.hset(hkey, key, value);
		jedis.close();
		return result;
	}

	@Override
	public String hget(String hkey, String key) {
		Jedis jedis = redisPool.getResource();
		jedis.auth("admin");
		String string = jedis.hget(hkey, key);
		jedis.close();
		return string;
	}

	@Override
	public long incr(String key) {
		Jedis jedis = redisPool.getResource();
		jedis.auth("admin");
		Long result = jedis.incr(key);
		jedis.close();
		return result;
	}

	@Override
	public long expire(String key, int second) {
		Jedis jedis = redisPool.getResource();
		jedis.auth("admin");
		Long reslut = jedis.expire(key, second);
		jedis.close();
		return reslut;
	}

	@Override
	public long ttl(String key) {
		Jedis jedis = redisPool.getResource();
		jedis.auth("admin");
		Long result = jedis.ttl(key);
		jedis.close();
		return result;
	}

	@Override
	public long del(String key) {
		Jedis jedis = redisPool.getResource();
		jedis.auth("admin");
		Long result = jedis.del(key);
		jedis.close();
		return result;
	}

	@Override
	public long hdel(String hkey, String key) {
		Jedis jedis = redisPool.getResource();
		jedis.auth("admin");
		Long result = jedis.hdel(hkey, key);
		jedis.close();
		return result;
	}

	public void getTest(){
		System.out.println("测试注入！");
	}
}
