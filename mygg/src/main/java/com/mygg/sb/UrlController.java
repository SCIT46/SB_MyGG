package com.mygg.sb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UrlController {
	@GetMapping("/main")
	@ResponseBody
	public String index(){
		return "index.html";
	}
	
	@GetMapping("/")	//root 도메인 접속시 리다이렉션
	public String root() {
		return "redirect:/main";
	}
}
