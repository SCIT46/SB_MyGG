package com.mygg.sb.statics.api;

import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mygg.sb.statics.util.UrlToJson;

public class RiotApiClient {
    // public static String getVersion(){
    // String versionJSON = new BufferedReader(new InputStreamReader(new
    // URL("https://ddragon.leagueoflegends.com/api/versions.json").openStream(),"UTF-8")).readLine();
    // }
    // public static String getLanguage(){
    // String languageJSON = new BufferedReader(new InputStreamReader(new
    // URL("https://ddragon.leagueoflegends.com/cdn/languages.json").openStream(),"UTF-8")).readLine();
    // }

    

    // API : gameName(String)과 tagLine(String)를 주면 puuid(String)로 변환
    public static String nameTagToPid(String gameName, String tagLine) throws Exception {
        // 이름을 URL인코딩하여 처리(영어 외에 처리 안되는 문제 해결)
        gameName = URLEncoder.encode(gameName, "UTF-8");
        tagLine = URLEncoder.encode(tagLine, "UTF-8");

        // url을 json으로 변환
        String userJSON = UrlToJson.urlToJson(UrlToJson.urlConvertor("nameTag", gameName, tagLine));

        // JSON 데이터를 분석해주는 JSONParser 객체 생성
        JSONParser parser = new JSONParser();

        // String 형태의 JSON 데이터를 JSONObject(HashMap)형 jsonObject로 변환
        JSONObject jsonObject = (JSONObject) parser.parse(userJSON);

        // jsonObject의 JSON Key값으로 모든 데이터 조회
        String puuid = (String) jsonObject.get("puuid");

        return puuid; // size : 78
    }

    // API : puuid(String)를 주면 gameName, tagLine(String[])으로 변환
    public static String[] pidToNametag(String pid) throws Exception {

        // url을 json으로 변환
        String userJSON = UrlToJson.urlToJson(UrlToJson.urlConvertor("accountByPid", pid));

        // JSON 데이터를 분석해주는 JSONParser 객체 생성
        JSONParser parser = new JSONParser();

        // String 형태의 JSON 데이터를 JSONObject(HashMap)형 jsonObject로 변환
        JSONObject jsonObject = (JSONObject) parser.parse(userJSON);

        // jsonObject의 JSON Key값으로 모든 데이터 조회
        String gameName = (String) jsonObject.get("gameName");
        String tagLine = (String) jsonObject.get("tagLine");

        return new String[] {gameName,tagLine};
    }

    // API : puuid(String)로 summonerId(String) 변환
    public static String pidToSummonerId(String pid) throws Exception {
        
        //String user_url = String.format("%s%s%s?api_key=%s", RiotApiConstants.RIOT_API_URL, RiotApiConstants.RIOT_API_SUMMONER_INFO, pid,
        //        RiotApiConstants.API_KEY);

        // url을 json으로 변환
        String userJSON = UrlToJson.urlToJson(UrlToJson.urlConvertor("summonerInfo", pid));

        // JSON 데이터를 분석해주는 JSONParser 객체 생성
        JSONParser parser = new JSONParser();

        // String 형태의 JSON 데이터를 JSONObject(HashMap)형 jsonObject로 변환
        JSONObject jsonObject = (JSONObject) parser.parse(userJSON);

        // jsonObject의 JSON Key값으로 모든 데이터 조회
        String summonerId = (String) jsonObject.get("id");

        return summonerId; // size : 63(max)
    }

    // API : SummonerId(String), isExp(Boolean)로 소환사 리그정보(JSONObject) 변환
    public static JSONObject getLeagueBySummonerId(String summonerId) throws Exception {
        // puuid로 소환사의 정보를 받아오는 API
        //String user_url = String.format("%s%s%s%s?api_key=%s", RiotApiConstants.RIOT_API_URL_KR, RiotApiConstants.RIOT_API_LEAGUE, "entries/by-summoner/",
        //        summonerId, RiotApiConstants.API_KEY);


        // url을 json으로 변환
        String userJSON = UrlToJson.urlToJson(UrlToJson.urlConvertor("leagueInfo", summonerId));

        /* JSON Parsing 부 */

        // JSON 데이터를 분석해주는 JSONParser 객체 생성
        JSONParser parser = new JSONParser();

        // JSON 데이터가 Array로 오기 때문에 JSONArray로 변환
        JSONArray jsonArray = (JSONArray) parser.parse(userJSON);
    
        // 리그 정보가 없는 경우 기본값을 가진 JSONObject 반환
        if (jsonArray.isEmpty()) {
            JSONObject defaultObj = new JSONObject();
            defaultObj.put("tier", "UNRANKED");
            defaultObj.put("rank", "");
            defaultObj.put("leaguePoints", 0L);
            defaultObj.put("wins", 0L);
            defaultObj.put("losses", 0L);
            return defaultObj;
        }
    
        return (JSONObject) jsonArray.get(0);
    }

    // API : puuid(String)로 소환사 정보(JSONObject) 변환
    public static JSONObject getSummonerInfo(String puuid) throws Exception{
        // API 주소값
        //String request_url = String.format("%s%s%s?api_key=%s",RiotApiConstants.RIOT_API_URL_KR,RiotApiConstants.RIOT_API_SUMMONER_INFO,this.puuid,RiotApiConstants.API_KEY);
        
        //url을 json으로 변환
        String summoJSON = UrlToJson.urlToJson(UrlToJson.urlConvertor("summonerInfo", puuid));

		// JSON 데이터를 분석해주는 JSONParser 객체 생성
		JSONParser parser = new JSONParser();

		// 소환사 정보 JSON
        return (JSONObject) parser.parse(summoJSON);
    }

    // API : matchId(String)로 매치 정보(JSONObject) 변환
    public static JSONObject getMatchInfo(String matchId) throws Exception{
        // API 주소값
        //String request_url = String.format("%s%s%s?api_key=%s",RiotApiConstants.RIOT_API_URL,RiotApiConstants.RIOT_API_MATCH,matchId,RiotApiConstants.API_KEY);
        
        //url을 json으로 변환
        String matchJSON = UrlToJson.urlToJson(UrlToJson.urlConvertor("matchInfo", matchId));

        // JSON 데이터를 분석해주는 JSONParser 객체 생성
        JSONParser parser = new JSONParser();

        return (JSONObject) parser.parse(matchJSON);
    }
}
