package com.mygg.sb.match;

import java.util.List;

public class TeamDto
	{
		List<BanDto> bans;
		ObjectDto objectives;
		int teamId;
		boolean win;
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