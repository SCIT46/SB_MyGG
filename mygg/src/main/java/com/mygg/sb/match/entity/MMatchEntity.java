package com.mygg.sb.match.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mygg.sb.match.MatchDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// mongoDB matches 저장
@Document(collection = "matches")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MMatchEntity
	{
		@Id
		private String matchId;
		
		private MMatchInfoEntity info;
		private MMetadataEntity metadata;
		
//		public static MMatchEntity toEntity (MatchDTO matchDTO) { 
//			return MMatchEntity.builder()
//					.info(matchDTO.getInfo())
//					.metadata(matchDTO.getMetadata())
//					.matchId(matchDTO.getMetadata().getMatchId())
//					.build();
//		} 
	}
