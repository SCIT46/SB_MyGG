package com.mygg.sb.match.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// mongoDB matches 저장
@Document(collection = "matches")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MMatchEntity
	{
		private MMatchInfoEntity info;
		private MMetadataEntity metadata;
	}
