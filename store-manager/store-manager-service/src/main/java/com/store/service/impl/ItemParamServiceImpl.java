package com.store.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.store.common.pojo.EUDataGridResult;
import com.store.common.utils.StoreResult;
import com.store.mapper.TbItemParamMapper;
import com.store.pojo.TbItem;
import com.store.pojo.TbItemParam;
import com.store.pojo.TbItemParamExample;
import com.store.pojo.TbItemParamExample.Criteria;
import com.store.service.ItemParamService;

@Service
public class ItemParamServiceImpl implements ItemParamService{
	@Autowired
	private TbItemParamMapper itemParamMapper;

	/**
	 * 查询商品模板
	 */
	@Override
	public StoreResult getItemParamByCid(long cid) {
		System.out.println("进入ItemParamServiceImpl，打印cid:"+cid);
		TbItemParamExample example=new TbItemParamExample();
		Criteria critera= example.createCriteria();
		critera.andItemCatIdEqualTo(cid);
		List<TbItemParam> list=	itemParamMapper.selectByExampleWithBLOBs(example);
		System.out.println("打印List:"+list);
		if (list != null && list.size() > 0) {
			return StoreResult.ok(list.get(0));
		}
		return StoreResult.ok();
	}

	
	/**
	 * 添加商品模板
	 * @return
	 */
	@Override
	public StoreResult saveItemParam(TbItemParam itemParam) {
		//补充信息
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		itemParamMapper.insert(itemParam);
		return StoreResult.ok();
	}


	

	/**
	 * 查询商品规格参数
	 * @param page
	 * @param rows
	 * @return
	 */
	@Override
	public EUDataGridResult getItemParam(int page, int rows) {
		System.out.println("进入ItemParamServiceImpl");
		EUDataGridResult dataGridResult=new EUDataGridResult();
		TbItemParamExample example=new TbItemParamExample();
		

		PageHelper.startPage(page, rows);
		//selectByExampleWithBLOBs()方法：解决数据库大文本无返回问题
		List<TbItemParam> list= itemParamMapper.selectByExampleWithBLOBs(example);
		//取出分页信息，就是取Total
		PageInfo<TbItemParam> pageInfo=new PageInfo<>(list);
		long total=pageInfo.getTotal();
		dataGridResult.setTotal(total);
		dataGridResult.setRows(list);
		return dataGridResult;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
