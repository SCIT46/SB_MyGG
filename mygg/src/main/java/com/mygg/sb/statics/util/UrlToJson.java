package com.mygg.sb.statics.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.mygg.sb.statics.api.RiotApiConstants;

public class UrlToJson {
    // JSON : url을 json으로 변환
    public static String urlToJson(String url) throws Exception {
        /* JSON 파일 불러오기/변환 */

        // API 키를 요청할 주소값을 URL 타입으로 생성
        URL request_url = new URL(url);

        // URL을 openStream() UTF-8을 통해 열고 받은 데이터를 inputStreamReader를 통해 받아
        // BufferedReader를 통해 bf에 저장
        BufferedReader bf = new BufferedReader(new InputStreamReader(request_url.openStream(), "UTF-8"));

        // bf에 저장한 데이터를 String 형태로 저장(JSONParser가 String으로 된 JSON 데이터를 분석)
        String userJSON = bf.readLine();

        // 버퍼 닫기
        bf.close();

        return userJSON;
    }

    // url : 타입에 따라 url 생성
    // summonerInfo - puuid로 소환사 정보 (RIOT_API_URL_KR/account/v1/summoners/by-puuid/{puuid})
    // matchInfo - matchId로 매치 정보 (RIOT_API_URL/match/v5/matches/{matchId})
    // leagueInfo - summonerId로 리그 정보 (RIOT_API_URL_KR/league/v4/entries/by-summoner/{summonerId})
    // accountByPid - puuid로 소환사 정보 (RIOT_API_URL/account/v1/accounts/by-puuid/{puuid})
    public static String urlConvertor(String type, String arg1){
        switch(type){
            case "summonerInfo":  //arg1 : puuid
                return String.format("%s%s%s?api_key=%s", RiotApiConstants.RIOT_API_URL_KR, RiotApiConstants.RIOT_API_SUMMONER_INFO, arg1, RiotApiConstants.API_KEY);
            case "matchInfo":   //arg1 : matchId
                return String.format("%s%s%s?api_key=%s", RiotApiConstants.RIOT_API_URL, RiotApiConstants.RIOT_API_MATCH, arg1, RiotApiConstants.API_KEY);
            case "leagueInfo":  //arg1 : summonerId
                return String.format("%s%s%s%s?api_key=%s", RiotApiConstants.RIOT_API_URL_KR, RiotApiConstants.RIOT_API_LEAGUE, "entries/by-summoner/", arg1, RiotApiConstants.API_KEY);
            case "accountByPid":      //arg1 : puuid
                return String.format("%s%s%s?api_key=%s", RiotApiConstants.RIOT_API_URL, RiotApiConstants.RIOT_API_ACCOUNT_PID, arg1, RiotApiConstants.API_KEY);                
            default:
                return null;
        }
    }

    // url : 타입에 따라 url 생성
    // nameTag - 소환사 닉네임으로 소환사 정보 (/account/v1/accounts/by-riot-id/{gameName}/{tagLine})
    public static String urlConvertor(String type, String arg1, String arg2){
        switch(type){
            case "nameTag":  //arg1 : gameName, arg2 : tagLine
                return String.format("%s%s%s/%s?api_key=%s", RiotApiConstants.RIOT_API_URL, RiotApiConstants.RIOT_API_ACCOUNT_RID, arg1, arg2, RiotApiConstants.API_KEY);
            default:
                return null;
        }
    }

    // url : 타입에 따라 url 생성
    // matchList - puuid로 매치 목록 조회 (/match/v5/matchlists/by-puuid/{puuid}/ids?start={start}&count={count})
    public static String urlConvertor(String type, String arg1, int start, int count){
        switch(type){
            case "matchList":   //arg1 : puuid, start(default : 0), count(default : 20, max : 100) 결과가 없을 경우 빈 배열 반환
                return String.format("%s%sby-puuid/%s/ids?start=%d&count=%d&api_key=%s", RiotApiConstants.RIOT_API_URL, RiotApiConstants.RIOT_API_MATCH, arg1, start, count, RiotApiConstants.API_KEY);
            default:
                return null;
        }
    }
}
