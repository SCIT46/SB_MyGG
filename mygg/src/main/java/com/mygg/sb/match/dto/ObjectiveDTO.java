package com.mygg.sb.match.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ObjectiveDTO
{
	boolean first;		
	int kills;	
	
//	public static ObjectiveDTO toDTO(MObjectiveEntity entity)
//	{
//		return ObjectiveDTO.builder()
//				.first(entity.getFirst())
//				.kills(entity.getKills())
//				.build();
//	}
}