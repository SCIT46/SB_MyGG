package com.mygg.sb.match;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mygg.sb.statics.api.RiotApiConstants;
import com.mygg.sb.statics.util.UrlToJson;

	/*
	* Field
	*  유저 경기 정보(info.participants.) //0~4 0팀 / 5~9 1팀
	*    승패여부(win)
	*    포지션(teamPosition/individualPosition/lane)
	*    소환사명(riotIdGameName+riotIdTagline)
	*    챔피언(championName) //-> (https://ddragon.leagueoflegends.com/cdn/14.22.1/data/ko_KR/champion.json).data.{championName}.name
	*    KDA(challenges.kda)
	*    전체딜량(totalDamageDealt)
	*    분당골드(challenges.goldPerMinute)
	*    남은골드(goldEarned-goldSpent)
	*/

public class PrivateMatch {
	// 매치 내 플레이어 식별자(participants) 를 저장해줄 List
	ArrayList<String> player;

	public PrivateMatch(String matchId) {
		try {
			/* JSON 파일 불러오기/변환 */

			// 테스트 : Local JSONfile 사용

			// //FileReader로 내부 파일 testMatch.json을 열어 bf에 저장
			// FileReader bf = new FileReader("mygg/src/main/resources/testMatch.json");

			// RIOT API JSON 수신부

			// API 주소값
			String match_url = String.format("%s%s%s?api_key=%s", RiotApiConstants.RIOT_API_URL, RiotApiConstants.RIOT_API_MATCH, matchId,
					RiotApiConstants.API_KEY);

			//url을 json으로 변환
			String matchJSON = UrlToJson.urlToJson(match_url);

			// ======================================================================================================================

			/* JSON Parsing 부 */

			// JSON 데이터를 분석해주는 JSONParser 객체 생성
			JSONParser parser = new JSONParser();

			// player의 식별코드(playerId)를 저장할 List
			player = new ArrayList<String>();

			// String 형태의 JSON 데이터를 JSONObject(HashMap)형 jsonObject로 변환
			JSONObject jsonObject = (JSONObject) parser.parse(matchJSON);

			// jsonObject의 JSON Key값으로 모든 데이터 조회
			for (Object key : jsonObject.keySet()) {

				// key 값으로 받은 데이터를 value에 저장
				Object value = jsonObject.get(key);

				// Extract Player Identity KEY
				// JSON파일 내부의 metadata의 데이터 불러오기
				if (key.equals("metadata")) {
					JSONObject jsonObj = (JSONObject) value;

					// JSONArray(ArrayList)형 partipants(매치참여유저List)
					JSONArray participants = (JSONArray) jsonObj.get("participants");

					// partipants의 데이터를 player List에 저장
					for (int i = 0; i < participants.size(); i++) {
						player.add((String) participants.get(i));
					}
				}
				// =======================================================================

				// JSON파일 내부의 info의 데이터 불러오기
				if (key.equals("info")) {
					JSONObject jsonObj = (JSONObject) value;

					// Player별 데이터를 조회하기 위해 info 내의 participants List를 따로 빼냄
					JSONArray participants = (JSONArray) jsonObj.get("participants");

					// List 인덱스마다 조회
					for (int i = 0; i < participants.size(); i++) {
						JSONObject partPlayer = (JSONObject) participants.get(i);

						// 챔피언 이름, 라인(포지션), 유저이름#태그, 유저레벨 추출/출력
						String champion = (String) partPlayer.get("championName"); //
						String position = (String) partPlayer.get("lane"); // individualPosition, teamPosition
						String userName = partPlayer.get("riotIdGameName") + "#" + partPlayer.get("riotIdTagline");
						Long userLevel = (Long) partPlayer.get("summonerLevel");
						System.out.printf("%d : %s(%d) : %s(%s)", i, userName, userLevel, champion, position);

						// info 내의 participants 내의 challenges를 따로 빼냄
						JSONObject playerChall = (JSONObject) partPlayer.get("challenges");
						
						// 분당 골드 값 추출
						Double goldPerMin = (Double) playerChall.get("goldPerMinute");

						System.out.println();

						// TODO : 객체의 형태로 return 
					}
				}

			}

			//테스트 : 플레이어 식별자(participants) 확인
			for (int i = 0; i < player.size(); i++) {
				System.out.println("Player" + i + " : " + player.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws Exception {
		PrivateMatch match = new PrivateMatch("KR_7334845449");
	}


}
