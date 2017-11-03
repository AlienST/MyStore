package com.store.controller;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.store.mapper.TbItemMapper;
import com.store.pojo.TbItem;
import com.store.pojo.TbItemExample;


public class TestPageHelper {
	/**
	 * 测试分页插件的可用性
	 * 注意：分页查询，不能对有查询条件的执行分页查询
	 */
	@Test
	public void TestPageHelper(){
		//创建一个Spring容器
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		//从容器中取出Mapper的代理对象
		TbItemMapper mapper= applicationContext.getBean(TbItemMapper.class);
		//执行查询，默认查询全部内容
		TbItemExample example=new TbItemExample();
		//执行分页处理,第一页开始，取前30个内容
		PageHelper.startPage(1, 10);
		List<TbItem> list= mapper.selectByExample(example);
		for (TbItem tbItem : list) {
			System.out.println("遍历item表"+tbItem.getTitle());
		}
		//取出分页信息，就是取Total
		PageInfo<TbItem> pageInfo=new PageInfo<>(list);
		long total=pageInfo.getTotal();
		System.out.println("表中共有商品的总数量："+total);
		
		
	}
}
