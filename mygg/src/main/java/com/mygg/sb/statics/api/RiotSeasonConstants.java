package com.mygg.sb.statics.api;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RiotSeasonConstants
	{
		/*
		  	to do: 2024 Split3 종료일 미정, 수정필요
		 */
		public static final long SEASON_2024_SPLIT3_START = (LocalDateTime.of(2024, 9, 25, 12, 0)).atZone(ZoneId.of("UTC")).toEpochSecond(); 
		public static final long SEASON_2024_SPLIT3_END = (LocalDateTime.of(2025, 1, 31, 2, 0)).atZone(ZoneId.of("UTC")).toEpochSecond();
		public static final LocalDateTime SEASON_2024_SPLIT2_START = LocalDateTime.of(2024, 5, 15, 12, 0); 
		public static final LocalDateTime SEASON_2024_SPLIT2_END = LocalDateTime.of(2024, 9, 25, 2, 0);
		public static final long SEASON_2024_SPLIT1_START = (LocalDateTime.of(2024, 1, 10, 12, 0)).atZone(ZoneId.of("UTC")).toEpochSecond(); 
		public static final LocalDateTime SEASON_2024_SPLIT1_END = LocalDateTime.of(2024, 5, 15, 2, 0);
		
		public static final long getCurrentYearSeasonStartTimeStamp() {return SEASON_2024_SPLIT1_START;}
		public static final long getNowStartSeasonTimeStamp() {return SEASON_2024_SPLIT3_START;}
		public static final long getNowEndSeasonTimeStamp() {return SEASON_2024_SPLIT3_END;}
	}
