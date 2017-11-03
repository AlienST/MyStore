package com.store.service;

import java.util.List;

import com.store.common.pojo.EUTreeNode;
import com.store.common.utils.StoreResult;

public interface ContentCategoryService {
	public List<EUTreeNode> getCategoryList(long parentId);
	//添加ContentCategory
	public StoreResult insertContentCategoey(long parent,String name);
	
	//删除ContentCategory
	/**
	 * 
	 * @param parentId   子节点的父类ID
	 * @param id  分类的ID
	 * @return
	 */
	public StoreResult deleteContentCategory(long id);
	
	
	/**
	 * 更新ContentCategory名字
	 * @param id  id
	 * @param name  重命名
	 * @return
	 */
	public StoreResult updateContentCategory(long id , String name);
}
