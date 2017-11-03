package com.store.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.store.portal.service.ContentService;

@Controller
public class IndexController {
	@Autowired
	private ContentService contentSerive;
	@RequestMapping("/index")
	public String showIndex(Model model){
		String json=contentSerive.getContentList(); 
		System.out.println("IndexController-返回的Json:"+json);
		model.addAttribute("ad1", json);
		return "index";
	}
}
