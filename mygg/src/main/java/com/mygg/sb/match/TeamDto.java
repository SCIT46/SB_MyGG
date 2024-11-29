package com.mygg.sb.match;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeamDto
	{
		List<BanDto> bans;		// 밴한 것들
		ObjectDto objectives;	// 먹은 오브젝트들
		int teamId;				// 팀ID
		boolean win;			// 승리여부
	}

// 밴픽
@Getter
@Setter
class BanDto
	{
		int championId;
		int pickTurn;
	}

@Getter
@Setter
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

@Getter
@Setter
class ObjectiveDto
{
	boolean first;		
	int kills;	
}