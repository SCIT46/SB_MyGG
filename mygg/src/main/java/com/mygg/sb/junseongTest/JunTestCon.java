package com.mygg.sb.junseongTest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class JunTestCon
	{
	    private final JunTestUserService userService;
	    
	    @GetMapping("/tt")
	    public String getMethodName() {
	    	userService.createUser("덕삼이", 2);
	    	return "/";
	    }
	    
	}
