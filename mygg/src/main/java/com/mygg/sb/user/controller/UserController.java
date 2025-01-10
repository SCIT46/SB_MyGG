package com.mygg.sb.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mygg.sb.user.UserDTO;
import com.mygg.sb.user.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/user")
@Tag(name = "유저 Riot 정보 조회/갱신 API", description = "유저 Riot 정보 조회/갱신에 사용되는 API")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // User(유저 정보제공) API
    @GetMapping(path = "/{name}/{tag}")
    @Transactional
    public ResponseEntity<UserDTO> user(@PathVariable("name") String name, @PathVariable("tag") String tag) throws Exception {
        return ResponseEntity.ok(userService.searchUser(name, tag));
    }

    // User 최신화 API (전적갱신 버튼이 눌렸을 때 동작 2)
    // @GetMapping(path = "/update/{puuid}")
    // @Transactional
    // public ResponseEntity<UserDTO> userUpdate(@PathVariable("puuid") String puuid) throws Exception {
    //     // TODO: 유저 최신 데이터 조회/저장 로직 추가
    //     return ResponseEntity.ok(userService.searchUser(puuid));
    // }


    //User 최신화 API (전적갱신 버튼이 눌렸을 때 동작 2)
    // @GetMapping(path = "/update/{puuid}")
    // public ResponseEntity userUpdate(@PathVariable("puuid") String puuid) throws Exception {
    // // TODO: 유저 최신 데이터 조회/저장 로직 추가
    // return ResponseEntity.status(HttpStatus.CREATED);
    // }

}
