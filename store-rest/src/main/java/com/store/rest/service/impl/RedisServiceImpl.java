package com.store.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.store.common.utils.StoreResult;
import com.store.rest.service.RedisService;
import com.store.rest.dao.JedisClient;

@Service
public class RedisServiceImpl implements RedisService {
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;

	/**
	 * 同步后台管理修改广告位数据，删除redis中广告位key
	 */
	@Override
	public StoreResult syncContent(long contentCid) {
		try {
			long result = jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, contentCid+"");
			System.out.println("删除的结果:"+result);
		} catch (Exception e) {
			System.out.println("同步删除redis-key失败！");
			return StoreResult.build(500, "同步删除redis-key失败！");
		}
		return StoreResult.ok();
	}

}
