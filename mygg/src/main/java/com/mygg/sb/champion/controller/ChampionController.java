package com.mygg.sb.champion.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mygg.sb.champion.ChampionDTO;
import com.mygg.sb.champion.ChampionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/champion")
@Tag(name = "챔피언 정보 관련 API", description = "챔피언 정보 조회에 사용되는 API")
@RequiredArgsConstructor
public class ChampionController {

    private final ChampionService championService;

    @Operation(summary = "Champion(챔피언 전체 정보제공) API", description = "Champion(챔피언 전체 정보제공) API")
    @GetMapping(path = {"", "/","/all"})
    public ResponseEntity<Map<String, ChampionDTO>> champion() throws Exception {
        return ResponseEntity.ok(championService.getChampion("all"));
    }
    
    @Operation(summary = "Champion(챔피언 정보제공) API", description = "Champion(챔피언 정보제공) API")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Map<String, ChampionDTO>> champion(@PathVariable("id") String id) throws Exception {
        return ResponseEntity.ok(championService.getChampion(id));
    }
}
