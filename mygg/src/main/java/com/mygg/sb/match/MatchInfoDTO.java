package com.mygg.sb.match;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchInfoDTO
	{
		String endOfGameResult; // 게임 정상적으로 끝났는지
		long gameDuration;		// 게임시간
		long gameStartTimestamp;// 게임시작시간
		long gameEndTimestamp;	// 게임종료시간
		String gameMode;		// 게임 모드 ex) "CLASSIC"
		String gameVersion;		// 게임버전 ex) 14.23.636.9832
		String gameType;		// 게임타입 ex) "MATCHED_GAME"
		int queueId;
		int mapId;				// 맵 ID,  11
		String platformId;		// 게임 플랫폼, ex) "JP1"
		List<ParticipantsDTO> participants;
		List<TeamDTO> teams;	// 두 팀의 밴, 승리여부 등 여부
		
//		public static MatchInfoDTO toDTO(MMatchInfoEntity data)
//			{
//				List<ParticipantsDTO> pList = new ArrayList<>();
//				List<TeamDTO> tList = new ArrayList<>();
//				for(MParticipantsEntity d : data.getParticipants())
//					{
//						pList.add(ParticipantsDTO.toDTO(d));
//					}
//				for(MTeamEntity e : data.getTeams())
//					{
//						tList.add(TeamDTO.toDTO(e));
//					}
//				
//				
//				
//				return MatchInfoDTO.builder()
//			            .endOfGameResult(data.getEndOfGameResult())       // 게임 정상 종료 여부
//			            .gameDuration(data.getGameDuration())             // 게임 시간
//			            .gameStartTimestamp(data.getGameStartTimestamp()) // 게임 시작 시간
//			            .gameEndTimestamp(data.getGameEndTimestamp())     // 게임 종료 시간
//			            .gameMode(data.getGameMode())                     // 게임 모드
//			            .gameVersion(data.getGameVersion())               // 게임 버전
//			            .gameType(data.getGameType())                     // 게임 타입
//			            .queueId(data.getQueueId())                       // 큐 ID
//			            .mapId(data.getMapId())                           // 맵 ID
//			            .platformId(data.getPlatformId())                 // 플랫폼 ID
//						.participants(pList)
//						.teams(tList)
//						.build();
//			}
	}
