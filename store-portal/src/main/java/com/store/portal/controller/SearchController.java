package com.store.portal.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.store.portal.pojo.SearchResult;
import com.store.portal.service.SearchService;

@Controller
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	@RequestMapping(value="/search.html")
	public String search(@RequestParam(value="q", defaultValue="手机") String queryString,
			@RequestParam(value="page",defaultValue="1") Integer page,Model model){
		System.out.println("进入SearchController");
		//对传进来的中文参数进行转码！
		if (queryString != null) {
			try {
				queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		SearchResult searchResult = searchService.search(queryString, page);
		//向页面传递参数
		model.addAttribute("query", queryString);
		model.addAttribute("totalPages", searchResult.getPageCount());
		model.addAttribute("itemList", searchResult.getItemList());
		model.addAttribute("page", page);
		return "search";
	}
}
