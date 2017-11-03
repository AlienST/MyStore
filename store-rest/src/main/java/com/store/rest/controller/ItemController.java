package com.store.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
/**
 * 商品查询类
 * @author 麦苗
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.store.common.utils.StoreResult;
import com.store.rest.service.ItemService;
@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/info/{itemId}")
	@ResponseBody
	public StoreResult getItemBaseInfo(@PathVariable long itemId){
		StoreResult result = itemService.getItemBaseInfo(itemId);
		return result;
	}
}
