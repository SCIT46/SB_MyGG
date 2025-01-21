package com.mygg.sb.analysis.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mygg.sb.analysis.AnalysisService;
import com.mygg.sb.analysis.ChampionAnalysis;
import com.mygg.sb.analysis.UserAnalysisDTO;
import com.mygg.sb.match.MatchDTO;
import com.mygg.sb.match.service.PublicMatchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/analysis")
public class AnalysisController {

    private final AnalysisService analysisService;
    private final PublicMatchService pmatchService;

    // 개인 통계 조회
    @GetMapping("/personal/{puuid}")
    public ResponseEntity<UserAnalysisDTO> personalAnalysis(@PathVariable(name="puuid") String puuid) throws Exception {
        return ResponseEntity.ok(analysisService.getUserAnalysis(puuid));
    }

    // test
    @GetMapping("/test/{puuid}")
    public ResponseEntity<List<Map<String, ChampionAnalysis>>> test(@PathVariable(name="puuid") String puuid) throws Exception {
        List<MatchDTO> list = pmatchService.getMatchDataInDB(puuid);
        return ResponseEntity.ok(analysisService.getChampionAnalysis(list, puuid));
    }
}
