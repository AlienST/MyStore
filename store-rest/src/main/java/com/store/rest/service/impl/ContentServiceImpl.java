package com.store.rest.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.store.common.pojo.EUDataGridResult;
import com.store.common.utils.JsonUtils;
import com.store.common.utils.StoreResult;
import com.store.mapper.TbContentMapper;
import com.store.pojo.TbContent;
import com.store.pojo.TbContentExample;
import com.store.pojo.TbContentExample.Criteria;
import com.store.rest.dao.JedisClient;
import com.store.rest.service.ContentService;
@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private TbContentMapper contentMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	
	/**
	 * 根据叶子节点查询内容分类的具体信息
	 * @param id 
	 */
	@Override
	public List<TbContent> getContentList(long contentCid) {
		//注意：缓存的添加,不能影响正常的业务逻辑(要正常走下去)
		try {
			System.out.println("进入Redis缓存中，正在取数据");
			String result = jedisClient.hget(INDEX_CONTENT_REDIS_KEY, contentCid+"");
			//判断是否为空,若不为空，则：
			if (!StringUtils.isBlank(result)) {
				//把取出来的字符串转化为list
				System.out.println("redis有缓存");
				List<TbContent> resultList = JsonUtils.jsonToList(result, TbContent.class);
				return  resultList;
			}
		} catch (Exception e) {
			System.out.println("redis中没有缓存！");
		}
		
		
		TbContentExample example=new TbContentExample();
		Criteria criteria= example.createCriteria();
		criteria.andCategoryIdEqualTo(contentCid);
		List<TbContent> list= contentMapper.selectByExample(example);	
		
		
		//向缓存中添加内容
		try {
			System.out.println("进入Redis缓存中，正在保存数据");
			//先把List的转化为字符串
			String cacheString = JsonUtils.objectToJson(list);
			System.out.println("打印转化的cacheString："+cacheString);
			jedisClient.hset(INDEX_CONTENT_REDIS_KEY, contentCid + "", cacheString);
		} catch (Exception e) {
			System.out.println("redis缓存存入失败！");
		}
		
		return list;
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
		return StoreResult.ok();
	}

}
