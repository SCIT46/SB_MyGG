package com.mygg.sb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mygg.sb.champion.ChampionApi;
import com.mygg.sb.item.ItemApi;
import com.mygg.sb.match.PrivateMatch;
import com.mygg.sb.match.PublicMatch;
import com.mygg.sb.user.UserApi;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class ApiController {

	// Private Match(사진에서 추출한 결과) 정보 호출 API
	@GetMapping(path="/match/private/{matchId}")
	public PrivateMatch privateMatch(@PathVariable("matchId") String matchId) {
		return new PrivateMatch(matchId);
	}
	
	// Public Match(API로부터 받아온 결과) API
	@GetMapping(path="/match/public/{matchId}")
	public PublicMatch publicMatch(@PathVariable("matchId") String matchId) throws Exception{
		return new PublicMatch(matchId);
	}

	// User(유저 정보제공) API
	@GetMapping(path="/user/{name}/{tag}")
	public UserApi user(@PathVariable("name") String name, @PathVariable("tag") String tag) throws Exception{
		return new UserApi(name,tag);
	}

	//Item(아이템 정보제공) API
	@GetMapping(path="/item/")
	public ItemApi item() throws Exception{
		return new ItemApi();
	}
	@GetMapping(path="/item/{id}")
	public ItemApi item(@PathVariable("id") String id) throws Exception{
		return new ItemApi(id);
	}

	//Champion(챔피언 정보제공) API
	@GetMapping(path="/champion/{id}")
	public ChampionApi champion(@PathVariable("id") String id) throws Exception{
		return new ChampionApi(id);
	}
	
	//Search(검색 정보제공) API
	@GetMapping(path="/search/{keyword}")
	public SearchApi search(@PathVariable("keyword") String keyword) throws Exception{
		return new SearchApi(keyword);
	}
}

// URL 
// USERAPI -> Json 뿌려주고
// fatch -> 가공