package com.mygg.sb.match.service;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.mygg.sb.match.InfoDTO;
import com.mygg.sb.match.MatchDTO;
import com.mygg.sb.match.MetadataDTO;
import com.mygg.sb.match.repository.UserMatchesRepository;
import com.mygg.sb.statics.api.RiotApiClient;
import com.mygg.sb.statics.api.RiotSeasonConstants;
import com.mygg.sb.statics.util.JsonToDTOMapper;
import com.mygg.sb.statics.util.DateTimeUtils;

import com.mygg.sb.user.UserRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
// riot api로 부터 받아온 match JSON 파일을 DB에 저장(matchId / match.JSON)
// /api/user/{userId} -> DB에 userId.JSON이 있는가? DB에서 JSON 불러오기 : riot API에서 JSON 불러오기 / DB에 기록 -> JSON parsing / return 
// /api/match/public/{matchId} -> DB에 matchId.JSON이 있는가? DB에서 JSON 불러오기 : riot API에서 JSON 불러오기 / DB에 기록 -> JSON parsing / return
@Getter
@RequiredArgsConstructor
@Service
@Slf4j
public class PublicMatchService
	{
		// 매치 내 플레이어 식별자(participants) 를 저장해줄 List
		//ArrayList<String> player;
		//List<String> participants; 		//player UID들
		//List<ParticipantsDto> playerDto;
		
		private final UserMatchesRepository userMatchesRepo;
		private final UserRepository userRepository;
		
		private final int count = 100;
		private final int limitRequestForSecond = 20;	// 초당 요청제한 갯수(데이터 크기X, 데이터 요청임)
		private final int limitRequestFor2Min = 100;	// 2분당 요청제한 갯수(데이터 크기X, 데이터 요청임)
		
		public List<MatchDTO> run(String name, String tag) throws Exception
			{
				// 테스트 코드
				return indexingData(name, tag);
			}

		
		private List<MatchDTO> indexingData(String _name, String _tag) throws Exception
		{
			List<String> listUserMatches = new ArrayList<>();	// matchId들이 저장된 곳
			List<MatchDTO> listMatchDto = new ArrayList<>();	// 매치 아이디로 DTO들 저장하는 곳 
			String[] arrStr = new String[100];

			String puuid = RiotApiClient.getPuuidNameAndTag(_name, _tag);
			String lastMatchId = "";
			int start = 0;
			int indexInList = -1;
			int currentRequestCnt = 0;

			// 매치 데이터에 기간을 두고, 그 기간 안의 데이터가 100개인 경우 게속 데이터를 뽑아낸다.
			boolean isRoopEnd = false;
			int nullIdx = -1;
			while(arrStr.length > 99)
			{
				// 3. player의 최근 데이터 100개를 갖고 온다
				//	3-1) index 찾는다.
				//	3-2) 찾을 인덱스 100을 더한다.
				currentRequestCnt++;
				arrStr = RiotApiClient.getMatchList(puuid, start, count,
						RiotSeasonConstants.getCurrentYearSeasonStartTimeStamp(), RiotSeasonConstants.getNowEndSeasonTimeStamp());

				// DB에 접근해서 lastMatchId를 갖고 온다. 없다면 pass
				//indexInList = indexOf(arrStr, lastMatchId);
				start += 100;
				
				//  3-4) List에 [index]부터 [0]까지 저장(list가 최근 - 오래된)
				for(int i = 0; i < arrStr.length; i++)
					{
						listUserMatches.add(arrStr[i]);
						System.out.println(arrStr[i]);
					}
			}
			
			for(int i = 0; i < ((nullIdx < 0)? listUserMatches.size(): nullIdx); i++)
				{
					currentRequestCnt++;
					MatchDTO dto = getMatchInfo(listUserMatches.get(i));
					if(dto != null)
						{
							System.out.println("dddddddddddddddddddddddddddddddddddddd");
							System.out.println(dto.getInfo().getMapId());
							listMatchDto.add(dto);
						}
					
					if(checkRequestSecondLimit(currentRequestCnt)) 
						{
							log.info("sleep");
							//Thread.sleep(1000l);
							currentRequestCnt = 0;
						};
				}

			return listMatchDto;
		}
		
		public MatchDTO getMatchInfo(String matchId) throws Exception
			{
				// matchid를 받아서 그 매치의 정보를 받아오는 함수
				MetadataDTO metadata = new MetadataDTO();
				InfoDTO info = new InfoDTO();

				// matchId로 매치 정보(JSONObject) 변환							// String 형태의 JSON 데이터를 JSONObject(HashMap)형 jsonObject로 변환
				JSONObject jsonObject = RiotApiClient.getMatchInfo(matchId);	//(JSONObject) parser.parse(matchJSON);
				JsonToDTOMapper mapper = new JsonToDTOMapper();
				
				// jsonObject의 JSON Key값으로 모든 데이터 조회
				for (Object key : jsonObject.keySet())
					{
						// key 값으로 받은 데이터를 value에 저장
						Object value = jsonObject.get(key);
						JSONObject jsonObj = (JSONObject) value;

						// Extract Player Identity KEY
						// JSON파일 내부의 metadata, info 데이터 불러오기
						
						if (key.equals("metadata")) metadata = mapper.mapToDto(jsonObj, MetadataDTO.class);
						if (key.equals("info")) info = mapper.mapToDto(jsonObj, InfoDTO.class);
					}

				MatchDTO result = new MatchDTO();
				result.setMetadata(metadata);
				result.setInfo(info);

				return result;
			}
		
		private int indexOf(String[] array, String keyword) {
			// Index Of: 필요: 유저 데이터가 이미 조회가 된 상태에서
			// 일정 데이터 ~ 마지막 로딩 데이터 사이를 조회하기 위해 만든 함수
		    for (int i = 0; i < array.length; i++) 
		    {
		        if (array[i] != null && array[i].equals(keyword)) {
		            return i; // 단어를 찾으면 인덱스 반환
		        }
		    }
		    return -1; // 찾지 못한 경우 -1 반환
		}
		
		private boolean checkTimeStamp(long time, long seasonStart)
		{
			// 일정 기간 안쪽인지 true | false를 반환하는 함수이다
			return time > seasonStart;
		}
		
		private boolean checkRequestSecondLimit(int curRequest) throws InterruptedException
		{
			// Request 요청제한을 넘었는지 체크한다.
			// 넘었으면 True, 아니라면 false
			if(limitRequestForSecond-1 <= curRequest) return true;

			return false;
		}
	}
