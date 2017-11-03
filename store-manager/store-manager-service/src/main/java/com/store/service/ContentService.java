package com.store.service;

import com.store.common.pojo.EUDataGridResult;
import com.store.common.utils.StoreResult;
import com.store.pojo.TbContent;

public interface ContentService {
	//根据叶子节点查询内容分类的具体信息
	public EUDataGridResult getContentList(long parentId,int page, int rows);
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


