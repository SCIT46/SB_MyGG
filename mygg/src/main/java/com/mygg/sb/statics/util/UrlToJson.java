package com.mygg.sb.statics.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

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
}
