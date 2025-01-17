package com.mygg.sb.junseongTest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mygg.sb.match.service.PublicMatchService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class JunTestCon
	{
	    private final JunTestUserService userService;
	    
	    @GetMapping("/junTestUrl")
	    public String getMethodName(@RequestParam(name="name") String name,
	    							@RequestParam(name="age") int age) {
	    	userService.createUser("test", 200);
	    	userService.createUser(name, age);
	    	return "testPage";
	    }
	}
