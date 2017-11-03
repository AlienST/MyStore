package com.store.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.store.common.pojo.EUDataGridResult;
import com.store.common.utils.HttpClientUtil;
import com.store.common.utils.StoreResult;
import com.store.mapper.TbContentMapper;
import com.store.pojo.TbContent;
import com.store.pojo.TbContentExample;
import com.store.pojo.TbContentExample.Criteria;
import com.store.service.ContentService;
@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private TbContentMapper contentMapper;
	@Value("${JEDIS_BASE_URL}")
	private String JEDIS_BASE_URL;
	@Value("${JEDIS_CONTENT_SYNC_URL}")
	private String JEDIS_CONTENT_SYNC_URL;
	
	
	/**
	 * 根据叶子节点查询内容分类的具体信息
	 * @param id 
	 */
	@Override
	public EUDataGridResult getContentList(long parentId,int page, int rows) {
		EUDataGridResult dataGridResult=new EUDataGridResult();
		TbContentExample example=new TbContentExample();
		Criteria criteria= example.createCriteria();
		criteria.andCategoryIdEqualTo(parentId);
		PageHelper.startPage(page, rows);
		List<TbContent> list= contentMapper.selectByExample(example);
		PageInfo<TbContent> pageInfo=new PageInfo<>(list);
		long total=pageInfo.getTotal();
		dataGridResult.setTotal(total);
		dataGridResult.setRows(list);
		return dataGridResult;
	}
	
	/**
	 * 保存content
	 * @return
	 */
	@Override
	public StoreResult saveContent(TbContent content) {
		content.setUpdated(new Date());
		content.setCreated(new Date());
		contentMapper.insert(content);
		try {
			HttpClientUtil.doGet(JEDIS_BASE_URL+JEDIS_CONTENT_SYNC_URL+"/"+content.getCategoryId());
			System.out.println("同步更新redis缓存");
		} catch (Exception e) {
			System.out.println("同步更新redis缓存失败");
		}
		return StoreResult.ok();
	}

	/**
	 * 修改content信息
	 * @param content
	 * @return
	 */
	@Override
	public StoreResult contentEdit(TbContent content) {
		content.setUpdated(new Date());
		content.setCreated(new Date());
		TbContentExample example=new TbContentExample();
		Criteria criteria= example.createCriteria();
		contentMapper.updateByPrimaryKeySelective(content);
		try {
			HttpClientUtil.doGet(JEDIS_BASE_URL+JEDIS_CONTENT_SYNC_URL+"/"+content.getCategoryId());
			System.out.println("同步更新redis缓存");
		} catch (Exception e) {
			System.out.println("同步更新redis缓存失败");
		}
		return StoreResult.ok();
	}


	/**
	 * 删除content
	 * @return
	 */
	@Override
	public StoreResult deleteContent(long id) {
		int result=contentMapper.deleteByPrimaryKey(id);
		System.out.println("删除成功的int:"+result);
		try {
			HttpClientUtil.doGet(JEDIS_BASE_URL+JEDIS_CONTENT_SYNC_URL+"/"+id);
			System.out.println("同步更新redis缓存");
		} catch (Exception e) {
			System.out.println("同步更新redis缓存失败");
		}
		return StoreResult.ok();
	}

}
