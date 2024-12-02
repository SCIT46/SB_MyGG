package com.mygg.sb.match;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.mygg.sb.statics.api.RiotApiClient;
import com.mygg.sb.statics.util.JsonToDtoMapper;

import lombok.Getter;
import lombok.Setter;   
 
// riot api로 부터 받아온 match JSON 파일을 DB에 저장(matchId / match.JSON)
// /api/user/{userId} -> DB에 userId.JSON이 있는가? DB에서 JSON 불러오기 : riot API에서 JSON 불러오기 / DB에 기록 -> JSON parsing / return 
// /api/match/public/{matchId} -> DB에 matchId.JSON이 있는가? DB에서 JSON 불러오기 : riot API에서 JSON 불러오기 / DB에 기록 -> JSON parsing / return
@Getter
//@Setter
@Service
public class PublicMatchService
	{
		// 매치 내 플레이어 식별자(participants) 를 저장해줄 List
		//ArrayList<String> player;
		//List<String> participants; 		//player UID들
		//List<ParticipantsDto> playerDto;
		
		MetadataDto metadata;
		InfoDto info;
		
		public PublicMatchService(String matchId) throws Exception
			{
				// matchId로 매치 정보(JSONObject) 변환							// String 형태의 JSON 데이터를 JSONObject(HashMap)형 jsonObject로 변환
				JSONObject jsonObject = RiotApiClient.getMatchInfo(matchId);	//(JSONObject) parser.parse(matchJSON);
				JsonToDtoMapper mapper = new JsonToDtoMapper();
				
				// jsonObject의 JSON Key값으로 모든 데이터 조회
				for (Object key : jsonObject.keySet())
					{
						// key 값으로 받은 데이터를 value에 저장
						Object value = jsonObject.get(key);
						JSONObject jsonObj = (JSONObject) value;

						// Extract Player Identity KEY
						// JSON파일 내부의 metadata, info 데이터 불러오기
						
						if (key.equals("metadata")) metadata = mapper.mapToDto(jsonObj, MetadataDto.class);
						if (key.equals("info")) info = mapper.mapToDto(jsonObj, InfoDto.class);
					}
			}
		
		private void searchParticipants(JSONObject jsonObj)
		{
			// 게임에 참여한 플레이어들 정보를 받아서 정리하는 함수
			
			JSONArray participants = (JSONArray) jsonObj.get("participants");

			// List 인덱스마다 조회
			for (int i = 0; i < participants.size(); i++)
				{
					// participants: 게임 참자가들
					// Player별 데이터를 조회하기 위해 info 내의 participants(플레이어들) List를 따로 빼냄
					JSONObject partPlayer = (JSONObject) participants.get(i);
					insertParticipantsDto(partPlayer);
				}
		}
		
		private void insertParticipantsDto(JSONObject _partPlayer)
		{
//			ParticipantsDto _participantsDto = new ParticipantsDto();
			
//			_participantsDto.setLane		 	((String) _partPlayer.get("lane")); // lane
//			_participantsDto.setUserName	    ((String) _partPlayer.get("riotIdGameName"));
//			_participantsDto.setRiotIdGameName  ((String) _partPlayer.get("riotIdGameName") + '#' +
//												 (String) _partPlayer.get("riotIdTagline"));
//			_participantsDto.setSummonerLevel   ((Long) _partPlayer.get("summonerLevel"));
//			_participantsDto.setGoldPerMinute	((Double) _partPlayer.get("goldPerMin"));
//			_participantsDto.setChampionId		(((Long) _partPlayer.get("championId")).intValue());
//			_participantsDto.setChampionName	((String) _partPlayer.get("championName"));
//			_participantsDto.setChampLevel		(((Long) _partPlayer.get("champLevel")).intValue());
//			_participantsDto.setItem0			(((Long) _partPlayer.get("item0")).intValue());
//			_participantsDto.setItem1			(((Long) _partPlayer.get("item1")).intValue());
//			_participantsDto.setItem2			(((Long) _partPlayer.get("item2")).intValue());
//			_participantsDto.setItem3			(((Long) _partPlayer.get("item3")).intValue());
//			_participantsDto.setItem4			(((Long) _partPlayer.get("item4")).intValue());
//			_participantsDto.setItem5			(((Long) _partPlayer.get("item5")).intValue());
//			_participantsDto.setItem6			(((Long) _partPlayer.get("item6")).intValue());
//			_participantsDto.setKills			(((Long)_partPlayer.get("kills")).intValue());
//			_participantsDto.setDeaths			(((Long)_partPlayer.get("deaths")).intValue());
//			_participantsDto.setAssists			(((Long)_partPlayer.get("assists")).intValue());
//			_participantsDto.setKda				(((float)(_participantsDto.getKills() + _participantsDto.getAssists()) 
//														 / _participantsDto.getDeaths()));
//			_participantsDto.setVisionScore		(((Long)_partPlayer.get("visionScore")).intValue());
//			_participantsDto.setVisionWardsBoughtInGame(((Long)_partPlayer.get("visionWardsBoughtInGame")).intValue());
//			_participantsDto.setWardsPlaced		(((Long)_partPlayer.get("wardsPlaced")).intValue());
//			_participantsDto.setWin				(((boolean)_partPlayer.get("win")));
//			
//			_participantsDto.setTotalDamageDealtToChampions	(((Long)_partPlayer.get("totalDamageDealtToChampions")).intValue());		
//			_participantsDto.setTotalDamageTaken			(((Long)_partPlayer.get("totalDamageTaken")).intValue());
//			_participantsDto.setSummoner1Id					(((Long)_partPlayer.get("summoner1Id")).intValue());
//			_participantsDto.setSummoner2Id					(((Long)_partPlayer.get("summoner2Id")).intValue());
//			
//			PerksDto perksDto = new PerksDto();
//			perksDto.insertIntoPerksDto((JSONObject)_partPlayer.get("perks"));
//			_participantsDto.setPerkDto(perksDto);
			
			//_participantsDto.setPerkDto						((PerksDto)_partPlayer.get("perks"));
			// 룬 넣는 거 코드 추가 필요
//			_participantsDto.insert
			// 깃 테스트중
			
			// -------------- 테스트 코드 2, gson을 이용한 DTO 자동바인딩
			JsonToDtoMapper mapper = new JsonToDtoMapper();
			ParticipantsDto _participantsDto = mapper.mapToDto(_partPlayer, ParticipantsDto.class);
			
			//playerDto.add(_participantsDto);
			
			System.out.println(_participantsDto);
		} 

	}
