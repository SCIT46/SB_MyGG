package com.mygg.sb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import io.github.cdimascio.dotenv.Dotenv;

public class statics {
    public static Dotenv dotenv = Dotenv.load();
    public static final String API_KEY = "RGAPI-f89611ea-e446-4cd6-8ea0-3a17be2ba38e";
    // =============================================================================================
    public final static String RIOT_API_URL = "https://asia.api.riotgames.com";
    public final static String RIOT_API_URL_KR = "https://kr.api.riotgames.com";
    // =============================================================================================
    public final static String RIOT_API_ACCOUNT_RID = "/riot/account/v1/accounts/by-riot-id/"; // public match
    public final static String RIOT_API_ACCOUNT_PID = "/riot/account/v1/accounts/by-puuid/"; // public match
    public final static String RIOT_API_MATCH = "/lol/match/v5/matches/"; // public match
    public final static String RIOT_API_MATCH_P = "/lol/rso-match/v1/matches/"; // private match
    public final static String RIOT_API_SUMMONER_INFO = "/lol/summoner/v4/summoners/by-puuid/";
    public final static String RIOT_API_LEAGUE = "/lol/league/v4/"; // Iron ~ Diamond
    public final static String RIOT_API_LEAGUE_EXP = "/lol/league-exp/v4/"; // Master ~ Challenger
    // =============================================================================================

    // public static String getVersion(){
    // String versionJSON = new BufferedReader(new InputStreamReader(new
    // URL("https://ddragon.leagueoflegends.com/api/versions.json").openStream(),"UTF-8")).readLine();
    // }
    // public static String getLanguage(){
    // String languageJSON = new BufferedReader(new InputStreamReader(new
    // URL("https://ddragon.leagueoflegends.com/cdn/languages.json").openStream(),"UTF-8")).readLine();
    // }

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

    // API : gameName(String)과 tagLine(String)를 주면 puuid(String)로 변환
    public static String nameTagToPid(String gameName, String tagLine) throws Exception {
        // 이름을 URL인코딩하여 처리(영어 외에 처리 안되는 문제 해결)
        gameName = URLEncoder.encode(gameName, "UTF-8");

        // API 주소값
        String user_url = String.format("%s%s%s/%s?api_key=%s", RIOT_API_URL, RIOT_API_ACCOUNT_RID, gameName, tagLine,
                API_KEY);

        // url을 json으로 변환
        String userJSON = urlToJson(user_url);

        /* JSON Parsing 부 */

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
        // API 주소값
        String user_url = String.format("%s%s%s?api_key=%s", RIOT_API_URL, RIOT_API_ACCOUNT_PID, pid,
                API_KEY);

        // url을 json으로 변환
        String userJSON = urlToJson(user_url);

        /* JSON Parsing 부 */

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
        
        String user_url = String.format("%s%s%s?api_key=%s", RIOT_API_URL, RIOT_API_SUMMONER_INFO, pid,
                API_KEY);

        // url을 json으로 변환
        String userJSON = urlToJson(user_url);

        /* JSON Parsing 부 */

        // JSON 데이터를 분석해주는 JSONParser 객체 생성
        JSONParser parser = new JSONParser();

        // String 형태의 JSON 데이터를 JSONObject(HashMap)형 jsonObject로 변환
        JSONObject jsonObject = (JSONObject) parser.parse(userJSON);

        // jsonObject의 JSON Key값으로 모든 데이터 조회
        String summonerId = (String) jsonObject.get("id");

        return summonerId; // size : 63(max)
    }

    // API : SummonerId(String)로 소환사 정보(JSONObject) 변환
    public static JSONObject getLeagueBySummonerId(String summonerId) throws Exception {
        // puuid로 소환사의 정보를 받아오는 API
        String user_url = String.format("%s%s%s%s?api_key=%s", RIOT_API_URL_KR, RIOT_API_LEAGUE, "entries/by-summoner/",
                summonerId, API_KEY);

        // url을 json으로 변환
        String userJSON = urlToJson(user_url);

        /* JSON Parsing 부 */

        // JSON 데이터를 분석해주는 JSONParser 객체 생성
        JSONParser parser = new JSONParser();

        // Array안에 담긴 Object 형식으로 오기 때문에 parsing
        JSONObject jsonObject = (JSONObject)((JSONArray) parser.parse(userJSON)).get(0);

        return jsonObject;
    }

    // 시간을 Epoch(TimeStamp)으로
    public static long localDateTimeToEpoch(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    // Epoch(TimeStamp)를 시간으로
    public static LocalDateTime epochToLocalDateTime(long epochMillis) {
        Instant instant = Instant.ofEpochMilli(epochMillis);
        return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
    }
}
