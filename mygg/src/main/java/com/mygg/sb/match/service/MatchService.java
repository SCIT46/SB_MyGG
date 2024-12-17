package com.mygg.sb.match.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.mygg.sb.match.Entity.UserMatchEntity;
import com.mygg.sb.match.repository.UserMatchesRepository;
import com.mygg.sb.statics.api.RiotApiClient;
import com.mygg.sb.user.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Controller
@Slf4j
public class MatchService
	{
		private final UserMatchesRepository userMatchesRepo;
		private final UserRepository userRepository;
		
		List<String> listUserMatches = new ArrayList<>();
		
		public List<String> run(String name, String tag) throws Exception
			{
				//1. userDb에 저장된 id(index) 값을 받는다

				// 2. [미완]
				//	userMatch userId가 있는지 체크해서 index 제일 아래 것(제일최신) matchId를 갖고 온다.
				// 	없다면 user 정보를 갱신하는 작업을 진행한다
				//checkEntity();
				
				// 3.데이터 처리(puuid 필요)
				indexingData(name, tag);
				
				return listUserMatches;
			}
		
		private int indexOf(String[] array, String keyword) {
		    for (int i = 0; i < array.length; i++) 
		    {
		        if (array[i] != null && array[i].equals(keyword)) {
		            return i; // 단어를 찾으면 인덱스 반환
		        }
		    }
		    return -1; // 찾지 못한 경우 -1 반환
		}
	
		private boolean timeCheck(long startTime, float timeLimite)
		{
			//	3-3) 예외처리) 무한 루프 방지, 일정 시간 후 강제종료
			// timeCheck 해서 시간이 지났으면 true값 반환
			long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
			if(elapsedTime > timeLimite) 
				{
					System.out.println("리스트 찾기 시간초과, return");
					return true;
				}
			
			return false;
		}
		
		private boolean checkEntity()
		{
			Optional<UserMatchEntity> entity = userMatchesRepo.findById(1);
			
			if (entity.isEmpty())
				{
					// to do: 유저 갱신 작업 필요
					log.info("userMatchsRepo findById가 비어 있습니다");
					return false;
				}
			return false;
		}
		
		private String indexingData(String _name, String _tag) throws Exception
		{
			String puuid = RiotApiClient.getPuuidNameAndTag(_name, _tag);
			String lastMatchId = "";
			int start = 0;
			int count = 100;
			int indexInList = -1;
			//long startTime = System.currentTimeMillis();
			String[] arrStr = new String[100];
			while(indexInList < 0)
			{
				// 3. player의 최근 데이터 100개를 갖고 온다
				//	3-1) index 찾는다.
				//	3-2) 찾을 인덱스 100을 더한다.

				arrStr = RiotApiClient.getMatchList(puuid, start, count);
				indexInList = indexOf(arrStr, lastMatchId);
				start += 100;
				if(arrStr.length < 1) 
					{
						log.info("attStr이 비어 있습니다.");
						return "arrStr이 비어 있습니다.";
					}
				
				//  3-4) List에 [index]부터 [0]까지 저장(list가 최근-
				for(int i = (indexInList == -1? arrStr.length -1: 0); i >= 0; i--)
					{
						listUserMatches.add(arrStr[i]);
					}
				
			}
			

			return "";
		}
		
	}
