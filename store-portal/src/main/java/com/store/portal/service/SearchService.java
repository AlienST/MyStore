package com.store.portal.service;

import com.store.portal.pojo.SearchResult;

public interface SearchService {
	/**
	 * 搜索
	 * @param queryString 搜索条件
	 * @param page 当前页
	 * @return
	 */
	public SearchResult search(String queryString, int page);
}
