package com.mygg.sb.match.service;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mygg.sb.match.InfoDTO;
import com.mygg.sb.match.MatchDTO;
import com.mygg.sb.match.MetadataDTO;
import com.mygg.sb.match.repository.UserMatchesRepository;
import com.mygg.sb.statics.api.RiotApiClient;
import com.mygg.sb.statics.api.RiotSeasonConstants;
import com.mygg.sb.statics.util.JsonToDTOMapper;
import com.mygg.sb.user.UserRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
		
		public List<String> run(String name, String tag) throws Exception
			{
				//1. userDb에 저장된 id(index) 값을 받는다

				// 2. [미완]
				//	userMatch userId가 있는지 체크해서 index 제일 아래 것(제일최신) matchId를 갖고 온다.
				// 	없다면 user 정보를 갱신하는 작업을 진행한다
				//checkEntity();
				
				// 3.데이터 처리(puuid 필요)
				//List<String> matchIds = indexingData(name, tag);
				
				//return matchIds;
				return null;
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
		
		private List<MatchDTO> indexingData(String _name, String _tag) throws Exception
		{
			List<String> listUserMatches = new ArrayList<>();	// matchId들이 저장된 곳
			List<MatchDTO> listMatchDto = new ArrayList<>();	// 
			
			// puuid로 matchId를 뱉어낸다.
			String puuid = RiotApiClient.getPuuidNameAndTag(_name, _tag);
			String lastMatchId = "";
			int start = 0;
			int count = 100;
			int indexInList = -1;
			//long startTime = System.currentTimeMillis();
			String[] arrStr = new String[100];
			boolean isRoop = true;
			while(indexInList < 0 && isRoop)
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
						return null;
					}
				
				//  3-4) List에 [index]부터 [0]까지 저장(list가 최근 - 오래된)
				for(int i = (indexInList == -1? arrStr.length -1: 0); i >= 0; i--)
					{
						// Queue 필요할 듯?
						if(checkDataTime(listMatchDto, arrStr[i]) == false)
							return listMatchDto;
						
						listUserMatches.add(arrStr[i]);
					}
			}
			
			System.out.println("roop를 전부 돌았습니다.");
			
			return null;
		}
		
		private boolean checkDataTime(List<MatchDTO> list, String matchId) throws Exception
		{
			// matchId의 데이터를 체크해서 일정타임 안쪽인지 체크해보도록 하자!
			
			//1. 받은 matchId의 데이터를 받는다.
			MatchDTO match = getMatchInfo(matchId);
			//2. timeStamp를 체크한다.
			if(checkTimeStamp(match.getInfo().getGameStartTimestamp(),
							  RiotSeasonConstants.SEASON_2024_SPLIT3_START))
				{
					//3. 이번 시즌(true)라면 list에 저장한다.
					list.add(match);
					return true;
				}
			
			//4. 이번 시즌이 아니라면 false 반환한다
			return false;
		}
		
		private boolean checkTimeStamp(long time, long seasonStart)
		{
			// 현재부터 과거까지의 데이터를 조회할 때 일정 기간 안쪽인지 true | false를 반환하는 함수이다
			return time > seasonStart;
		}
		public MatchDTO getMatchInfo(String matchId) throws Exception
			{
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
	
//	    public List<String> getMatchIds(String puuid, long startTime, long endTime) {
//	        RestTemplate restTemplate = new RestTemplate();
//
//	        String url = BASE_URL + "?startTime=" + startTime + "&endTime=" + endTime + "&count=100&api_key=" + API_KEY;
//
//	        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class, puuid);
//
//	        if (response.getStatusCode().is2xxSuccessful()) {
//	            return response.getBody();
//	        } else {
//	            throw new RuntimeException("Failed to fetch match IDs: " + response.getStatusCode());
//	        }
//	    }
	}
