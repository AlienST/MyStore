package com.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.store.common.pojo.EUDataGridResult;
import com.store.common.utils.StoreResult;
import com.store.pojo.TbItemParam;
import com.store.service.ItemParamService;

@Controller
@RequestMapping("/item/param")
public class ItemParamComtroller {
	@Autowired
	private ItemParamService itemParamService;
	
	/**
	 * 查询商品模板是否存在
	 * 从路径中/query/itemcatid/{itemCatId}去参数itemCatId
	 */
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public StoreResult getItemParamByCid(@PathVariable Long itemCatId){
		StoreResult result= itemParamService.getItemParamByCid(itemCatId);
		return result;
	}
	
	/**
	 * 添加商品模板
	 * @return
	 */
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public StoreResult saveItemParam(@PathVariable("cid") long cid, String paramData){	
		TbItemParam itemParam=new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		StoreResult result=itemParamService.saveItemParam(itemParam);
		System.out.println("打印saveItemParam的StoreResult.ok():"+result.getStatus());
		return result;
	}
	
	/**
	 * 查询商品规格参数
	 * @param page 
	 * @param rows
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getItemParam(Integer page,Integer rows){
		System.out.println("进入ItemParamComtroller,page,rows:"+page+";"+rows);
		EUDataGridResult dataGridResult=itemParamService.getItemParam(page, rows);
		System.out.println("打印dataGridResult："+dataGridResult);
		return dataGridResult;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
