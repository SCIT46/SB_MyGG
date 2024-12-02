package com.mygg.sb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mygg.sb.champion.ChampionService;
import com.mygg.sb.champion.ChampionsService;
import com.mygg.sb.item.ItemService;
import com.mygg.sb.item.ItemsService;
import com.mygg.sb.match.PrivateMatchService;
import com.mygg.sb.match.PublicMatchService;
import com.mygg.sb.user.UserService;
//import com.mygg.sb.search.SearchService;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private PrivateMatchService privateMatchService;
    @Autowired
    private PublicMatchService publicMatchService;
    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemsService itemsService;
    @Autowired
    private ChampionService championService;
    @Autowired
    private ChampionsService championsService;
    //@Autowired
    //private SearchService searchService;

    // Private Match(사진에서 추출한 결과) 정보 호출 API
    @GetMapping(path="/match/private/{matchId}")
    public PrivateMatchService privateMatch(@PathVariable("matchId") String matchId) {
        privateMatchService = new PrivateMatchService(matchId);
        return privateMatchService;
    }
    
    // Public Match(API로부터 받아온 결과) API
    @GetMapping(path="/match/public/{matchId}")
    public PublicMatchService publicMatch(@PathVariable("matchId") String matchId) throws Exception {
        publicMatchService = new PublicMatchService(matchId);
        return publicMatchService;
    }

    // User(유저 정보제공) API 
    @GetMapping(path="/user/{name}/{tag}")
    public UserService user(@PathVariable("name") String name, @PathVariable("tag") String tag) throws Exception {
        userService = new UserService(name, tag);
        return userService;
    }

    //Item(아이템 정보제공) API
    @GetMapping(path="/item")
    public ItemsService items() throws Exception {
        itemsService = new ItemsService();
        return itemsService;
    }
    
    @GetMapping(path="/item/{id}")
    public ItemService item(@PathVariable("id") String id) throws Exception {
        itemService = new ItemService(id);
        return itemService;
    }

    //Champion(챔피언 정보제공) API
	@GetMapping(path="/champion/{id}")
    public ChampionsService champions() throws Exception {
        championsService = new ChampionsService();
        return championsService;
    }

    @GetMapping(path="/champion/{id}")
    public ChampionService champion(@PathVariable("id") String id) throws Exception {
        championService = new ChampionService(id);
        return championService;
    }
    
    //Search(검색 정보제공) API
    /*
    @GetMapping(path="/search/{keyword}")
    public SearchService search(@PathVariable("keyword") String keyword) throws Exception {
        searchService = new SearchService(keyword);
        return searchService;
    }
    */
}