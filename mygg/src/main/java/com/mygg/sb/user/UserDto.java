package com.mygg.sb.user;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String leagueId;
    private String puuid;
    private String summonerId;
    //유저 이미지(초상화)
    private int profileIconId;
    //유저 이름
    private String gameName; 
    //유저 태그
    private String tagLine;
    //유저 레벨
    private int summonerLevel;
    //유저 등급
    private String tier;
    private String rank;
    //유저 lp점수
    private int leaguePoints;
    //유저 승패 
    private int wins;
    private int losses;
    //최근 갱신 날짜
    private LocalDateTime revisionDate;
    // 최근 매치 목록
    private String[] matchList;
}
