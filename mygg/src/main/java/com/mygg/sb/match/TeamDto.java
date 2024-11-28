package com.mygg.sb.match;

import java.util.List;

public class TeamDto
	{
		List<BanDto> bans;		// 밴한 것들
		ObjectDto objectives;	// 먹은 오브젝트들
		int teamId;				// 팀ID
		boolean win;			// 승리여부
	}

// 밴픽
class BanDto
	{
		int championId;
		int pickTurn;
	}

class ObjectDto
{
	ObjectiveDto baron;		
	ObjectiveDto champion;		
	ObjectiveDto dragon;
	ObjectiveDto horde;		
	ObjectiveDto inhibitor;		
	ObjectiveDto riftHerald;		
	ObjectiveDto tower;	
}

class ObjectiveDto
{
	boolean first;		
	int kills;	
}