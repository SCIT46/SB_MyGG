package com.mygg.sb.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mygg.sb.user.UserDTO;
import com.mygg.sb.user.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/user")
@Tag(name = "유저 Riot 정보 조회/갱신 API", description = "유저 Riot 정보 조회/갱신에 사용되는 API")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // User(유저 정보제공) API
    @Operation(summary = "User(유저 정보제공) API", description = "User(유저 정보제공) API")
    @GetMapping(path = "/{name}/{tag}")
    @Transactional
    public ResponseEntity<UserDTO> user(@PathVariable("name") String name, @PathVariable("tag") String tag) throws Exception {
        UserDTO dto = userService.searchUser(name, tag);
        // 민감정보 제외
        // dto.setLeagueId(null);
        // dto.setPuuid(null);
        // dto.setSummonerId(null);
        return ResponseEntity.ok(dto);
    }

    // User 최신화 API (전적갱신 버튼이 눌렸을 때 동작 2) DB Index로 조회
    @Operation(summary = "User(유저 정보 업데이트) API", description = "User(유저 정보 업데이트) API")
    @GetMapping(path = "/update/{index}")
    public ResponseEntity<?> userUpdate(@PathVariable("index") Long id) throws Exception {
        // TODO: 유저 최신 데이터 조회/저장 로직 추가
        userService.updateUser(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    //User 최신화 API (전적갱신 버튼이 눌렸을 때 동작 2) Puuid로 조회
    // @Operation(summary = "User(유저 정보 업데이트) API", description = "User(유저 정보 업데이트) API")
    // @GetMapping(path = "/update/{puuid}")
    // public ResponseEntity<?> userUpdate(@PathVariable("puuid") String puuid) throws Exception {
    //     // TODO: 유저 최신 데이터 조회/저장 로직 추가
    //     userService.updateUserByPuuid(puuid);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(null);
    // }

    

}
