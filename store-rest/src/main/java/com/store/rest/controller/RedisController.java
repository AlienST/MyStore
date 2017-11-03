package com.store.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.store.common.utils.StoreResult;
import com.store.rest.service.RedisService;

@Controller
public class RedisController {
	@Autowired
	private RedisService redisService;
	
	/**
	 * 同步后台管理修改广告位数据，删除redis中广告位key
	 */
	@RequestMapping("content/{contentCid}")
	@ResponseBody
	public StoreResult contentCacheSync(@PathVariable long contentCid ){
		System.out.println("contentCid:"+contentCid);
		StoreResult result = redisService.syncContent(contentCid);
		return result;
	}

}
