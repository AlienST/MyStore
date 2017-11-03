package com.store.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.store.common.utils.JsonUtils;
import com.store.mapper.TbItemCatMapper;
import com.store.pojo.TbItemCat;
import com.store.pojo.TbItemCatExample;
import com.store.pojo.TbItemCatExample.Criteria;
import com.store.rest.dao.JedisClient;
import com.store.rest.pojo.CatNode;
import com.store.rest.pojo.CatResult;
import com.store.rest.service.ItemCatService;

/**
 * 获取商品分类
 * @author 麦苗
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private TbItemCatMapper itemCatMapper;
	@Value("${INDEX_ITEMCAT_KEY}")
	private String INDEX_ITEMCAT_KEY;
	/**
	 * 获取商品分类服务
	 */
	@Override
	public CatResult getItemCatList() {
		CatResult catResult=new CatResult();
		/*
		//从redis缓存中读取
		try {
			String result = jedisClient.hget(INDEX_ITEMCAT_KEY, "商品分类");
			if (!StringUtils.isBlank(result)) {
				System.out.println("----商品分类有缓存");			
				List data=JsonUtils.jsonToList(result, catResult.getData().getClass());
				catResult.setData(data);
				return catResult;
			}
		} catch (Exception e) {
			System.out.println("从商品分类缓存中读取数据失败！");
		}	
		*/
		//查询商品分类列表
		catResult.setData(getCatList(0));	
		
		/*
		//存入redis缓存中
		try {
			System.out.println("将商品分类存入Redis缓存");
			String string = JsonUtils.objectToJson(catResult.getData());
			jedisClient.hset(INDEX_ITEMCAT_KEY, "商品分类", string);
		} catch (Exception e) {
			System.out.println("商品分类存入缓存失败！");
		}
		*/
		return catResult;
	}

	/**
	 * 查询分类列表的方法
	 * @param parentId
	 * @return
	 */
	private List<?> getCatList(long parentId){
		//创建查询条件
		TbItemCatExample example=new TbItemCatExample();
		Criteria criteria=  example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list= itemCatMapper.selectByExample(example);  //执行查询
		List data=new ArrayList<>();    //返回值List
		//向List中添加节点，添加计数器
		int count=0;
		
		for (TbItemCat tbItemCat : list) {
			//判断是否为父节点
			if (tbItemCat.getIsParent()) {		
				CatNode catNode=new CatNode();
				//返回的格式:  n : "<a href='/products/1.html'>图书、音像、电子书刊</a>"
				if (parentId==0) {
					catNode.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");	
				}else {
					catNode.setName(tbItemCat.getName());
				}
				catNode.setUrl("/products/"+tbItemCat.getId()+".html");
				catNode.setItem(getCatList(tbItemCat.getId()));
				data.add(catNode);
				count++;
				//只取前14个分类，超出的不要了
				if (parentId==0 && count>=14) {
					break;
				}
				//是叶子节点
			}else {
				data.add("/products/"+tbItemCat.getId()+".html|"+tbItemCat.getName());
			}
		}

		return data;
	}



}
