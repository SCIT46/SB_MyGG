package com.mygg.sb.search.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mygg.sb.search.SearchBaseDTO;
import com.mygg.sb.search.SearchService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/search")
@Tag(name = "통합검색 API", description = "유저, 챔피언, 아이템 검색에 사용되는 API")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;
    
    // Search(검색 정보제공) API
    @Operation(summary = "Search(검색 정보제공) API", description = "Search(검색 정보제공) API")
    @GetMapping(path = "/{keyword}")
    public ResponseEntity<Map<String, List<? extends SearchBaseDTO>>> search(@PathVariable("keyword") String keyword) throws Exception {
        return ResponseEntity.ok(searchService.search(keyword));
    }
}
