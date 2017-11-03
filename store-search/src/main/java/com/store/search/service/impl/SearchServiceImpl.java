package com.store.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.search.dao.SearchDao;
import com.store.search.pojo.SearchResult;
import com.store.search.service.SearchService;
@Service
public class SearchServiceImpl implements SearchService {
	@Autowired
	private SearchDao searchDao;
	/**
	 * 提供搜索服务
	 */
	@Override
	public SearchResult search(String queryString, int page, int rows)throws Exception {
		SolrQuery solrQuery=new SolrQuery(); 	// 创建查询对象
		solrQuery.setQuery(queryString);      //设置查询条件
		solrQuery.setStart((page-1)*rows);         //设置分页
		solrQuery.setRows(rows);
		solrQuery.set("df", "item_keywords");  //设置默认搜索域
		solrQuery.setHighlight(true);  //设置高亮
		solrQuery.addHighlightField("item_title");
		solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
		solrQuery.setHighlightSimplePost("</em>");
		SearchResult search = searchDao.search(solrQuery); //执行查询
		//计算查询结果总页数
		long recordCount = search.getRecordCount();
		long pageCount = recordCount / rows;
		if (recordCount % rows > 0) {
			pageCount++;
		}
		search.setPageCount(pageCount);
		search.setCurPage(page);
		
		return search;
	}

}
