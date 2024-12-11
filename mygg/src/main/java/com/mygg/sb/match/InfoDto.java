package com.mygg.sb.match;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InfoDto
	{
		String endOfGameResult; // 게임 정상적으로 끝났는지
		long gameDuration;		// 게임시간
		String gameMode;		// 게임 모드 ex) "CLASSIC"
		String gameVersion;		// 게임버전 ex) 14.23.636.9832
		String gameType;		// 게임타입 ex) "MATCHED_GAME"
		int queueId;
		int mapId;				// 맵 ID,  11
		String platformId;		// 게임 플랫폼, ex) "JP1"
		List<ParticipantsDto> participants;
		List<TeamDto> teams;	// 두 팀의 밴, 승리여부 등 여부
	}
