package com.store.service;

import java.util.List;

import com.store.common.pojo.EUTreeNode;

public interface ItemCatService {
	//获取树节点
	List<EUTreeNode> getCatList(long parentId );
}
