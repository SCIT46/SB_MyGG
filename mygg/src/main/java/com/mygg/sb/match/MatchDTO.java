package com.mygg.sb.match;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MatchDTO
	{
		private String matchId;
		
		private MatchInfoDTO Info;
		private MetadataDTO metadata;
		
		public MatchDTO(String str)
		{
			matchId = str;
		}
//		public static MatchDTO toDTO(MMatchEntity mMatchEntity)
//			{
//				return MatchDTO.builder()
//						.info(MatchInfoDTO.toDTO(mMatchEntity.getInfo())
//						.match(MetadataDTO.toDTO(mMatchEntity.getMetadata()))
//						.build();
//			}
	}
