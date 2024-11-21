package com.mygg.sb.match;

import java.util.List;

class ParticipantsDto
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
	
	// PerksDto perks;
//	서브룬 ("description": "subStyle",
//		  "styles"."selections"."perk": 8429,
//		  "style"."selections"."perk": 8451)
//	
//	미니언킬수(totalMinionsKilled)

	
	public String getLane()
		{
			return lane;
		}

	public void setLane(String lane)
		{
			this.lane = lane;
		}

	public String getUserName()
		{
			return userName;
		}

	public void setUserName(String userName)
		{
			this.userName = userName;
		}

	public Long getSummonerLevel()
		{
			return summonerLevel;
		}

	public void setSummonerLevel(Long summonerLevel)
		{
			this.summonerLevel = summonerLevel;
		}

	public Double getgoldPerMinute()
		{
			return goldPerMinute;
		}

	public void setGoldPerMinute(Double goldPerMinute)
		{
			this.goldPerMinute = goldPerMinute;
		}

	public String getRiotIdGameName()
		{
			return riotIdGameName;
		}

	public void setRiotIdGameName(String riotIdGameName)
		{
			this.riotIdGameName = riotIdGameName;
		}

	public int getChampionId()
		{
			return championId;
		}

	public void setChampionId(int championId)
		{
			this.championId = championId;
		}

	public String getChampionName()
		{
			return championName;
		}

	public void setChampionName(String championName)
		{
			this.championName = championName;
		}

	public int getChampLevel()
		{
			return champLevel;
		}

	public void setChampLevel(int champLevel)
		{
			this.champLevel = champLevel;
		}

	public int getItem0()
		{
			return item0;
		}

	public void setItem0(int item0)
		{
			this.item0 = item0;
		}

	public int getItem1()
		{
			return item1;
		}

	public void setItem1(int item1)
		{
			this.item1 = item1;
		}

	public int getItem2()
		{
			return item2;
		}

	public void setItem2(int item2)
		{
			this.item2 = item2;
		}

	public int getItem3()
		{
			return item3;
		}

	public void setItem3(int item3)
		{
			this.item3 = item3;
		}

	public int getItem4()
		{
			return item4;
		}

	public void setItem4(int item4)
		{
			this.item4 = item4;
		}

	public int getItem5()
		{
			return item5;
		}

	public void setItem5(int item5)
		{
			this.item5 = item5;
		}

	public int getItem6()
		{
			return item6;
		}

	public void setItem6(int item6)
		{
			this.item6 = item6;
		}

	public int getKills()
		{
			return kills;
		}

	public void setKills(int kills)
		{
			this.kills = kills;
		}

	public int getDeaths()
		{
			return deaths;
		}

	public void setDeaths(int deaths)
		{
			this.deaths = deaths;
		}

	public int getAssists()
		{
			return assists;
		}

	public void setAssists(int assists)
		{
			this.assists = assists;
		}

	public float getKda()
		{
			return kda;
		}

	public void setKda(float kda)
		{
			this.kda = kda;
		}

	public int getVisionScore()
		{
			return visionScore;
		}

	public void setVisionScore(int visionScore)
		{
			this.visionScore = visionScore;
		}

	public int getVisionWardsBoughtInGame()
		{
			return visionWardsBoughtInGame;
		}

	public void setVisionWardsBoughtInGame(int visionWardsBoughtInGame)
		{
			this.visionWardsBoughtInGame = visionWardsBoughtInGame;
		}

	public int getWardsPlaced()
		{
			return wardsPlaced;
		}

	public void setWardsPlaced(int wardsPlaced)
		{
			this.wardsPlaced = wardsPlaced;
		}

	public boolean isWin()
		{
			return win;
		}

	public void setWin(boolean win)
		{
			this.win = win;
		}

	public int getTotalDamageDealtToChampions()
		{
			return totalDamageDealtToChampions;
		}

	public void setTotalDamageDealtToChampions(int totalDamageDealtToChampions)
		{
			this.totalDamageDealtToChampions = totalDamageDealtToChampions;
		}

	public int getTotalDamageTaken()
		{
			return totalDamageTaken;
		}

	public void setTotalDamageTaken(int totalDamageTaken)
		{
			this.totalDamageTaken = totalDamageTaken;
		}

	public int getSummoner1Id()
		{
			return summoner1Id;
		}

	public void setSummoner1Id(int summoner1Id)
		{
			this.summoner1Id = summoner1Id;
		}

	public int getSummoner2Id()
		{
			return summoner2Id;
		}

	public void setSummoner2Id(int summoner2Id)
		{
			this.summoner2Id = summoner2Id;
		}

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

class PerksDto
{
	PerkStatsDto statPerks;		// 적응형 공격력 3개 찍는 거, 사용자파편(["perks"]["statPerks"]["defense"] | ["flex"] | ["offense"]) = 5011 5008 5005
	List<PerkStyleDto> styles;	// 룬 선택 리스트 ex) [0]["selections"]["perk"] : 8345
								// 				  [1]["selections"]["perk"] : 8347
}
class PerkStyleDto
{
	String description;	// 설명
	List<PerkStyleSelectionDto> selections;		
	int style;	
}

class PerkStatsDto 
{
	// Name	Data Type	Description
	int defense;		
	int	flex;	
	int offense;	
}
class PerkStyleSelectionDto 
{
	int perk;		
	int var1;		
	int var2;		
	int var3;	
}