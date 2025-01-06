package com.mygg.sb.match.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerkStatsDto 
{
	// Name	Data Type	Description
	int defense;		
	int	flex;	
	int offense;	
}