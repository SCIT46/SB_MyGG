package com.mygg.sb.match;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
class ParticipantsDto
{
	// 플레이어 정보
	String riotIdGameName;	// 라이엇 이름
	String riotIdTagline;	// 라이엇 태그
	String championName;	// 사용한 챔피언	
	String lane;			// 어느 라인인가
	String role;			// 어느 라인인가	
	Long summonerLevel;		// 유저 레벨
	String puuid;			// puuid
	
	int championId;		// 챔피언 이미지에 쓸 id값 ex) 51
	int champLevel;		// 챔피언 레벨
	
	// 아이템칸 1 ~ 6, ex) 1054, 3006 3153 0 0 0 3340
	int item0;
	int item1;
	int item2;
	int item3;
	int item4;
	int item5;
	int item6;
	
	int teamId;
	boolean win;
	
	// KDA, KDA는 Challengers에 있음
	int kills;
	int deaths;
	int assists;
	
	int visionScore;			// 시야점수
	int visionWardsBoughtInGame;// 와드 구입수
	int wardsPlaced;			// 와드설치수
	int wardsKilled;			// 부셔진 와드수
	int detectorWardsPlaced;	// 와드 감지
	
	int totalMinionsKilled;		// 미니언 잡은 수
	int neutralMinionsKilled;	// 정글몹 잡은 수, 총 CS=totalMinionsKilled+neutralMinionsKilled
								// 분당 CS=  게임 시간(분) / 총 CS​

	int totalDamageDealtToChampions;// 챔피언에게 가한 피해량
	int totalDamageTaken;			// 받은 피해량
	int summoner1Id;				// 서머너 스펠1	 12(Teleport), 4(Flaxsh)
	int summoner2Id;				// 서머너 스펠2
	
	int dangerPings;			// 위험핑
	int getBackPings;			// 백핑
	int holdPings;				// 홀드?핑
	int needVisionPings;		// 시야필요 핑
	
	PerksDto perks;				// 룬
	ChallengesDto challenges;	// 챌린지스
	
	//Double goldPerMinute;// 분당 골드 값 추출
	// PerksDto perks;
//	서브룬 ("description": "subStyle",
//		  "styles"."selections"."perk": 8429,
//		  "style"."selections"."perk": 8451)
//	
//	미니언킬수(totalMinionsKilled)

//	public ParticipantsDto(String _championName, String lane, String userName, Long userLevel, Double goldPerMin)
//		{
//			this.championName = _championName;
//			this.lane = lane;
//			this.userName = userName;
//			this.userLevel = userLevel;
//			this.goldPerMin = goldPerMin;
//		}

	public ParticipantsDto()
		{
			// TODO Auto-generated constructor stub
		}
}

