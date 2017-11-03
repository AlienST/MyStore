package com.store.rest.service;

import java.util.List;

import com.store.common.pojo.EUDataGridResult;
import com.store.common.utils.StoreResult;
import com.store.pojo.TbContent;

public interface ContentService {
	//根据叶子节点查询内容分类的具体信息
	public List<TbContent> getContentList(long parentId);
	/**
	 * 保存content
	 * @return
	 */
	public StoreResult saveContent(TbContent content);
	
	/**
	 * 修改content信息
	 * @param content
	 * @return
	 */
	public StoreResult contentEdit(TbContent content);


	/**
	 * 删除content
	 * @return
	 */
	public StoreResult deleteContent(long id);
}


