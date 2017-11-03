package com.store.service.impl;

import java.util.ArrayList;import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.common.pojo.EUTreeNode;
import com.store.common.utils.StoreResult;
import com.store.mapper.TbContentCategoryMapper;
import com.store.pojo.TbContentCategory;
import com.store.pojo.TbContentCategoryExample;
import com.store.pojo.TbContentCategoryExample.Criteria;
import com.store.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	@Autowired
	private TbContentCategoryMapper categoryMapper;
	/**
	 * 获取content分类树形数据
	 */
	@Override
	public List<EUTreeNode> getCategoryList(long parentId) {
		//根据parentId查询列表
		TbContentCategoryExample example=new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list= categoryMapper.selectByExample(example);
		List<EUTreeNode> resultList=new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			EUTreeNode treeNode=new EUTreeNode();
			treeNode.setId(tbContentCategory.getId());
			treeNode.setText(tbContentCategory.getName());
			treeNode.setState(tbContentCategory.getIsParent()?"closed":"open");
			resultList.add(treeNode);
		}		
		return resultList;
	}
	
	/**
	 * 添加ContentCategory
	 * @return 返回一个ContentCategory的pojo对象，用于显示子节点
	 * @parent，name  父节点id与新增的子节点名称, 返回主键Id
	 */
	@Override
	public StoreResult insertContentCategoey(long parentId, String name) {
		TbContentCategory contentCategory=new TbContentCategory();
		contentCategory.setName(name);
		contentCategory.setIsParent(false);
		contentCategory.setStatus(1);
		contentCategory.setParentId(parentId);
		contentCategory.setUpdated(new Date());
		contentCategory.setCreated(new Date());
		contentCategory.setSortOrder(1);
		long id= categoryMapper.insert(contentCategory);
		//查看父节点的idparent是否为true,如果不是true则改为true
		TbContentCategory parentCat= categoryMapper.selectByPrimaryKey(parentId);
		if (parentCat.getIsParent()) {
			parentCat.setIsParent(true);
			categoryMapper.updateByPrimaryKey(parentCat);  //跟新父节点
		}
		return StoreResult.ok(contentCategory);
	}

	
	
	/**
	 * @param parentId   子节点的父类ID
	 * @param id  分类的ID
	 * @return StoreResult
	 */
	@Override
	public StoreResult deleteContentCategory(long id) {
		System.out.println("进入service-delete");
		TbContentCategoryExample example=new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(id);
		categoryMapper.deleteByExample(example);
		categoryMapper.deleteByPrimaryKey(id);
		return StoreResult.ok();
	}

	
	/**
	 * 更新ContentCategory名字
	 * @param id  id
	 * @param name  重命名
	 * @return
	 */
	@Override
	public StoreResult updateContentCategory(long id, String name) {
		TbContentCategoryExample example=new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		TbContentCategory contentCategory=new TbContentCategory();
		contentCategory.setName(name);
		//只设定name的值，updateByExampleSelective，只更新不为空的值，数据库其他不改变
		categoryMapper.updateByExampleSelective(contentCategory, example);
		return StoreResult.ok();
	}

	
	
	
	
	
	
	
	
	
	
}
