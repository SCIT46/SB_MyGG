package com.mygg.sb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mygg.sb.match.PrivateMatch;
import com.mygg.sb.match.PublicMatch;

@RestController
public class ApiController {
	@GetMapping(path="/api/match/private/{matchId}")
	public PrivateMatch privateMatch(@PathVariable String matchId) {
		return new PrivateMatch(matchId);
	}
	
	@GetMapping(path="/api/match/public/{matchId}")
	public PublicMatch publicMatch(@PathVariable String matchId) throws Exception{
		return new PublicMatch(matchId);
	}

	// @GetMapping(path="/api/user/{name}/{tag}")
	// public User user(@PathVariable String name, @PathVariable String tag) throws Exception{
	// 	return new User(name,tag);
	// }
}
