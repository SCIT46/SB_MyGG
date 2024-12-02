package com.mygg.sb.statics.util;

import org.json.simple.JSONObject;

import com.google.gson.Gson;

public class JsonToDtoMapper
	{
		private final Gson gson = new Gson();

		public <T> T mapToDto(JSONObject jsonObject, Class<T> dtoClass)
			{
				return gson.fromJson(jsonObject.toString(), dtoClass);
			}
	}
