package com.mygg.sb.match.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mygg.sb.match.MatchDTO;
import com.mygg.sb.match.entity.MMatchEntity;
import com.mygg.sb.match.service.PublicMatchService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/match")
@Tag(name = "매치 정보 조회/갱신 API", description = "유저별 매치 데이터(리스트) 조회/갱신에 사용되는 API")
@RequiredArgsConstructor
public class MatchController
	{
		private final PublicMatchService publicService;
		private final PublicMatchService publicMatchService;
		//private final PrivateMatchService privateMatchService;
		
		@GetMapping("/test/{name}/{tag}")
		@ResponseBody
		public List<MMatchEntity> getMethodName(@PathVariable(name="name") String name,
										  @PathVariable(name="tag") String tag) throws Exception 
		{
			//List<MatchDTO> matchesIds = publicService.run(name, tag);
			// matchesIdes[0]: 제일최근, amtchesides[..]: 제일 나중
			
			return publicService.run(name, tag);
		}
		

		// Private Match(사진에서 추출한 결과) 정보 호출 API
		// @GetMapping(path="/private/{matchId}")
		// public PrivateMatchService privateMatch(@PathVariable("matchId") String
		// matchId) {
		// privateMatchService = new PrivateMatchService(matchId);
		// return privateMatchService;
		// }

		// Public Match(API로부터 받아온 결과) API
		@GetMapping(path = "/public/{matchId}")
		public ResponseEntity<MatchDTO> publicMatch(@PathVariable("matchId") String matchId) throws Exception {
			return ResponseEntity.ok(publicMatchService.getMatchInfo(matchId));
		}

		// 유저 전적 조회 API (전적갱신 버튼이 눌렸을 때 동작 1)
		@GetMapping(path = "/public/{puuid}")
		public ResponseEntity<List<MatchDTO>> userMatch(@PathVariable("puuid") String puuid) throws Exception {
			// TODO: 유저 전적 조회 로직 추가
			return ResponseEntity.ok(List.of(publicMatchService.getMatchInfo(puuid)));
		}
		
	}
