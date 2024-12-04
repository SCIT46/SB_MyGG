package com.mygg.sb.rune;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.mygg.sb.match.MetadataDto;
import com.mygg.sb.statics.api.RiotApiClient;
import com.mygg.sb.statics.util.JsonToDtoMapper;

@Service
public class RuneService
	{	
		List<JSONObject> runeDto;
		
		public JSONObject getRuneDto() throws Exception
		{
		    JSONObject jsonObject = RiotApiClient.getRune("all");
		    JsonToDtoMapper mapper = new JsonToDtoMapper();

//		    for(Object key : jsonObject.keySet())
//		    	{
//		    		//JSONObject temp = mapper.mapToDto(jsonObject.get(key), RuneDTO.class);  
//		    	}
			
			return jsonObject;
		}
	}
