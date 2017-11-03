package com.store.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.store.common.pojo.EUDataGridResult;
import com.store.common.utils.StoreResult;
import com.store.pojo.TbItem;

public interface ItemService {
	/**
	 * 接收商品ID，放回一个pojo对象
	 */
	public TbItem geItemById(long itemId);
	
	/**
	 * 返回Easyui对象数据
	 */
	public EUDataGridResult getItemList(int page,int rows);

	/**
	 * 商品图片的上传
	 * @return upLoadFile 上传的商品图片
	 * @param 
	 */
	public Map uploadItemPhoto(MultipartFile upLoadFile);
	
	/**
	 * 保存添加的商品
	 * @param item 提交的商品基本信息的表单
	 * @param ItemDesc  商品的描述
	 * @param itemParams  商品的规格参数
	 * @return
	 * @throws Exception
	 */
	public StoreResult saveItem(TbItem item,String ItemDesc,String itemParams)throws Exception;

	/**
	 * 删除商品
	 * @param ids 商品的ID
	 * @return
	 */
	public StoreResult deleteItem(long ids);

	/**
	 * 修改商品信息
	 * @param id 商品
	 * @return
	 */
	public StoreResult updateItem(TbItem item);

}
