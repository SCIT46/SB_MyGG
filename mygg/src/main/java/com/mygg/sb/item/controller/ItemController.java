package com.mygg.sb.item.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mygg.sb.item.ItemDTO;
import com.mygg.sb.item.ItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/item")
@Tag(name = "아이템 정보 관련 API", description = "아이템 정보 조회에 사용되는 API")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    // Item(아이템 전체 정보제공) API
    @Operation(summary = "Item(아이템 전체 정보제공) API", description = "Item(아이템 전체 정보제공) API")
    @GetMapping(path = {"", "/","/all"})
    public ResponseEntity<Map<String, ItemDTO>> item() throws Exception {
        return ResponseEntity.ok(itemService.getItem("all"));
    }

    // Item(아이템 정보제공) API
    @Operation(summary = "Item(아이템 정보제공) API", description = "Item(아이템 정보제공) API")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Map<String, ItemDTO>> item(@PathVariable("id") String id) throws Exception {
        return ResponseEntity.ok(itemService.getItem(id));
    }
}
