package com.store.rest.service;

import com.store.common.utils.StoreResult;

public interface RedisService {
	/**
	 * 同步广告位的内容修改
	 * @param contentCid
	 * @return
	 */
	public  StoreResult syncContent(long contentCid);
}
