package com.store.search.service;

import com.store.search.pojo.SearchResult;

public interface SearchService {
	public SearchResult search(String queryString,int page,int rows)throws Exception;
}
