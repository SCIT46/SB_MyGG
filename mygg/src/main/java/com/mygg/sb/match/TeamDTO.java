package com.mygg.sb.match;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeamDTO
	{
		List<BanDTO> bans;		// 밴한 것들
		ObjectDTO objectives;	// 먹은 오브젝트들
		int teamId;				// 팀ID
		boolean win;			// 승리여부
	}

// 밴픽
@Getter
@Setter
class BanDTO
	{
		int championId;
		int pickTurn;
	}

@Getter
@Setter
class ObjectDTO
{
	ObjectiveDTO baron;		
	ObjectiveDTO champion;		
	ObjectiveDTO dragon;
	ObjectiveDTO horde;		
	ObjectiveDTO inhibitor;		
	ObjectiveDTO riftHerald;		
	ObjectiveDTO tower;	
}

@Getter
@Setter
class ObjectiveDTO
{
	boolean first;		
	int kills;	
}