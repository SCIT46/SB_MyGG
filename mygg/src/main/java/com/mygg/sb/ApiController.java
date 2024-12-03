package com.mygg.sb;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mygg.sb.champion.ChampDTO;
import com.mygg.sb.champion.ChampionService;
import com.mygg.sb.item.ItemDTO;
import com.mygg.sb.item.ItemService;
import com.mygg.sb.rune.RuneDTO;
import com.mygg.sb.rune.RuneService;
import com.mygg.sb.user.UserDTO;
import com.mygg.sb.user.UserService;

@RestController
@RequestMapping("/api")
public class ApiController {

    // @Autowired
    // private PrivateMatchService privateMatchService;
    // @Autowired
    // private PublicMatchService publicMatchService;
    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ChampionService championService;
    @Autowired
    private RuneService runeService;
    //@Autowired
    //private SearchService searchService;

    // Private Match(사진에서 추출한 결과) 정보 호출 API
    // @GetMapping(path="/match/private/{matchId}")
    // public PrivateMatchService privateMatch(@PathVariable("matchId") String matchId) {
    //     privateMatchService = new PrivateMatchService(matchId);
    //     return privateMatchService;
    // }
    
    // // Public Match(API로부터 받아온 결과) API
    // @GetMapping(path="/match/public/{matchId}")
    // public PublicMatchService publicMatch(@PathVariable("matchId") String matchId) throws Exception {
    //     //publicMatchService = new PublicMatchService(matchId);
    //     return publicMatchService.getMatchInfo(matchId);
    // }

    // User(유저 정보제공) API 
    @GetMapping(path="/user/{name}/{tag}")
    public UserDTO user(@PathVariable("name") String name, @PathVariable("tag") String tag) throws Exception {
        // userService = new UserService(name, tag);
        return userService.getUserInfo(name, tag);
    }

    //Item(아이템 정보제공) API
    @GetMapping(path="/item")
    public ArrayList<ItemDTO> items() throws Exception {
        // itemsService = new ItemsService();
        return itemService.getItems();
    }
    
    @GetMapping(path="/item/{id}")
    public ArrayList<ItemDTO> item(@PathVariable("id") String id) throws Exception {
        // itemService = new ItemService(id);
        return itemService.getItem(id);
    }

    //Champion(챔피언 정보제공) API
	  @GetMapping(path="/champion")
    public ArrayList<ChampDTO> champions() throws Exception {
        // championsService = new ChampionsService();
        return championService.getChampions();
    }

    @GetMapping(path="/champion/{id}")
    public ArrayList<ChampDTO> champion(@PathVariable("id") String id) throws Exception {
        // championService = new ChampionService(id);
        return championService.getChampion(id);
    }
    
    @GetMapping(path="/runesReforged")
    public JSONObject runes() throws Exception
    {
    	return runeService.getRuneDto();
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