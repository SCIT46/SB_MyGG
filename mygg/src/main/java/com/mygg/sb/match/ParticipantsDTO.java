package com.mygg.sb.match;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mygg.sb.match.entity.MParticipantsEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Document
@Builder
@AllArgsConstructor
@Getter
@Setter
public class ParticipantsDTO
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
	
	PerksDTO perks;				// 룬
	ChallengesDTO challenges;	// 챌린지스
	
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

	public ParticipantsDTO()
		{
			// TODO Auto-generated constructor stub
		}

//	public static ParticipantsDTO toDTO(MParticipantsEntity participants)
//		{
//		    return ParticipantsDTO.builder()
//		            .riotIdGameName(participants.getRiotIdGameName())          // 라이엇 이름
//		            .riotIdTagline(participants.getRiotIdTagline())            // 라이엇 태그
//		            .championName(participants.getChampionName())              // 사용한 챔피언
//		            .lane(participants.getLane())                              // 라인
//		            .role(participants.getRole())                              // 역할
//		            .summonerLevel(participants.getSummonerLevel())            // 소환사 레벨
//		            .puuid(participants.getPuuid())                            // PUUID
//		            .championId(participants.getChampionId())                  // 챔피언 ID
//		            .champLevel(participants.getChampLevel())                  // 챔피언 레벨
//		            .item0(participants.getItem0())                            // 아이템 0
//		            .item1(participants.getItem1())                            // 아이템 1
//		            .item2(participants.getItem2())                            // 아이템 2
//		            .item3(participants.getItem3())                            // 아이템 3
//		            .item4(participants.getItem4())                            // 아이템 4
//		            .item5(participants.getItem5())                            // 아이템 5
//		            .item6(participants.getItem6())                            // 아이템 6
//		            .teamId(participants.getTeamId())                          // 팀 ID
//		            .win(participants.isWin())                                 // 승리 여부
//		            .kills(participants.getKills())                            // 킬
//		            .deaths(participants.getDeaths())                          // 데스
//		            .assists(participants.getAssists())                        // 어시스트
//		            .visionScore(participants.getVisionScore())                // 시야 점수
//		            .visionWardsBoughtInGame(participants.getVisionWardsBoughtInGame()) // 와드 구매 수
//		            .wardsPlaced(participants.getWardsPlaced())                // 설치한 와드 수
//		            .wardsKilled(participants.getWardsKilled())                // 부순 와드 수
//		            .detectorWardsPlaced(participants.getDetectorWardsPlaced()) // 감지 와드 수
//		            .totalMinionsKilled(participants.getTotalMinionsKilled())  // 총 미니언 킬
//		            .neutralMinionsKilled(participants.getNeutralMinionsKilled()) // 정글몹 킬
//		            .totalDamageDealtToChampions(participants.getTotalDamageDealtToChampions()) // 챔피언에게 가한 피해량
//		            .totalDamageTaken(participants.getTotalDamageTaken())      // 받은 피해량
//		            .summoner1Id(participants.getSummoner1Id())                // 서머너 스펠 1
//		            .summoner2Id(participants.getSummoner2Id())                // 서머너 스펠 2
//		            .dangerPings(participants.getDangerPings())                // 위험 핑
//		            .getBackPings(participants.getGetBackPings())              // 백 핑
//		            .holdPings(participants.getHoldPings())                    // 홀드 핑
//		            .needVisionPings(participants.getNeedVisionPings())        // 시야 핑
//		            .perks(PerksDTO.toDTO(participants.getPerks()))            // 룬
//		            .challenges(ChallengesDTO.toDTO(participants.getChallenges())) // 챌린지
//		            .build();
//		}
}

