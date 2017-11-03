package com.store.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.store.common.pojo.EUDataGridResult;
import com.store.common.utils.StoreResult;
import com.store.pojo.TbContent;
import com.store.rest.service.ContentService;

@Controller
public class ContentController {
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/content/query/list/{parentId}")
	@ResponseBody
	public StoreResult getContentList(@PathVariable long parentId){
		System.out.println("进入rest-ContentController，parentId："+parentId);
		List<TbContent> list=contentService.getContentList(parentId);
		return StoreResult.ok(list);
	}

	/**
	 * 接收的content需要与前端的属性名一致
	 * @param content
	 * @return
	 */
	@RequestMapping("/content/save")
	@ResponseBody
	public StoreResult saveContent(TbContent content) {
		StoreResult result=contentService.saveContent(content);
		System.out.println("打印StoreResult.status："+result.getStatus());
		return result;
		
	}
	
	/**
	 * 修改content信息
	 * @return
	 */
	@RequestMapping("/rest/content/edit")
	@ResponseBody
	public StoreResult contentEdit(TbContent content){
		System.out.println("content的id："+content.getId());
		StoreResult result=contentService.contentEdit(content);
		return result;
	}
	
	/**
	 * 删除content
	 * @return
	 */
	@RequestMapping("/content/delete")
	@ResponseBody
	public StoreResult deleteContent(long ids){
		System.out.println("要删除的id："+ids);
		StoreResult result=contentService.deleteContent(ids);
		return result;
	}
}
