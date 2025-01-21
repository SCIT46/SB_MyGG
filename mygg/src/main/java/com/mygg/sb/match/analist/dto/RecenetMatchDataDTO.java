package com.mygg.sb.match.analist.dto;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecenetMatchDataDTO
	{
		String characterId;		// 사용한 캐릭터
		int totalMinionKilled;// cs 개수
		int kill;			// 킬 
		int death;			// 데스
		int assisst;		// 어시스트
		float winRate;		// 승률
		int gameCnt;		// 게임수
	}
