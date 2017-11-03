package com.store.rest.service;

import com.store.common.utils.StoreResult;

public interface ItemService {
	/**
	 * 根据商品id，查询商品的基本信息
	 * @param itemId 商品id
	 * @return
	 */
	public StoreResult getItemBaseInfo(long itemId);
}
