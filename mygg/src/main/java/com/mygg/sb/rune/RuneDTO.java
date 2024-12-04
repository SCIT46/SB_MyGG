package com.mygg.sb.rune;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RuneDTO
	{
		private int id;
		private String key;
		private String icon;
		private String name;

		private List<RunesDTO> slots;
	}
