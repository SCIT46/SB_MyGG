package user;

import java.time.LocalDateTime;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mygg.sb.statics;

public class UserApi {
    String puuid;
    String summonerId;
    //유저 이미지(초상화)
    int profileIconId;
    //유저 이름
    String gameName; 
    //유저 태그
    String tagLine;
    //유저 레벨
    int summonerLevel;
    //유저 등급
    String tier;
    String rank;
    //유저 lp점수
    int leaguePoints;
    //유저 승패 
    int wins;
    int losses;
    //최근 갱신 날짜
    LocalDateTime revisionDate;


    public UserApi(String gameName, String tagLine) throws Exception{
        // 이름과 태그를 puuid로 변환
        puuid = statics.nameTagToPid(gameName,tagLine);

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

        // jsonObject의 값 추출
        summonerId = (String) jsonObject.get("id");
        profileIconId = (int)(long) jsonObject.get("profileIconId");
        revisionDate = statics.epochToLocalDateTime((long)jsonObject.get("revisionDate"));
        summonerLevel = (int)(long) jsonObject.get("summonerLevel");


		// // jsonObject의 JSON Key값으로 모든 데이터 조회
        // int cnt=0;
		// for (Object key : jsonObject.keySet()) {
        //     // key 값으로 받은 데이터를 value에 저장
        //     Object value = jsonObject.get(key);
        //     switch(cnt){
        //         case 1: profileIconId = (int)(long)value;    break;  //profileIconId
        //         case 2: System.out.println(value); revisionDate = statics.epochToLocalDateTime((Long)value);  break;
        //         case 4: summonerId = (String)value;          break;
        //         case 5: summonerLevel = (int)(long)value;    break; 
        //     }
        //     //if(key.equals("profileIconId")){}
        //     cnt++;
        //     //System.out.println(key+" : "+value);
        // }

        // summonerId를 통해 리그정보 JSON 받아옴
        JSONObject jsonObject2 = statics.getLeagueBySummonerId(summonerId);
        tier = (String) jsonObject2.get("tier");
        rank = (String) jsonObject2.get("rank");
        leaguePoints = (int)(long) jsonObject2.get("leaguePoints");
        wins = (int)(long) jsonObject2.get("wins");
        losses = (int)(long) jsonObject2.get("losses");


        this.gameName = gameName;
        this.tagLine = tagLine;
        // HashMap<String, String> userMap = statics.pidToNametag(puuid);
        // System.out.println(userMap);
        // this.gameName = userMap.get("gameName");
        // this.tagLine = userMap.get("tagLine");

	}

	public static void main(String[] args) throws Exception {
		UserApi user  = new UserApi("T1 Gumayusi","KR1");
        System.out.println(user.puuid);
        System.out.println(user.summonerId);
        System.out.println(user.profileIconId);
        System.out.println(user.gameName);
        System.out.println(user.tagLine);
        System.out.println(user.summonerLevel);
        System.out.println(user.tier);
        System.out.println(user.rank);
        System.out.println(user.leaguePoints);
        System.out.println(user.wins);
        System.out.println(user.losses);
        System.out.println(user.revisionDate);
	}

}
