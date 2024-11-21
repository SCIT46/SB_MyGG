package com.mygg.sb.user;

import java.time.LocalDateTime;

import org.json.simple.JSONObject;

import com.mygg.sb.statics.api.RiotApiClient;
import com.mygg.sb.statics.util.DateTimeUtils;
import lombok.Getter;

@Getter
public class UserApi {
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

    // 이름과 태그를 통해 소환사 정보를 불러올 때 사용하는 생성자
    public UserApi(String gameName, String tagLine) throws Exception{
        // 이름과 태그를 puuid로 변환
        this.gameName = gameName;
        this.tagLine = tagLine;
        this.puuid = RiotApiClient.nameTagToPid(gameName,tagLine);
        init();
	}
    
    // puuid를 통해 소환사 정보를 불러올 때 사용하는 생성자
    public UserApi(String puuid) throws Exception{
        this.puuid = puuid;
        String[] nametag = RiotApiClient.pidToNametag(puuid);
        this.gameName = nametag[0];
        this.tagLine = nametag[1];
        init();
    }

    // 인스턴스 초기화 구문
    private void init() throws Exception{
        // // API 주소값
        // //String request_url = String.format("%s%s%s?api_key=%s",RiotApiConstants.RIOT_API_URL_KR,RiotApiConstants.RIOT_API_SUMMONER_INFO,this.puuid,RiotApiConstants.API_KEY);
        
        // //url을 json으로 변환
        // String summoJSON = UrlToJson.urlToJson(UrlToJson.urlConvertor("summonerInfo", this.puuid));

		// // JSON 데이터를 분석해주는 JSONParser 객체 생성
		// JSONParser parser = new JSONParser();

		// 소환사 정보 JSON
		JSONObject jsonObject = RiotApiClient.getSummonerInfo(puuid);   //(JSONObject) parser.parse(summoJSON);           
        summonerId = (String) jsonObject.get("id");
        profileIconId = ((Long) jsonObject.get("profileIconId")).intValue();
        revisionDate = DateTimeUtils.epochToLocalDateTime((long)jsonObject.get("revisionDate"));
        summonerLevel = ((Long) jsonObject.get("summonerLevel")).intValue();

        // summonerId를 통해 리그정보 JSON 받아옴
        JSONObject jsonObject2 = RiotApiClient.getLeagueBySummonerId(summonerId);
        leagueId = (String) jsonObject2.get("leagueId");
        tier = (String) jsonObject2.get("tier");
        rank = (String) jsonObject2.get("rank");
        leaguePoints = ((Long) jsonObject2.get("leaguePoints")).intValue();
        wins = ((Long) jsonObject2.get("wins")).intValue();
        losses = ((Long) jsonObject2.get("losses")).intValue();
    }
}
