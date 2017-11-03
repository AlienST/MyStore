package com.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.store.common.pojo.EUTreeNode;
import com.store.common.utils.StoreResult;
import com.store.service.ContentCategoryService;

@Controller
public class ContentCategoryController {
	@Autowired
	private ContentCategoryService categoryService;
	/**
	 * 获取content分类树形数据
	 * @param parentId content的ID
	 * @return  返回content大分类树形数据
	 * content/category/list?id=*** 
	 * 将参数id映射到方法的参数parentId，并默认初始化值为0
	 */
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EUTreeNode> getCategoryList(@RequestParam(value="id",defaultValue="0") long parentId){
		List<EUTreeNode> list=categoryService.getCategoryList(parentId);
		return list;
	}

	/**
	 * 添加ContentCategory
	 * @return 父节点id与新增的子节点名称, 返回主键Id,
	 * 返回一个ContentCategory的pojo对象，用于显示子节点
	 */
	@RequestMapping("/content/category/create")
	@ResponseBody
	public StoreResult insertContentCategoey(long parentId, String name){
		StoreResult result= categoryService.insertContentCategoey(parentId, name);
		return result;
	}

	/**
	 * 删除ContentCategory
	 * @return 
	 * 
	 */
	@RequestMapping("/content/category/delete")
	@ResponseBody
	public StoreResult deleteContentCategory( long id){
		System.out.println("打印id"+id);
		StoreResult result= categoryService.deleteContentCategory(id);
		return result;
	}
	
	/**
	 * 更新ContentCategory名字
	 * @param id  id
	 * @param name  重命名
	 * @return
	 */
	@RequestMapping("/content/category/update")
	@ResponseBody
	public StoreResult updateContentCategory(long id,String name){
		System.out.println("进入updateContentCategory，id，name："+id+";"+name);
		StoreResult result= categoryService.updateContentCategory(id, name);
		return result;
	}
}
