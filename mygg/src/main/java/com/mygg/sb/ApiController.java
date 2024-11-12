package com.mygg.sb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mygg.sb.match.PrivateMatch;
import com.mygg.sb.match.PublicMatch;

@RestController
public class ApiController {
	
	@GetMapping("/match/private")
	public PrivateMatch privateMatch() {
		return new PrivateMatch("id123");
	}
	
	@GetMapping("/match/public")
	public PublicMatch publicMatch() {
		return new PublicMatch("id123");
	}
}
