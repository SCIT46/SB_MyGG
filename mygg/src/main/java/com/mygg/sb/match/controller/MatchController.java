package com.mygg.sb.match.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mygg.sb.match.MatchDTO;
import com.mygg.sb.match.service.PublicMatchService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/match")
@Tag(name = "매치 정보 조회/갱신 API", description = "유저별 매치 데이터(리스트) 조회/갱신에 사용되는 API")
@RequiredArgsConstructor
public class MatchController
	{
		private final PublicMatchService publicService;
		// private final PrivateMatchService privateMatchService;

		@Operation(summary = "DB에서 match Data 꺼내서 보여준다", description = "name: 유저이름, tag: 유저태그, page: 몇 번째 페이지 데이터")
		@GetMapping("/{name}/{tag}")
		public ResponseEntity<List<MatchDTO>> getMethodName(@PathVariable(name = "name") String name,
															@PathVariable(name = "tag") String tag, 
															@PageableDefault(page = 0, size = 20, sort = {"matchId"}, direction = Sort.Direction.DESC) Pageable page) throws Exception
			{
				return publicService.findMatchDataInDB(name, tag, page);
			}

		@Operation(summary = "Riot Api에 요청하여 match DB를 최신화한다", description = "name: 유저이름, tag: 유저태그")
		@GetMapping("/updateMatch/{name}/{tag}")
		public ResponseEntity<String> updateMatchDataForAPI(@PathVariable(name = "name") String name,
															@PathVariable(name = "tag") String tag) throws Exception
			{
				return publicService.updateMatchDataForAPI(name, tag);
			}

//		// Public Match(API로부터 받아온 결과) API(안 씀)
//		@Operation(summary = "Public Match(API로부터 받아온 결과) API", description = "Public Match(API로부터 받아온 결과) API")
//		@GetMapping(path = "/public/matchId/{matchId}")
//		public ResponseEntity<MatchDTO> publicMatch(@PathVariable("matchId") String matchId) throws Exception
//			{
//				return ResponseEntity.ok(publicService.changeJSONToDTOMatchData(matchId));
//			}
//
//		// 유저 전적 조회 API (전적갱신 버튼이 눌렸을 때 동작 1)(안씀)
//		@Operation(summary = "유저 전적 조회 API (전적갱신 버튼이 눌렸을 때 동작 1)(안 씀)", description = "유저 전적 조회 API (전적갱신 버튼이 눌렸을 때 동작 1)")
//		@GetMapping(path = "/public/puuid/{puuid}")
//		public ResponseEntity<List<MatchDTO>> userMatch(@PathVariable("puuid") String puuid) throws Exception
//			{
//				return ResponseEntity.ok(List.of(publicService.changeJSONToDTOMatchData(puuid)));
//			}

	}
