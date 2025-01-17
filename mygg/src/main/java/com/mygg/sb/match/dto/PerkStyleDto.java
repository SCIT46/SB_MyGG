package com.mygg.sb.match.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerkStyleDto
{
	String description;	// 설명
	List<PerkStyleSelectionDto> selections = new ArrayList<>();
	int style;
}