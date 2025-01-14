package com.mygg.sb.match.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Document
public class MObjectEntity
{
	MObjectiveEntity baron;		
	MObjectiveEntity champion;		
	MObjectiveEntity dragon;
	MObjectiveEntity horde;		
	MObjectiveEntity inhibitor;		
	MObjectiveEntity riftHerald;		
	MObjectiveEntity tower;	
}