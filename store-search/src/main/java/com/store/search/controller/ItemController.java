package com.store.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.store.common.utils.StoreResult;
import com.store.search.service.ItemService;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	/**
	 * 导入商品数据到索引库
	 */
	@RequestMapping("/manager/importall")
	@ResponseBody	
	public StoreResult importAllItems(){
		StoreResult result = itemService.importAllItems();
		return result;
	}
}
