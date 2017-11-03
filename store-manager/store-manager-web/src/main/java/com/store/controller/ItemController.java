package com.store.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.store.common.pojo.EUDataGridResult;
import com.store.common.utils.JsonUtils;
import com.store.common.utils.StoreResult;
import com.store.pojo.TbItem;
import com.store.service.ItemService;
/**
 * 商品管理表现层
 * @author 麦苗
 *
 */
@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public  TbItem geiItemById(@PathVariable Long itemId){
		TbItem item=itemService.geItemById(itemId);
		return item;
	}

	@RequestMapping("/item/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows){
		EUDataGridResult dataGridResult= itemService.getItemList(page, rows);
		return dataGridResult;
	}

	/**
	 * 上传商品图片处理
	 * uploadFile名称要与前端名一致
	 */
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String pictureUpLoad(MultipartFile uploadFile){
		Map result=itemService.uploadItemPhoto(uploadFile);
		System.out.println("在controller返回图片回显url:"+result.get("url")+";状态："+result.get("error"));
		String json=JsonUtils.objectToJson(result);
		System.out.println("打印Json："+json);
		return json;
	}
	
	
	/**
	 * 保存添加表单中的商品
	 * 要求传入的表单是post请求
	 * @param item 提交的商品基本信息的表单
	 * @param desc  商品的描述
	 * @param itemParams  商品的规格参数
	 * @throws Exception 
	 */
	@RequestMapping(value="/item/save",method=RequestMethod.POST)
	@ResponseBody
	public StoreResult saveItem(TbItem item, String desc,String itemParams) throws Exception{
		StoreResult result=itemService.saveItem(item, desc,itemParams);
		System.out.println("走出Control,打印rrsult："+result);
		return result;	
	}

	/**
	 * 删除商品
	 * @param ids 商品ID
	 * @return
	 */
	@RequestMapping("/rest/item/delete")
	@ResponseBody
	public StoreResult deleteItem(Integer ids){
		System.out.println("进入ItemController，ids："+ids);
		StoreResult result=itemService.deleteItem(ids);
		System.out.println("打印StoreResult.ok():"+result.getStatus());
		return result;
	}

	
	@RequestMapping("/rest/item/update")
	@ResponseBody
	public StoreResult updateItem(TbItem item){
		System.out.println("修改商品的ID:"+item.getId());
		StoreResult result= itemService.updateItem(item);
		System.out.println("打印StoreResult.ok():"+result.getStatus());
		return result;
	}
}
