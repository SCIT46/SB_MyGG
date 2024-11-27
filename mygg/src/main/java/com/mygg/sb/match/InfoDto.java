package com.mygg.sb.match;

import java.util.List;

public class InfoDto
	{
		String endOfGameResult; // 게임 정상적으로 끝났는지
		long gameDuration;		// 게임시간
		long gameID;
		String gameMode;
		String gameName;
		String gameVersion;
		String gameType;
		int mapId;
		List<ParticipantsDto> participants;
		List<TeamDto> teams; 
	}
