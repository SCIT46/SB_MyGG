package com.mygg.sb.match.service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mygg.sb.match.MatchInfoDTO;
import com.mygg.sb.match.MatchDTO;
import com.mygg.sb.match.MetadataDTO;
import com.mygg.sb.match.entity.MMatchEntity;
import com.mygg.sb.match.entity.MMatchInfoEntity;
import com.mygg.sb.match.entity.MMetadataEntity;
import com.mygg.sb.match.repository.MMatchesRepository;
import com.mygg.sb.match.repository.UserMatchesRepository;
import com.mygg.sb.statics.api.RiotApiClient;
import com.mygg.sb.statics.api.RiotSeasonConstants;
import com.mygg.sb.statics.util.DateTimeUtils;
import com.mygg.sb.statics.util.JsonToDTOMapper;
import com.mygg.sb.user.UserDTO;
import com.mygg.sb.user.UserEntity;
import com.mygg.sb.user.UserRepository;
import com.mygg.sb.user.UserService;

import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
		private final MMatchesRepository mMatchesRepository;
		private final UserService	userService;
		private final ModelMapper modelMapper;
		
		private final int count = 5;					// api에 요청할 찾을 데이터 수
		private final int limitRequestForSecond = 20;	// 초당 요청제한 갯수(데이터 크기X, 데이터 요청임)
		private final int limitRequestFor2Min = 100;	// 2분당 요청제한 갯수(데이터 크기X, 데이터 요청임)
		
		public List<MMatchEntity> run(String name, String tag) throws Exception
			{
				// 테스트 코드
				return indexingData(name, tag);
			}
	
		private List<MMatchEntity> indexingData(String _name, String _tag) throws Exception
		{
			//1. DB내의 마지막 매치 데이터를 조회한다.
			//2. api에게 매치데이터 100개를 받아온다.
			//3. 마지막 매치데이터 ~ api의 매치데이터의 데이터를 받아온다.
			//	- DB내의 매치데이터가 없다면 전부 조회한다.
			List<String> listUserMatches = new ArrayList<>();	// matchId들이 저장된 곳
			List<MatchDTO> listMatchDto = new ArrayList<>();	// 매치 아이디로 DTO들 저장하는 곳 
			String[] arrStr = new String[100];					// 매치 ID 저장할 곳(KR_...)

			String puuid = RiotApiClient.getPuuidNameAndTag(_name, _tag);
			String lastMatchId = "";		// 마지막 matchID
			int start = 0;					// 찾기 시작하는 위치 
			int indexInList = -1;			// 리스트 내에서 DB에 있는 마지막 DB
			int currentRequestCnt = 0;		// 

			// --------------------- api로부터 데이터 탐색 --------------------------------------------------------------
			// 종료조건:
			// 		- 매치 데이터에 기간을 두고, 그 기간 안의 데이터가 100개가 아닌 경우 혹은
			// 		  인덱스가 발견된 경우에는 루프를 종료한다
			int nullIdx = -1;
			while(arrStr.length > 99 && nullIdx == -1)
			{
				currentRequestCnt++;
				
				// arrStr: 일정 기간 내에 100개의 게임 매치ID를 갖고 온다
				arrStr = RiotApiClient.getMatchList(puuid, start, count,
						RiotSeasonConstants.getCurrentYearSeasonStartTimeStamp(), RiotSeasonConstants.getNowEndSeasonTimeStamp());

				// DB에 접근해서 lastMatchId를 갖고 온다. 없다면 pass
				// indexInList = indexOf(arrStr, lastMatchId);
				start += count;
				
				//  3-4) List에 [index]부터 [0]까지 저장(list가 최근 - 오래된)
				for(int i = 0; i < arrStr.length; i++)
					{
						listUserMatches.add(arrStr[i]);
					}
			}
			
			// -------------------------- 데이터 가공 ---------------------------------------------------------------------
//			for(int i = 0; i < ((nullIdx < 0)? listUserMatches.size(): nullIdx); i++)
//				{
//					currentRequestCnt++;
//					MatchDTO dto = getMatchInfo(listUserMatches.get(i));
//					if(dto.getInfo().getMapId() == 0) continue;	// 데이터 못 받아오는 것들 거르기
//					
//					if(dto != null)
//						{
//							listMatchDto.add(dto);
//						}
//					
//					// 예외처리: 요청 19개 되면 1초 종료(위에서 한 번 요청 보내는 거 포함해서 20개임)
//					if(checkRequestSecondLimit(currentRequestCnt)) 
//						{
//							log.info("요청제한으로 1초 sleep 합니다.");
//							Thread.sleep(1000l);
//							currentRequestCnt = 0;
//						};
//				}


			
			//return listMatchDto;
			//return mMatchList;
			return null;
		} 
		

		@Transactional
		public void matchDataUpdateForAPI(String _name, String _tag) throws Exception
		{
			// 전적갱신 버튼을 눌렀을 때 최신화하는 API
			
			// 1. user의 전적갱신 타임스탬프를 갖고 온다.
			UserDTO user = userService.searchUser(_name, _tag);

			if(user == null) 
				{
					log.info("==== err: matchDataUpdateForAPI에서 DB에서 user를 못 찾았습니다.");
					return;
				}
			
			LocalDateTime dateLastUpdatTime = user.getLastUpdateDate();
			long stampLastUpdateTime = DateTimeUtils.localDateTimeToEpoch(dateLastUpdatTime);

			// 2. 타임 스탬프 값을 기준으로 api에 요청해서 match를 갖고 온다.
			List<String> matchIds = getMatchIDsForAPI(user.getPuuid(), stampLastUpdateTime);
			
			// 3. ID값들을 DTO로 변환하고 저장한다.
			for(int i = 0; i < matchIds.size(); i++)
				{
					 MatchDTO dto = changeJSONToDTOMatchData(matchIds.get(i));
					 
					 // mapper를 사용해서 DTO -> Entity
					 MMatchEntity entity = getDTOToEntity(dto);
					 
					 if(dto != null) 
						 mMatchesRepository.save(entity);
				}
		}
		
		// ----------------------------- 함수에서 쓰일 함수들 ------------------------------------------
		
		private List<MatchDTO> requestMatchIDToAPI(List<String> _matchIds)
		{
			// matchId를 넣어주면 DB에 없는 것들만 조회해서 List로 Data를 반환한다.
			int nullIdx = -1;
			String[] arrStr = new String[100];	
			
//			while(arrStr.length > 99 && nullIdx == -1)
//			{
//				currentRequestCnt++;
//				
//				// arrStr: 일정 기간 내에 100개의 게임 매치ID를 갖고 온다
//				arrStr = RiotApiClient.getMatchList(puuid, start, count,
//						RiotSeasonConstants.getCurrentYearSeasonStartTimeStamp(), RiotSeasonConstants.getNowEndSeasonTimeStamp());
//
//				// DB에 접근해서 lastMatchId를 갖고 온다. 없다면 pass
//				// indexInList = indexOf(arrStr, lastMatchId);
//				start += count;
//				
//				//  3-4) List에 [index]부터 [0]까지 저장(list가 최근 - 오래된)
//				for(int i = 0; i < arrStr.length; i++)
//					{
//						listUserMatches.add(arrStr[i]);
//					}
//			}
			return null;
		}
		
		private boolean checkRequestSecondLimit(int curRequest) throws InterruptedException
		{
			// Request 요청제한을 넘었는지 체크한다.
			// 넘었으면 True, 아니라면 false
			if(limitRequestForSecond-1 <= curRequest) return true;

			return false;
		}
		
		private Page<MMatchEntity> getMatchDataInDB(int startPoint, int count, String puuid)
		{
			// DB에서 matchEntity 20개 받아오는 함수
			Pageable pageable = PageRequest.of(startPoint, count);
			
			return mMatchesRepository.findByInfoParticipantsPuuidOrderByInfoGameEndTimestamp(puuid, pageable);
		}
		
		// api에 요청하여 match id들을 갖고오는 함수
		private List<String> getMatchIDsForAPI(String puuid, long startTime) throws Exception
		{
			// API에 요청해서 전적갱신 시간 ~ 현재까지의 데이터를 조회한다.
			// 예외처리) 만약 전적갱신 버튼 누른 날짜가 이번 시즌 시작일 보다 오래됐다면? 이번 시즌부터 조회하도록 하도록 한다.
			startTime = (startTime < RiotSeasonConstants.getNowStartSeasonTimeStamp())? 
					RiotSeasonConstants.getNowStartSeasonTimeStamp() : startTime;
			
			// 1. api에 요청해서 최근 매치 데이터 100개를 갖고 오는 로직
			List<String> listUserMatches = new ArrayList<>();	// matchId들이 저장된 곳 
			String[] arrStr = new String[100];					// 매치 ID 저장할 곳(KR_...)
			int start = 0;					// 찾기 시작하는 위치 

			// --------------------- api로부터 데이터 탐색 --------------------------------------------------------------
			// 종료조건:
			// 		- 매치 데이터에 기간을 두고, 그 기간 안의 데이터가 100개가 아닌 경우 혹은
			// 		  인덱스가 발견된 경우에는 루프를 종료한다
			while(arrStr.length > 99)
			{
				// arrStr: 일정 기간 내에 100개의 게임 매치ID를 갖고 온다
				arrStr = RiotApiClient.getMatchList(puuid, start, count,
						startTime, RiotSeasonConstants.getNowTimeStamp());
				
				start += count;
				
				// list에 [0]최근 ~ [size()-1]오래된 순으로 저장한다.
				for(int i = 0; i < arrStr.length; i++)
					{
						listUserMatches.add(arrStr[i]);
					}
			}
			
			// 0: 최근, size()-1: 오래된 데이터
			return listUserMatches;
		}
		
		// api에 요청하여 id를 matchData로 바꿔서 List<matchDTO>로 변환해서 반환
		public MatchDTO changeJSONToDTOMatchData(String matchId) throws Exception
			{
				// matchid를 api에게 요청해서 받아오고 MatchDTO를 반환하는 함수
				
//				MatchDTO result = new MatchDTO();				
				MetadataDTO metadata = new MetadataDTO();
				MatchInfoDTO info = new MatchInfoDTO();

				// matchId로 매치 정보(JSONObject) 변환							// String 형태의 JSON 데이터를 JSONObject(HashMap)형 jsonObject로 변환
				JSONObject jsonObject = RiotApiClient.getMatchInfo(matchId);	//(JSONObject) parser.parse(matchJSON);
				JsonToDTOMapper mapper = new JsonToDTOMapper();
				
//				result = mapper.mapToDto(jsonObject, MatchDTO.class);
				// jsonObject의 JSON Key값으로 모든 데이터 조회
				for (Object key : jsonObject.keySet())
					{
						// key 값으로 받은 데이터를 value에 저장
						Object value = jsonObject.get(key);
						JSONObject jsonObj = (JSONObject) value;

						// Extract Player Identity KEY
						// JSON파일 내부의 metadata, info 데이터 불러오기
						
						if (key.equals("metadata")) metadata = mapper.mapToDto(jsonObj, MetadataDTO.class);
						if (key.equals("info")) info = mapper.mapToDto(jsonObj, MatchInfoDTO.class);
					}

				MatchDTO result = new MatchDTO();
				result.setMetadata(metadata);
				result.setInfo(info);

				return result;
			}

		// matchDTO를 Entity로 바꾸기
		public MMatchEntity getDTOToEntity(MatchDTO dto)
		{
			// ------------------------- Entity로 변환 --------------------------------------------------
				 MMatchEntity _entity = new MMatchEntity();
				 //_entity = modelMapper.map(listMatchDto.get(i), MMatchEntity.class);
				 _entity.setInfo(modelMapper.map(dto.getInfo(), MMatchInfoEntity.class));
				 _entity.setMetadata(modelMapper.map(dto.getMetadata(), MMetadataEntity.class));
				 _entity.setMatchId(dto.getMetadata().getMatchId());
				 
				 return _entity;
		}
	}
