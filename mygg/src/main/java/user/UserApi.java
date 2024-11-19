package user;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mygg.sb.statics;

public class UserApi {
    //유저 이미지(초상화)
    //유저 이름
    //유저 태그
    //유저 레벨
    //유저 등급
    //유저 lp점수
    //유저 승패 

    public UserApi(String gameName, String tagLine) throws Exception{
        // 이름과 태그를 puuid로 변환
        String puuid = statics.nametag2Pid(gameName,tagLine);

        // API 주소값
        String request_url = String.format("%s%s%s?api_key=%s",statics.RIOT_API_URL_KR,statics.RIOT_API_SUMNONER_INFO,puuid,statics.API_KEY);
        
        //url을 json으로 변환
        String summoJSON = statics.url2Json(request_url);

		// ======================================================================================================================

		/* JSON Parsing 부 */

		// JSON 데이터를 분석해주는 JSONParser 객체 생성
		JSONParser parser = new JSONParser();

		// String 형태의 JSON 데이터를 JSONObject(HashMap)형 jsonObject로 변환
		JSONObject jsonObject = (JSONObject) parser.parse(summoJSON);

		// jsonObject의 JSON Key값으로 모든 데이터 조회
		for (Object key : jsonObject.keySet()) {
            // key 값으로 받은 데이터를 value에 저장
            Object value = jsonObject.get(key);
            System.out.println(key+" : "+value);
        }

	}

	public static void main(String[] args) throws Exception {
		UserApi user  = new UserApi("Hide on bush","KR1");
	}

}
