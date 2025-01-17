package com.mygg.sb.match.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;

//밴픽
@Getter
@NoArgsConstructor
@Document
public class MBanEntity
	{
		int championId;
		int pickTurn;
	}