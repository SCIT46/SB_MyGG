package com.mygg.sb;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mygg.sb.champion.ChampionDTO;
import com.mygg.sb.champion.ChampionService;
import com.mygg.sb.item.ItemDTO;
import com.mygg.sb.item.ItemService;
import com.mygg.sb.rune.RuneDTO;
import com.mygg.sb.rune.RuneService;
import com.mygg.sb.search.SearchService;
import com.mygg.sb.match.MatchDTO;
import com.mygg.sb.match.PublicMatchService;
import com.mygg.sb.user.UserDTO;
import com.mygg.sb.user.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "유저 전적검색 관련 API", description = "유저 전적검색에 사용되는 API")
public class ApiController {

    // @Autowired
    // private PrivateMatchService privateMatchService;
    @Autowired
    private PublicMatchService publicMatchService;
    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ChampionService championService;
    @Autowired
    private RuneService runeService;
    @Autowired
    private SearchService searchService;

    // Private Match(사진에서 추출한 결과) 정보 호출 API
    // @GetMapping(path="/match/private/{matchId}")
    // public PrivateMatchService privateMatch(@PathVariable("matchId") String
    // matchId) {
    // privateMatchService = new PrivateMatchService(matchId);
    // return privateMatchService;
    // }

    // Public Match(API로부터 받아온 결과) API
    @GetMapping(path = "/match/public/{matchId}")
    public MatchDTO publicMatch(@PathVariable("matchId") String matchId) throws Exception {
        return publicMatchService.getMatchInfo(matchId);
    }

    // User(유저 정보제공) API
    @GetMapping(path = "/user/{name}/{tag}")
    @Transactional
    public UserDTO user(@PathVariable("name") String name, @PathVariable("tag") String tag) throws Exception {
        // DB에 저장된 유저 정보를 받아오기
        UserDTO user = userService.readOne(name, tag);
        if (user == null) {
            // DB에 유저 정보가 없으면 API로부터 유저 정보를 받아와 DB에 저장
            user = userService.getUserInfo(name, tag);
            userService.create(user);

            return user;
        }
        // 유저 검색 횟수 증가
        user.setSearchCount(user.getSearchCount() + 1);
        userService.update(user);
        // DB에 저장된 유저 정보를 반환
        return user;
    }

    // Item(아이템 전체 정보제공) API
    @GetMapping(path = "/item")
    public Map<String, ItemDTO> items() throws Exception {
        return itemService.getItems();
    }

    // Item(아이템 정보제공) API
    @GetMapping(path = "/item/{id}")
    public Map<String, ItemDTO> item(@PathVariable("id") String id) throws Exception {
        // itemService = new ItemService(id);
        return itemService.getItem(id);
    }

    // Champion(챔피언 전체 정보제공) API
    @GetMapping(path = "/champion")
    public Map<String, ChampionDTO> champions() throws Exception {
        // championsService = new ChampionsService();
        return championService.getChampions();
    }

    // Champion(챔피언 정보제공) API
    @GetMapping(path = "/champion/{id}")
    public Map<String, ChampionDTO> champion(@PathVariable("id") String id) throws Exception {
        // championService = new ChampionService(id);
        return championService.getChampion(id);
    }

    @GetMapping(path = "/runesReforged")
    public JSONObject runes() throws Exception {
        return runeService.getRuneDto();
    }
    // Search(검색 정보제공) API

    @GetMapping(path = "/search/{keyword}")
    public Map<String, List<? extends BaseDTO>> search(@PathVariable("keyword") String keyword) throws Exception {
        return searchService.search(keyword);
    }
}