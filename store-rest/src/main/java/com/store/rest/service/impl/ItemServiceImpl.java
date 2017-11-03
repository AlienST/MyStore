package com.store.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.common.utils.StoreResult;
import com.store.mapper.TbItemMapper;
import com.store.pojo.TbItem;
import com.store.rest.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private TbItemMapper itemMapper;
	
	
	/**
	 * 根据商品id，查询商品的基本信息
	 * @param itemId 商品id
	 * @return
	 */
	@Override
	public StoreResult getItemBaseInfo(long itemId) {
		//根据商品id查询基本信息
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		return StoreResult.ok(item);
	}

}
