package com.mygg.sb.match.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mygg.sb.match.MatchDTO;
import com.mygg.sb.match.PublicMatchService;
import com.mygg.sb.match.service.MatchService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MatchController
	{
		private final MatchService service;
		private final PublicMatchService publicService;
		
		@GetMapping("/api/match/test/{name}/{tag}")
		@ResponseBody
		public List<MatchDTO> getMethodName(@PathVariable(name="name") String name,
										  @PathVariable(name="tag") String tag) throws Exception 
		{
			List<String> matchesIds = service.run(name, tag);
			List<MatchDTO> getMatchInfo = new ArrayList<>();
			
			// matchesIdes[0]: 제일최근, amtchesides[..]: 제일 나중
			
			for(int i = 0; i < matchesIds.size(); i++)
				{
					getMatchInfo.add(publicService.getMatchInfo(matchesIds.get(i)));
				}
			
			return getMatchInfo;
		}
		
		
	}
