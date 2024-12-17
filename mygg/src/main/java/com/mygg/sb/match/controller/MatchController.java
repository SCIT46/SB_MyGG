package com.mygg.sb.match.controller;

import org.springframework.stereotype.Controller;

import com.mygg.sb.match.service.MatchService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MatchController
	{
		private final MatchService service;

		
	}
