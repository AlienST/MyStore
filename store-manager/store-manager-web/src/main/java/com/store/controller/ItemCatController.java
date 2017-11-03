package com.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * 商品分类ItemCat
 * @author 麦苗
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.store.common.pojo.EUTreeNode;
import com.store.service.ItemCatService;
import com.store.service.ItemService;
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	
	/**
	 * 返回商品分类树形数据
	 * @param parentId
	 * @return
	 *  item/cat/list?id=*** 
	 * 将参数id映射到方法的参数parentId，并默认初始化值为0
	 */
	@RequestMapping("/list")  //前端传来的url：/item/cat/list
	@ResponseBody
	public List<EUTreeNode> getCatList(@RequestParam(value="id",defaultValue="0") long parentId ){
		List<EUTreeNode> list=itemCatService.getCatList(parentId);
		return list;
	}
}
