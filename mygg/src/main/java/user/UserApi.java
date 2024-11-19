package user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mygg.sb.statics;

public class UserApi {
    //유저 이미지(초상화)
    int profileIconId;
    //유저 이름
    String gameName; 
    //유저 태그
    String tagLine;
    //유저 레벨
    int summonerLevel;
    //유저 등급

    //유저 lp점수

    //유저 승패 
    Boolean win;
    //최근 갱신 날짜
    LocalDateTime revisionDate;


    public UserApi(String gameName, String tagLine) throws Exception{
        // 이름과 태그를 puuid로 변환
        String puuid = statics.nameTagToPid(gameName,tagLine);

        // API 주소값
        String request_url = String.format("%s%s%s?api_key=%s",statics.RIOT_API_URL_KR,statics.RIOT_API_SUMMONER_INFO,puuid,statics.API_KEY);
        
        //url을 json으로 변환
        String summoJSON = statics.urlToJson(request_url);

		// ======================================================================================================================

		/* JSON Parsing 부 */

		// JSON 데이터를 분석해주는 JSONParser 객체 생성
		JSONParser parser = new JSONParser();

		// String 형태의 JSON 데이터를 JSONObject(HashMap)형 jsonObject로 변환
		JSONObject jsonObject = (JSONObject) parser.parse(summoJSON);

		// jsonObject의 JSON Key값으로 모든 데이터 조회
        int cnt=0;
		for (Object key : jsonObject.keySet()) {
            // key 값으로 받은 데이터를 value에 저장
            Object value = jsonObject.get(key);
            switch(cnt){
                case 1: profileIconId = (int)(long)value;    break;  //profileIconId
                case 2: System.out.println(value); revisionDate = statics.epochToLocalDateTime((Long)value);  break;
                case 5: summonerLevel = (int)(long)value;    break; 
            }
            //if(key.equals("profileIconId")){}
            cnt++;
            //System.out.println(key+" : "+value);
        }
        System.out.println(profileIconId);
        System.out.println(statics.localDateTimeToEpoch(revisionDate));
        System.out.println(revisionDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS")));
        System.out.println(summonerLevel);

	}

	public static void main(String[] args) throws Exception {
		UserApi user  = new UserApi("T1 Gumayusi","KR1");
	}

}
