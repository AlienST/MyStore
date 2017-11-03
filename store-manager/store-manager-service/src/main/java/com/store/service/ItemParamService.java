package com.store.service;

import com.store.common.pojo.EUDataGridResult;
import com.store.common.utils.StoreResult;
import com.store.pojo.TbItemParam;

public interface ItemParamService {
	/**
	 * 查询商品目类是否添加过
	 * @param cid
	 * @return
	 */
	public StoreResult getItemParamByCid(long cid);
	
	/**
	 * 添加商品模板
	 * @return 
	 */
	public StoreResult saveItemParam(TbItemParam itemParam);

	/**
	 * 查询商品规格参数
	 * @param page
	 * @param rows
	 * @return
	 */
	public EUDataGridResult getItemParam(int page,int rows);
}
