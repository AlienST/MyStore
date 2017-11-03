package com.store.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.common.pojo.EUTreeNode;
import com.store.mapper.TbItemCatMapper;
import com.store.pojo.TbItemCat;
import com.store.pojo.TbItemCatExample;
import com.store.pojo.TbItemCatExample.Criteria;
import com.store.service.ItemCatService;
/**
 * 商品分类
 * @author 麦苗
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	/**
	 * 获取商品分类列表
	 */
	@Override
	public List<EUTreeNode> getCatList(long parentId) {
		//创建查询条件
		TbItemCatExample example=new TbItemCatExample();
		Criteria  criteria= example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list=itemCatMapper.selectByExample(example);
		ArrayList<EUTreeNode> EUTreeList=new ArrayList<>();
		for (TbItemCat itemCat : list) {
			EUTreeNode treeNode=new EUTreeNode();
			treeNode.setId(itemCat.getId());      //放入把父节点的id
			treeNode.setText(itemCat.getName());  //放入父节点的名称
			treeNode.setState(itemCat.getIsParent()?"closed":"open");
			EUTreeList.add(treeNode);
		}
		return EUTreeList;	
	}

}
