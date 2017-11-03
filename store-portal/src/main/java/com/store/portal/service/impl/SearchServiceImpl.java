package com.store.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.store.common.utils.HttpClientUtil;
import com.store.common.utils.StoreResult;
import com.store.portal.pojo.SearchResult;
import com.store.portal.service.SearchService;
@Service
public class SearchServiceImpl implements SearchService {
	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;

	@Override
	public SearchResult search(String queryString, int page) {
		//调用store-search服务;
		//请求的格式：search/query?q={查询条件}&page={page}&rows={rows}
		Map<String, String> param=new HashMap<>();   	//查询参数
		param.put("q", queryString); 
		param.put("page", page+"");
		try {
			//调用服务
			String json= HttpClientUtil.doGet(SEARCH_BASE_URL,param);
			//把字符串转化为java对象
			StoreResult storeResult = StoreResult.formatToPojo(json, SearchResult.class);
			if (storeResult.getStatus()==200) {
				SearchResult result = (SearchResult)storeResult.getData();
				return result;
			}
		} catch (Exception e) {
			System.out.println("json转化java对象失败！");
		}
		return null;
	}

}
