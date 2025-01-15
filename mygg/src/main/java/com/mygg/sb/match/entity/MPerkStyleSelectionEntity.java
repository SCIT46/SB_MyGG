package com.mygg.sb.match.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class MPerkStyleSelectionEntity 
{
	int perk;		
	int var1;		
	int var2;		
	int var3;	
}