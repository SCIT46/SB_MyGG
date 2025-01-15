package com.mygg.sb.match.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class MPerkStyleEntity
{
	String description;	// 설명
	List<MPerkStyleSelectionEntity> selections = new ArrayList<>();
	int style;
}
