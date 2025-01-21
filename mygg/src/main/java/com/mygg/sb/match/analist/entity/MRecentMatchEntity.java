package com.mygg.sb.match.analist.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mygg.sb.match.entity.MMatchInfoEntity;
import com.mygg.sb.match.entity.MMetadataEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Document(collection = "RecentMatchEntity")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MRecentMatchEntity
	{
		@Id
		private String puuid;
		
		RecenetMatchDataEntity recenetMatchDataEntity;
	}
