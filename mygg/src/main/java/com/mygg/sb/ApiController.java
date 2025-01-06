package com.mygg.sb;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mygg.sb.champion.ChampionDTO;
import com.mygg.sb.champion.ChampionService;
import com.mygg.sb.exception.RiotApiNotFound;
import com.mygg.sb.item.ItemDTO;
import com.mygg.sb.item.ItemService;
import com.mygg.sb.rune.RuneDTO;
import com.mygg.sb.rune.RuneService;
import com.mygg.sb.search.SearchService;
import com.mygg.sb.match.MatchDTO;
import com.mygg.sb.match.service.PublicMatchService;
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
    public MatchDTO publicMatch(@PathVariable("matchId") String matchId) {
        try{
            return publicMatchService.getMatchInfo(matchId);
        } catch (RiotApiNotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());    //404
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());    //500
        }
    }

    // User(유저 정보제공) API
    @GetMapping(path = "/user/{name}/{tag}")
    @Transactional
    public UserDTO user(@PathVariable("name") String name, @PathVariable("tag") String tag) {
        try{
            return userService.searchUser(name, tag);
        } catch (RiotApiNotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());    //404
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());    //500
        }
    }

    // Item(아이템 전체 정보제공) API
    @GetMapping(path = "/item")
    public Map<String, ItemDTO> item() {
        try{
            return itemService.getItem("all");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());    //500
        }
    }

    // Item(아이템 정보제공) API
    @GetMapping(path = "/item/{id}")
    public Map<String, ItemDTO> item(@PathVariable("id") String id) {
        try{
            return itemService.getItem(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());    //500
        }
    }

    // Champion(챔피언 전체 정보제공) API
    @GetMapping(path = "/champion")
    public Map<String, ChampionDTO> champion() {
        try{
            return championService.getChampion("all");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());    //500
        }
    }

    // Champion(챔피언 정보제공) API
    @GetMapping(path = "/champion/{id}")
    public Map<String, ChampionDTO> champion(@PathVariable("id") String id) {
        try{
            return championService.getChampion(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());    //500
        }
    }

    // Rune(룬 전체 정보제공) API
    @GetMapping(path = "/runesReforged")
    public JSONObject rune() {
        try{
            return runeService.getRuneDto();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());    //500
        }
    }

    // Search(검색 정보제공) API
    @GetMapping(path = "/search/{keyword}")
    public Map<String, List<? extends BaseDTO>> search(@PathVariable("keyword") String keyword) {
        try{
            return searchService.search(keyword);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());    //500
        }
    }
}