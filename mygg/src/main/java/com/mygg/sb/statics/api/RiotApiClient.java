package com.mygg.sb.statics.api;

import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mygg.sb.statics.util.UrlToJson;

public class RiotApiClient {
    // API : 최신 데이터 버전 조회(Array로 이루어진 JSON 데이터에서 첫번째(최신) 값 반환);
    public static String getLatestVersion() throws Exception{
        String versionJSON = UrlToJson.urlToJson(UrlToJson.urlConvertor("dataVersion"));
        return (String) ((JSONArray) new JSONParser().parse(versionJSON)).get(0);
    }

    // API : 데이터 언어 조회(JSON 데이터 반환);
    public static String getLanguage() throws Exception{
        String languageJSON = UrlToJson.urlToJson(UrlToJson.urlConvertor("dataLanguage"));
        return languageJSON;
    }

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
        // url을 json으로 변환
        String userJSON = UrlToJson.urlToJson(UrlToJson.urlConvertor("leagueInfo", summonerId));

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
        //url을 json으로 변환
        String summoJSON = UrlToJson.urlToJson(UrlToJson.urlConvertor("summonerInfo", puuid));

		// JSON 데이터를 분석해주는 JSONParser 객체 생성
		JSONParser parser = new JSONParser();

		// 소환사 정보 JSON
        return (JSONObject) parser.parse(summoJSON);
    }

    // API : matchId(String)로 매치 정보(JSONObject) 변환
    public static JSONObject getMatchInfo(String matchId) throws Exception{
        //url을 json으로 변환
        String matchJSON = UrlToJson.urlToJson(UrlToJson.urlConvertor("matchInfo", matchId));

        // JSON 데이터를 분석해주는 JSONParser 객체 생성
        JSONParser parser = new JSONParser();

        return (JSONObject) parser.parse(matchJSON);
    }

    // API : puuid(String)로 매치 목록(String[]) 변환
    public static String[] getMatchList(String puuid) throws Exception{
        // url을 json으로 변환
        String matchJSON = UrlToJson.urlToJson(UrlToJson.urlConvertor("matchList", puuid, 0, 20)); 

        // JSON 데이터를 분석해주는 JSONParser 객체 생성
        JSONParser parser = new JSONParser();

        // JSON 데이터가 Array로 오기 때문에 JSONArray로 변환
        JSONArray jsonArray = (JSONArray) parser.parse(matchJSON);

        // JSONArray를 String[]로 변환
        String[] matchList = new String[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            matchList[i] = (String) jsonArray.get(i);
        }

        return matchList;
    }  

    // API : itemId(String)로 아이템 정보(JSONObject) 변환
    public static JSONObject getItem(String itemId) throws Exception{
        //if(itemId == null) return null;
        // url을 json으로 변환
        String itemJSON = UrlToJson.urlToJson(UrlToJson.urlConvertor("item"));

        // JSON 데이터를 분석해주는 JSONParser 객체 생성
        JSONParser parser = new JSONParser();
        
        // 아이템 정보 JSON
        JSONObject jsonObject = (JSONObject) parser.parse(itemJSON);

        // 아이템 정보 JSON에서 아이템 아이디로 해당하는 정보 조회
        return (JSONObject) ((JSONObject) jsonObject.get("data")).get(itemId);
    }

    // API : championId(String)로 챔피언 정보(JSONObject) 변환
    public static JSONObject getChampion(String championId) throws Exception{
        // url을 json으로 변환
        String championJSON = UrlToJson.urlToJson(UrlToJson.urlConvertor("champion", championId));

        // JSON 데이터를 분석해주는 JSONParser 객체 생성
        JSONParser parser = new JSONParser();

        return (JSONObject) parser.parse(championJSON);
    }
}
