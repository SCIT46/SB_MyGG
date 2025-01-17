package com.mygg.sb.match.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Document
public class MObjectiveEntity
{
	boolean first;	
	int kills;	
	
	public boolean getFirst()
	{
		return first;
	}
}