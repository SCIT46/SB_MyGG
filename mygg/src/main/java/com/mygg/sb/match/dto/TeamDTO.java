package com.mygg.sb.match.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamDTO
	{
		List<BanDTO> bans;		// 밴한 것들
		ObjectDTO objectives;	// 먹은 오브젝트들
		int teamId;				// 팀ID
		boolean win;			// 승리여부
	}