package com.mygg.sb.match.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mygg.sb.match.Entity.UserMatchEntity;
import com.mygg.sb.match.dto.UserMatchDTO;
import com.mygg.sb.match.repository.UserMatchesRepository;
import com.mygg.sb.statics.api.RiotApiClient;
import com.mygg.sb.user.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@Slf4j
public class MatchController
	{
		private final UserMatchesRepository userMatchesRepo;
		private final UserRepository		userRepository;
		
		//3. 없다면? false를 준다
		//4. api를 이용해서 최근 매치 100개를 받아온다.
		@PostMapping("api/match/tempUrl")
		public void run(@RequestParam Long id) throws Exception
		{
			//1. find User puuid
	
			
			// 2. UserMatches에서 유저의 최근 매치아이디를 갖고 온다.
			//	유저 조회할 때 뭘로 조회해야 하는가?
			// 		- 없다면? 
			//		- 있다면?
			// - service로 옮겨야 하는 부분
			Optional<UserMatchEntity> entity = userMatchesRepo.findById(1);
			
			if(entity.isEmpty())
				{
					log.info("userMatchsRepo findById가 비어 있습니다");
					return ;
				}
			
			String findingMatchId = "";
//			while(true)
//				{
//					
//					String[] matches= RiotApiClient.getMatchList(puuid, 0, 100);
//					for(int i = 0; i < matches.length; i++)
//						{
//							
//						}
//				}
			//RiotApiClient.nameTagToPid();
		}
	}
