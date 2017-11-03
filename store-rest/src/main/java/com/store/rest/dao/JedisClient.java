package com.store.rest.dao;
/**
 * Jedis的接口
 * @author 麦苗
 *
 */
public interface JedisClient {
	//设置常用的方法
	String set(String key,String value);
	String get(String key); 
	long hset(String hkey,String key,String value);
	String hget(String hkey,String key);
	//自增长
	long incr(String key);
	//设置key的过期时间，过期后改key的值为null，
	//返回值-2：key被删除; -1：key为永久; 1：key可用
	long expire(String key, int second);
	//查看key是否过期
	long ttl(String key);
	long del(String key);
	long hdel(String hkey, String key);

	//用于测试
	void getTest();
	
}
