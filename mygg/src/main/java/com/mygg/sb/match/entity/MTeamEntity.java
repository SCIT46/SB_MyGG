package com.mygg.sb.match.entity;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Document
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MTeamEntity
	{
		List<MBanEntity> bans;		// 밴한 것들
		MObjectEntity objectives;	// 먹은 오브젝트들
		int teamId;				// 팀ID
		boolean win;			// 승리여부
	}

// 밴픽
@Getter
@NoArgsConstructor
@Document
class MBanEntity
	{
		int championId;
		int pickTurn;
	}

@Getter
@NoArgsConstructor
@Document
class MObjectEntity
{
	MObjectiveEntity baron;		
	MObjectiveEntity champion;		
	MObjectiveEntity dragon;
	MObjectiveEntity horde;		
	MObjectiveEntity inhibitor;		
	MObjectiveEntity riftHerald;		
	MObjectiveEntity tower;	
}

@Getter
@NoArgsConstructor
@Document
class MObjectiveEntity
{
	boolean first;		
	int kills;	
}