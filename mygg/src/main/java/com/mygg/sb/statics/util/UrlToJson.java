package com.mygg.sb.statics.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mygg.sb.exception.custom.RiotApiBadRequest;
import com.mygg.sb.exception.custom.RiotApiForbidden;
import com.mygg.sb.exception.custom.RiotApiNotFound;
import com.mygg.sb.exception.custom.RiotApiTooManyRequests;
import com.mygg.sb.statics.api.RiotApiConstants;

public class UrlToJson {
    // JSON : url을 json으로 변환
    public static String urlToJson(String url) throws Exception {
        /* JSON 파일 불러오기/변환 */

        // API 키를 요청할 주소값을 URL 타입으로 생성
        URL request_url = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) request_url.openConnection();

        //System.out.println("==== UrloJson.java URL: " + url);
        // HTTP 응답 코드 확인
        int responseCode = connection.getResponseCode();

        // 응답 스트림 선택 (성공: InputStream, 실패: ErrorStream)
        InputStream stream = (responseCode >= 200 && responseCode < 300)
                ? connection.getInputStream()
                : connection.getErrorStream();

        // URL을 openStream() UTF-8을 통해 열고 받은 데이터를 inputStreamReader를 통해 받아
        // BufferedReader를 통해 bf에 저장
        BufferedReader bf = new BufferedReader(new InputStreamReader(stream, "UTF-8"));

        // bf에 저장한 데이터를 String 형태로 저장(JSONParser가 String으로 된 JSON 데이터를 분석)
        String userJSON = bf.readLine();

        // 버퍼 닫기
        bf.close();

        // 연결 종료
        connection.disconnect();

        // 에러 응답인 경우 예외 발생
        switch (responseCode) {
            case 200:
                return userJSON;
            case 403:
                throw new RiotApiForbidden("Riot API 응답이 유효하지 않습니다.");
            case 404:
                throw new RiotApiNotFound("Riot API 응답이 올바르지 않습니다.");
            case 400:
                throw new RiotApiBadRequest("Riot API 요청이 올바르지 않습니다.");
            case 429:
                // 429: api리퀘스트 초과
                // 25/2/5 추가: 라이엇 API 리미트 초과 → Retry-After 값을 가져옴
                String retryAfter         = connection.getHeaderField("Retry-After");
                String xAppRateLimit      = connection.getHeaderField("X-App-Rate-Limit");
                String xAppRateLimitCount = connection.getHeaderField("X-App-Rate-Limit-Count");
                throw new RiotApiTooManyRequests("Riot API의 요청 제한을 초과했습니다.", 
                        xAppRateLimit, retryAfter, xAppRateLimitCount);
            default:    //unexpected exception
                throw new RuntimeException("Riot API Error 코드: " + responseCode + ", 응답: " + userJSON);
        }
    }

    // dataVersion - 데이터 버전 조회
    // dataLanguage - 데이터 언어 조회
    public static String urlConvertor(String type) {
        switch (type) {
            case "dataVersion":
                return RiotApiConstants.RIOT_DATA_API_URL + RiotApiConstants.RIOT_DATA_API_VERSION;
            case "dataLanguage":
                return RiotApiConstants.RIOT_DATA_API_URL + RiotApiConstants.RIOT_DATA_API_LANGUAGE;
            case "item": // arg1 : itemId
                return RiotApiConstants.RIOT_DATA_API_URL + RiotApiConstants.RIOT_DATA_API_ITEM;
            case "champion": // arg1 : championId
                return RiotApiConstants.RIOT_DATA_API_URL + RiotApiConstants.RIOT_DATA_API_CHAMPION;
            case "runesReforged": // arg1 : rune
                return RiotApiConstants.RIOT_DATA_API_URL + RiotApiConstants.RIOT_DATA_API_RUNE;
            default:
                return null;
        }
    }

    // url : 타입에 따라 url 생성
    // summonerInfo - puuid로 소환사 정보
    // (RIOT_API_URL_KR/account/v1/summoners/by-puuid/{puuid})
    // matchInfo - matchId로 매치 정보 (RIOT_API_URL/match/v5/matches/{matchId})
    // leagueInfo - summonerId로 리그 정보
    // (RIOT_API_URL_KR/league/v4/entries/by-summoner/{summonerId})
    // accountByPid - puuid로 소환사 정보
    // (RIOT_API_URL/account/v1/accounts/by-puuid/{puuid})
    // itemImg - 아이템 이미지 조회 (RIOT_DATA_API_URL/img/item/{itemId}.png)
    // championImg - 챔피언 이미지 조회 (RIOT_DATA_API_URL/img/champion/{championId}.png)
    public static String urlConvertor(String type, String arg1) {
        switch (type) {
            case "summonerInfo": // arg1 : puuid
                return String.format("%s%s%s?api_key=%s", RiotApiConstants.RIOT_API_URL_KR,
                        RiotApiConstants.RIOT_API_SUMMONER_INFO, arg1, RiotApiConstants.API_KEY);
            case "matchInfo": // arg1 : matchId
                return String.format("%s%s%s?api_key=%s", RiotApiConstants.RIOT_API_URL,
                        RiotApiConstants.RIOT_API_MATCH, arg1, RiotApiConstants.API_KEY);
            case "leagueInfo": // arg1 : summonerId
                return String.format("%s%s%s%s?api_key=%s", RiotApiConstants.RIOT_API_URL_KR,
                        RiotApiConstants.RIOT_API_LEAGUE, "entries/by-summoner/", arg1, RiotApiConstants.API_KEY);
            case "accountByPid": // arg1 : puuid
                return String.format("%s%s%s?api_key=%s", RiotApiConstants.RIOT_API_URL,
                        RiotApiConstants.RIOT_API_ACCOUNT_PID, arg1, RiotApiConstants.API_KEY);
            case "itemImg": // arg1 : itemId
                return RiotApiConstants.RIOT_DATA_API_URL + RiotApiConstants.RIOT_DATA_API_ITEM_IMG + arg1 + ".png";
            case "championImg": // arg1 : championId
                return RiotApiConstants.RIOT_DATA_API_URL + RiotApiConstants.RIOT_DATA_API_CHAMPION_IMG + arg1 + ".png";
            default:
                return null;
        }
    }

    // url : 타입에 따라 url 생성
    // nameTag - 소환사 닉네임으로 소환사 정보
    // (/account/v1/accounts/by-riot-id/{gameName}/{tagLine})
    public static String urlConvertor(String type, String arg1, String arg2) {
        switch (type) {
            case "nameTag": // arg1 : gameName, arg2 : tagLine
                return String
                        .format("%s%s%s/%s?api_key=%s", RiotApiConstants.RIOT_API_URL,
                                RiotApiConstants.RIOT_API_ACCOUNT_RID, arg1, arg2, RiotApiConstants.API_KEY)
                        .replaceAll("\\+", "%20");
            default:
                return null;
        }
    }

    // url : 타입에 따라 url 생성
    // matchList - puuid로 매치 목록 조회
    // (/match/v5/matchlists/by-puuid/{puuid}/ids?start={start}&count={count})
    public static String urlConvertor(String type, String arg1, int start, int count) {
        switch (type) {
            case "matchList": // arg1 : puuid, start(default : 0), count(default : 20, max : 100) 결과가 없을 경우 빈
                              // 배열 반환
                return String.format("%s%sby-puuid/%s/ids?start=%d&count=%d&api_key=%s", RiotApiConstants.RIOT_API_URL,
                        RiotApiConstants.RIOT_API_MATCH, arg1, start, count, RiotApiConstants.API_KEY);
            default:
                return null;
        }
    }

    // (/match/v5/matchlists/by-puuid/{puuid}/ids?start={start}&count={count}&startTime={startDate}&endTime={endDate})
    public static String urlConvertor(String type, String arg1, int start, int count, long startDate, long endDate) {
        switch (type) {
            case "matchList": // arg1 : puuid, start(default : 0), count(default : 20, max : 100),
                              // startDate(default : null), endDate(default : null) 결과가 없을 경우 빈 배열 반환
                return String.format("%s%sby-puuid/%s/ids?start=%d&count=%d&startTime=%d&endTime=%d&api_key=%s",
                        RiotApiConstants.RIOT_API_URL, RiotApiConstants.RIOT_API_MATCH, arg1,
                        start, count, startDate, endDate, RiotApiConstants.API_KEY);
            default:
                return null;
        }
    }

    public static String urlConvertor(String type, String arg1, int start, int count,
            int startDate, int endDate, int queue, String gameType) {
        switch (type) {
            case "matchList": // arg1 : puuid, start(default : 0), count(default : 20, max : 100),
                              // startDate(default : null), endDate(default : null), queue(default : null),
                              // type(ranked, normal, tourney, tutorial) 결과가 없을 경우 빈 배열 반환
                return String.format(
                        "%s%sby-puuid/%s/ids?start=%d&count=%d&startTime=%d&endTime=%d&queue=%d&type=%s&api_key=%s",
                        RiotApiConstants.RIOT_API_URL, RiotApiConstants.RIOT_API_MATCH, arg1,
                        start, count, startDate, endDate, queue, gameType, RiotApiConstants.API_KEY);
            default:
                return null;
        }
    }

    public static String[] jsonArrayToStringArray(JSONArray jsonArray) {
        if (jsonArray == null)
            return null;
        String[] stringArray = new String[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            stringArray[i] = (String) jsonArray.get(i);
        }
        return stringArray;
    }

    public static HashMap<String, Number> jsonObjectToHashMap(JSONObject jsonObject) {
        if (jsonObject == null)
            return null;
        HashMap<String, Number> hashMap = new HashMap<>();
        for (Object key : jsonObject.keySet()) {
            if (jsonObject.get(key) instanceof Long)
                hashMap.put((String) key, ((Long) jsonObject.get(key)).intValue());
            else if (jsonObject.get(key) instanceof Double)
                hashMap.put((String) key, ((Double) jsonObject.get(key)).floatValue());
        }
        return hashMap;
    }
}
