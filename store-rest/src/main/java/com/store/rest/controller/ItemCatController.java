package com.store.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.store.common.utils.JsonUtils;
import com.store.rest.pojo.CatResult;
import com.store.rest.service.ItemCatService;

@Controller
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	
	/**
	 * 获取商品分类list
	 * @param callBack 回调函数的方法名
	 * @return  produces=.. :使用Spring提供的返回Json数据使用的编码格式
	 */
	@RequestMapping(value="/itemCat/list",produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public String getItemCatList(String callBack){
		CatResult catResult= itemCatService.getItemCatList();
		//把pojo转化为Json
		String Json=JsonUtils.objectToJson(catResult);	
		//拼装返回格式
		String result=callBack + "("+Json+");";
		return result;
		
	}
}
