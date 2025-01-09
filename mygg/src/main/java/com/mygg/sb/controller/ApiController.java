package com.mygg.sb.controller;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.mygg.sb.search.SearchBaseDTO;
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
    public ResponseEntity<MatchDTO> publicMatch(@PathVariable("matchId") String matchId) throws Exception {
        return ResponseEntity.ok(publicMatchService.getMatchInfo(matchId));

    }

    // 유저 전적 조회 API (전적갱신 버튼이 눌렸을 때 동작 1)
    @GetMapping(path = "/match/public/{puuid}")
    public ResponseEntity<List<MatchDTO>> userMatch(@PathVariable("puuid") String puuid) throws Exception {
        // TODO: 유저 전적 조회 로직 추가
        return ResponseEntity.ok(List.of(publicMatchService.getMatchInfo(puuid)));
    }

    // User(유저 정보제공) API
    @GetMapping(path = "/user/{name}/{tag}")
    @Transactional
    public ResponseEntity<UserDTO> user(@PathVariable("name") String name, @PathVariable("tag") String tag) throws Exception {
        return ResponseEntity.ok(userService.searchUser(name, tag));
    }

    // User 최신화 API (전적갱신 버튼이 눌렸을 때 동작 2)
    // @GetMapping(path = "/user/update/{puuid}")
    // @Transactional
    // public ResponseEntity<UserDTO> userUpdate(@PathVariable("puuid") String puuid) throws Exception {
    //     // TODO: 유저 최신 데이터 조회/저장 로직 추가
    //     return ResponseEntity.ok(userService.searchUser(puuid));
    // }

    // Item(아이템 전체 정보제공) API
    @GetMapping(path = "/item")
    public ResponseEntity<Map<String, ItemDTO>> item() throws Exception {
        return ResponseEntity.ok(itemService.getItem("all"));
    }

    // Item(아이템 정보제공) API
    @GetMapping(path = "/item/{id}")
    public ResponseEntity<Map<String, ItemDTO>> item(@PathVariable("id") String id) throws Exception {
        return ResponseEntity.ok(itemService.getItem(id));
    }

    // Champion(챔피언 전체 정보제공) API
    @GetMapping(path = "/champion")
    public ResponseEntity<Map<String, ChampionDTO>> champion() throws Exception {
        return ResponseEntity.ok(championService.getChampion("all"));
    }
    
    // Champion(챔피언 정보제공) API
    @GetMapping(path = "/champion/{id}")
    public ResponseEntity<Map<String, ChampionDTO>> champion(@PathVariable("id") String id) throws Exception {
        return ResponseEntity.ok(championService.getChampion(id));
    }

    // Rune(룬 전체 정보제공) API
    @GetMapping(path = "/runesReforged")
    public ResponseEntity<JSONObject> rune() throws Exception {
        return ResponseEntity.ok(runeService.getRuneDto());
    }

    // Search(검색 정보제공) API
    @GetMapping(path = "/search/{keyword}")
    public ResponseEntity<Map<String, List<? extends SearchBaseDTO>>> search(@PathVariable("keyword") String keyword) throws Exception {
        return ResponseEntity.ok(searchService.search(keyword));
    }
}