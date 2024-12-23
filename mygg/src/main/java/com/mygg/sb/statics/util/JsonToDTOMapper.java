package com.mygg.sb.statics.util;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class JsonToDTOMapper
	{
		private final Gson gson = new Gson();
		private final ObjectMapper objectMapper = new ObjectMapper(); // ObjectMapper 인스턴스 추가

		public <T> T mapToDto(JSONObject jsonObject, Class<T> dtoClass)
			{
				return gson.fromJson(jsonObject.toString(), dtoClass);
			}
		
		public <T> ArrayList<T> mapToDto(Object json, Class<T> dtoClass)
			{
				try
					{
						// List 타입으로 JSON 변환
						return objectMapper.convertValue(json, new TypeReference<ArrayList<T>>(){});
					} catch (Exception e)
					{
						throw new RuntimeException("Failed to map JSON to DTO list", e);
					}
			}
	}
