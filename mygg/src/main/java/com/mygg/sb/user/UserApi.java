package com.mygg.sb.user;

import java.time.LocalDateTime;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mygg.sb.statics;

import lombok.Getter;

@Getter
public class UserApi {
    String puuid;
    String summonerId;
    //유저 이미지(초상화)
    int profileIconId;
    //유저 이름
    String gameName; 
    //유저 태그
    String tagLine;
    //유저 레벨
    int summonerLevel;
    //유저 등급
    String tier;
    String rank;
    //유저 lp점수
    int leaguePoints;
    //유저 승패 
    int wins;
    int losses;
    //최근 갱신 날짜
    LocalDateTime revisionDate;


    public UserApi(String gameName, String tagLine) throws Exception{
        // 이름과 태그를 puuid로 변환
        puuid = statics.nameTagToPid(gameName,tagLine);

        // API 주소값
        String request_url = String.format("%s%s%s?api_key=%s",statics.RIOT_API_URL_KR,statics.RIOT_API_SUMMONER_INFO,puuid,statics.API_KEY);
        
        //url을 json으로 변환
        String summoJSON = statics.urlToJson(request_url);

		// JSON 데이터를 분석해주는 JSONParser 객체 생성
		JSONParser parser = new JSONParser();

		// 소환사 정보 JSON
		JSONObject jsonObject = (JSONObject) parser.parse(summoJSON);
        summonerId = (String) jsonObject.get("id");
        profileIconId = ((Long) jsonObject.get("profileIconId")).intValue();
        revisionDate = statics.epochToLocalDateTime((long)jsonObject.get("revisionDate"));
        summonerLevel = ((Long) jsonObject.get("summonerLevel")).intValue();

        // summonerId를 통해 리그정보 JSON 받아옴
        JSONObject jsonObject2 = statics.getLeagueBySummonerId(summonerId);
        tier = (String) jsonObject2.get("tier");
        rank = (String) jsonObject2.get("rank");
        leaguePoints = ((Long) jsonObject2.get("leaguePoints")).intValue();
        wins = ((Long) jsonObject2.get("wins")).intValue();
        losses = ((Long) jsonObject2.get("losses")).intValue();


        this.gameName = gameName;
        this.tagLine = tagLine;

	}
}
