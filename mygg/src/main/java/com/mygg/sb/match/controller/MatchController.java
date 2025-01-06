package com.mygg.sb.match.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mygg.sb.match.MatchDTO;
import com.mygg.sb.match.entity.MMatchEntity;
import com.mygg.sb.match.service.PublicMatchService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MatchController
	{
		private final PublicMatchService publicService;
		
		@GetMapping("/api/match/test/{name}/{tag}")
		@ResponseBody
		public List<MMatchEntity> getMethodName(@PathVariable(name="name") String name,
										  @PathVariable(name="tag") String tag) throws Exception 
		{
			//List<MatchDTO> matchesIds = publicService.run(name, tag);
			// matchesIdes[0]: 제일최근, amtchesides[..]: 제일 나중
			
			return publicService.run(name, tag);
		}
		
		
	}
