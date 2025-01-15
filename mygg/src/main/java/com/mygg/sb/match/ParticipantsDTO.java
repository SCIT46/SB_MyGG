package com.mygg.sb.match;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class ParticipantsDTO
{
	// 플레이어 정보
	// to do: 2024-11-21
	//	룬 작업, 서브룬 작업 구조체로 정의하든 한 구조체에 다 넣든 작업 필요함.
	String lane;		// 어느 라인인가														// teamPosition
	String userName;	// 유저 이름
	Long summonerLevel;	// 유저 레벨
	Double goldPerMinute;// 분당 골드 값 추출
	
	String riotIdGameName;
	int championId;		// 챔피언 이미지에 쓸 id값 ex) 51
	String championName;// 사용한 챔피언
	int champLevel;		// 챔피언 레벨
	
	// 아이템칸 1 ~ 6, ex) 1054, 3006 3153 0 0 0 3340
	int item0;
	int item1;
	int item2;
	int item3;
	int item4;
	int item5;
	int item6;
	
	// KDA
	int kills;
	int deaths;
	int assists;
	float kda;
	
	int visionScore;			// 시야점수
	int visionWardsBoughtInGame;// 와드 구입수
	int wardsPlaced;			// 와드설치수
	
	boolean win;
	
	int totalDamageDealtToChampions;// 챔피언에게 가한 피해량
	int totalDamageTaken;			// 받은 피해량
	int summoner1Id;				// 서머너 스펠1	 12(Teleport), 4(Flaxsh)
	int summoner2Id;				// 서머너 스펠2
	
	PerksDTO perkDto;				// 룬
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

	public ParticipantsDTO()
		{
			// TODO Auto-generated constructor stub
		}
	
	@Override
	public String toString()
		{
			return "ParticipantsDto [lane=" + lane + ", userName=" + userName + ", summonerLevel=" + summonerLevel
					+ ", goldPerMinute=" + goldPerMinute + ", riotIdGameName=" + riotIdGameName + ", championId="
					+ championId + ", championName=" + championName + ", champLevel=" + champLevel + ", item0=" + item0
					+ ", item1=" + item1 + ", item2=" + item2 + ", item3=" + item3 + ", item4=" + item4 + ", item5="
					+ item5 + ", item6=" + item6 + ", kills=" + kills + ", deaths=" + deaths + ", assists=" + assists
					+ ", kda=" + kda + ", visionScore=" + visionScore + ", visionWardsBoughtInGame="
					+ visionWardsBoughtInGame + ", wardsPlaced=" + wardsPlaced + ", win=" + win
					+ ", totalDamageDealtToChampions=" + totalDamageDealtToChampions + ", totalDamageTaken="
					+ totalDamageTaken + ", summoner1Id=" + summoner1Id + ", summoner2Id=" + summoner2Id + ", perkDto="
					+ perkDto + "]";
		}
}

