package com.mygg.sb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mygg.sb.match.PrivateMatch;
import com.mygg.sb.match.PublicMatch;

@RestController
public class ApiController {
	
	@GetMapping(path="/match/private")
	public PrivateMatch privateMatch(@PathVariable String matchId) {
		return new PrivateMatch(matchId);
	}
	
	@GetMapping(path="/match/public")
	public PublicMatch publicMatch(@PathVariable String matchId) {
		return new PublicMatch(matchId);
	}
}
