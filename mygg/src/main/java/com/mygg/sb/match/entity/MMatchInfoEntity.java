package com.mygg.sb.match.entity;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mygg.sb.match.TeamDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@Getter
@Setter
@NoArgsConstructor
public class MMatchInfoEntity
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
		List<MParticipantsEntity> participants;
		List<MTeamEntity> teams;	// 두 팀의 밴, 승리여부 등 여부
	}
