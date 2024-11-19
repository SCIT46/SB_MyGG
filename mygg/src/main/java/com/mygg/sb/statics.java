package com.mygg.sb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import io.github.cdimascio.dotenv.Dotenv;

public class statics {
	public static Dotenv dotenv = Dotenv.load();
	public static final String API_KEY = dotenv.get("RIOT_API_PERSONAL_KEY");
	public final static String RIOT_API_URL = "https://asia.api.riotgames.com";
	public final static String RIOT_API_URL_KR = "https://kr.api.riotgames.com";
	public final static String RIOT_API_MATCH = "/lol/match/v5/matches/";
	public final static String RIOT_API_USER_UID = "/riot/account/v1/accounts/by-riot-id/";
	public final static String RIOT_API_SUMNONER_INFO = "/lol/summoner/v4/summoners/by-puuid/";

	// public static String getVersion(){
	// 	String versionJSON = new BufferedReader(new InputStreamReader(new
	// 	URL("https://ddragon.leagueoflegends.com/api/versions.json").openStream(),"UTF-8")).readLine();
	// }
	// public static String getLanguage(){
	// 	String languageJSON = new BufferedReader(new InputStreamReader(new
	// 	URL("https://ddragon.leagueoflegends.com/cdn/languages.json").openStream(),"UTF-8")).readLine();
	// }
	public static String url2Json(String url) throws Exception  {
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

	public static String nametag2Pid(String gameName, String tagLine) throws Exception {
        //이름에 공백이 있으면 %20(URL escape code(space))로 변환
        gameName = gameName.replace(" ","%20");
		// API 주소값
        String user_url = String.format("%s%s%s/%s?api_key=%s", statics.RIOT_API_URL, statics.RIOT_API_USER_UID, gameName, tagLine,
                statics.API_KEY);

        //url을 json으로 변환
        String userJSON = statics.url2Json(user_url);

        // ======================================================================================================================

        /* JSON Parsing 부 */

        // JSON 데이터를 분석해주는 JSONParser 객체 생성
        JSONParser parser = new JSONParser();

        // String 형태의 JSON 데이터를 JSONObject(HashMap)형 jsonObject로 변환
        JSONObject jsonObject = (JSONObject) parser.parse(userJSON);

        // jsonObject의 JSON Key값으로 모든 데이터 조회
        String puuid = (String)jsonObject.get("puuid");
        System.out.println(puuid);
		return puuid;
	}
}
