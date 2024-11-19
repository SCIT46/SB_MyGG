package com.mygg.sb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import io.github.cdimascio.dotenv.Dotenv;

public class statics {
	public static Dotenv dotenv = Dotenv.load();
	public static final String API_KEY = dotenv.get("RIOT_API_PERSONAL_KEY");
	public final static String RIOT_API_URL = "https://asia.api.riotgames.com";
	public final static String RIOT_API_URL_KR = "https://kr.api.riotgames.com";
	public final static String RIOT_API_MATCH = "/lol/match/v5/matches/";
	public final static String RIOT_API_SUMMONER_INFO = "/lol/summoner/v4/summoners/by-puuid/";


	// public static String getVersion(){
        // 	String versionJSON = new BufferedReader(new InputStreamReader(new
        // 	URL("https://ddragon.leagueoflegends.com/api/versions.json").openStream(),"UTF-8")).readLine();
	// }
	// public static String getLanguage(){
        // 	String languageJSON = new BufferedReader(new InputStreamReader(new
        // 	URL("https://ddragon.leagueoflegends.com/cdn/languages.json").openStream(),"UTF-8")).readLine();
	// }

	public static String urlToJson(String url) throws Exception  {
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

    //gameName(String)과 tagLine(String)를 주면 puuid(String)로 변환
	public static String nameTagToPid(String gameName, String tagLine) throws Exception {
        //이름을 URL인코딩하여 처리(영어 외에 처리 안되는 문제 해결)
        gameName = URLEncoder.encode(gameName,"UTF-8");

		// API 주소값
        String user_url = String.format("%s%s%s/%s?api_key=%s", statics.RIOT_API_URL, "/riot/account/v1/accounts/by-riot-id/", gameName, tagLine,
                statics.API_KEY);

        //url을 json으로 변환
        String userJSON = statics.urlToJson(user_url);

        // ======================================================================================================================

        /* JSON Parsing 부 */

        // JSON 데이터를 분석해주는 JSONParser 객체 생성
        JSONParser parser = new JSONParser();

        // String 형태의 JSON 데이터를 JSONObject(HashMap)형 jsonObject로 변환
        JSONObject jsonObject = (JSONObject) parser.parse(userJSON);

        // jsonObject의 JSON Key값으로 모든 데이터 조회
        String puuid = (String)jsonObject.get("puuid");
        
		return puuid;
	}

    //puuid(String)를 주면 gameName, tagLine(String[])으로 변환
    public static String[] pidToNametag(String pid) throws Exception {
		// API 주소값
        String user_url = String.format("%s%s%s?api_key=%s", statics.RIOT_API_URL, "/riot/account/v1/accounts/by-puuid/", pid,
                statics.API_KEY);

        //url을 json으로 변환
        String userJSON = statics.urlToJson(user_url);

        // ======================================================================================================================

        /* JSON Parsing 부 */

        // JSON 데이터를 분석해주는 JSONParser 객체 생성
        JSONParser parser = new JSONParser();

        // String 형태의 JSON 데이터를 JSONObject(HashMap)형 jsonObject로 변환
        JSONObject jsonObject = (JSONObject) parser.parse(userJSON);

        // jsonObject의 JSON Key값으로 모든 데이터 조회
        String gameName = (String)jsonObject.get("gameName");
        String tagLine = (String)jsonObject.get("tagLine");
        
		return new String[]{gameName,tagLine} ;
	}

    //시간을 Epoch(TimeStamp)으로
    public static long localDateTimeToEpoch(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    //Epoch(TimeStamp)를 시간으로
    public static LocalDateTime epochToLocalDateTime(long epochMillis) {
        Instant instant = Instant.ofEpochMilli(epochMillis);
        return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
    }
}
