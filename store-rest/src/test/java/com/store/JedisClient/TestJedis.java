package com.store.JedisClient;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class TestJedis {
	/**
	 * 单机版Jedis
	 */
	
	@Test
	public void testJedisSingle() {
		Jedis jedis=new Jedis("172.18.90.23", 6379); //创建Jedis对象
		jedis.auth("admin");
		jedis.set("key1", "MyRedis"); //调用jedis对象的方法，
		String ping = jedis.ping();  
        System.out.println(ping);  
		String string = jedis.get("key1");
		System.out.println("输出Jedis:"+string);
		jedis.close(); //关闭Jedis
		
	}

	
	
	@Test
	public void testJedisPool(){
		JedisPool pool=new JedisPool("172.18.90.23", 6379);
		Jedis jedis=pool.getResource();
		jedis.auth("admin");
		String string = jedis.get("key1");
		System.out.println(string);
		jedis.close();
		pool.close();
	}
	
	/**
	 * 测试Jedis与Spring单机版的整合
	 */
	
	@Test
	public void testSpringJedis(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisPool pool = (JedisPool) applicationContext.getBean("redisPool");
		Jedis jedis = pool.getResource();
		jedis.auth("admin");
		String string = jedis.get("key1");
		System.out.println(string);
		jedis.close();
		pool.close();
	}

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisPool pool = (JedisPool) applicationContext.getBean("redisPool");
		Jedis jedis = pool.getResource();
		jedis.auth("admin");
		String string = jedis.get("key1");
		System.out.println(string);
		jedis.close();
		pool.close();
	}
	
	
	
	
}
