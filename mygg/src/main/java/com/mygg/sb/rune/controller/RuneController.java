package com.mygg.sb.rune.controller;

import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mygg.sb.rune.RuneService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api")
@Tag(name = "룬 정보조회 관련 API", description = "룬 조회에 사용되는 API")
@RequiredArgsConstructor
public class RuneController {

    private final RuneService runeService;

    // Rune(룬 전체 정보제공) API
    @Operation(summary = "Rune(룬 전체 정보제공) API", description = "Rune(룬 전체 정보제공) API")
    @GetMapping(path ="/runesReforged")
    public ResponseEntity<JSONObject> rune() throws Exception {
        return ResponseEntity.ok(runeService.getRuneDto());
    }
}
