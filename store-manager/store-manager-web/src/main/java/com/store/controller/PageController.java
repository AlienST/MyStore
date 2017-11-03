package com.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 后台页面跳转
 * @author 麦苗
 * 前缀：/WEB-INF/jsp/与后缀.jsp 已经在视图解析器上添加了
 */
@Controller
public class PageController {
	
	/**
	 * 
	 * 打开后台首页
	 */
	@RequestMapping("/")
	public String showIndex(){
		return "index";
	}
	
	/**
	 * 新增商品
	 * @return
	 */
	@RequestMapping("/itemAdd")
	public String itemAdd(){
		return "item/item-add";
	}
	/**
	 * 查询商品
	 */
	@RequestMapping("/itemList")
	public String itemList(){
		return "item/item-list";
	}
	
	/**
	 * 规格参数
	 * @return
	 */
	@RequestMapping("/itemParamList")
	public String itemParamList(){
		return "item/item-param-list";
	}
	
	/**
	 * 内容分类管理
	 * @return
	 */
	@RequestMapping("/contentCategory")
	public String contentCategory(){
		return "content/content-category";
	}
	
	/**
	 * 内容管理
	 * @return
	 */
	@RequestMapping("/content")
	public String content(){
		return "content/content";
	}
	
	/**
	 * 商品规格参数添加
	 * @return
	 */
	@RequestMapping("/item/param/add")
	public String itemParamAdd(){	
		return "item/item-param-add";
	}

	/**
	 * 商品编辑/修改
	 * @return
	 */
	@RequestMapping("/rest/page/item-edit")
	public String itemEdit(){
		return "item/item-edit";
	}

	/**
	 * 跳转新的后台首页
	 */
	@RequestMapping("/home")
	public String newHome(){
		return "home";
	}

	
	/**
	 * 搜索商品
	 */
	@RequestMapping("/itemSearch")
	public String itemSearch(){
		
		return "item/item-search";
	}

	@RequestMapping("/content/add")
	public String contentAdd(){
		return "content/content-add";
	}
	
	@RequestMapping("/content-edit")
	public String contentEdit(){
		return "content/content-edit";
		
	}
}
