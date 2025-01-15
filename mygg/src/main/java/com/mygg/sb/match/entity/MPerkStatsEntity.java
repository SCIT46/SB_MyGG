package com.mygg.sb.match.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Document
public class MPerkStatsEntity 
{
	// Name	Data Type	Description
	int defense;		
	int	flex;	
	int offense;	
}
