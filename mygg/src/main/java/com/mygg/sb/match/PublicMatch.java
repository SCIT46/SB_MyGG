package com.mygg.sb.match;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mygg.sb.statics;
import com.mygg.sb.user.UserApi;


   
 
// riot api로 부터 받아온 match JSON 파일을 DB에 저장(matchId / match.JSON)
// /api/user/{userId} -> DB에 userId.JSON이 있는가? DB에서 JSON 불러오기 : riot API에서 JSON 불러오기 / DB에 기록 -> JSON parsing / return 
// /api/match/public/{matchId} -> DB에 matchId.JSON이 있는가? DB에서 JSON 불러오기 : riot API에서 JSON 불러오기 / DB에 기록 -> JSON parsing / return
public class PublicMatch
	{
		// 매치 내 플레이어 식별자(participants) 를 저장해줄 List
		ArrayList<String> player;

		public PublicMatch(String matchId) throws Exception
			{

				/* JSON 파일 불러오기/변환 */

				// 테스트 : Local JSONfile 사용

				// //FileReader로 내부 파일 testMatch.json을 열어 bf에 저장
				// FileReader bf = new FileReader("mygg/src/main/resources/testMatch.json");

				// RIOT API JSON 수신부

				// API Matches, User 정보
				// Json에서 받아와서

				// API 주소값
				String match_url = String.format("%s%s%s?api_key=%s", statics.RIOT_API_URL, statics.RIOT_API_MATCH,
						matchId, statics.API_KEY);

				// url을 json으로 변환
				String matchJSON = statics.urlToJson(match_url);
				// ======================================================================================================================

				/* JSON Parsing 부 */

				// player의 식별코드(playerId)를 저장할 List
				player = new ArrayList<String>();

				// JSON 데이터를 분석해주는 JSONParser 객체 생성
				JSONParser parser = new JSONParser();

				// String 형태의 JSON 데이터를 JSONObject(HashMap)형 jsonObject로 변환
				JSONObject jsonObject = (JSONObject) parser.parse(matchJSON);

				// jsonObject의 JSON Key값으로 모든 데이터 조회
				for (Object key : jsonObject.keySet())
					{
						// key 값으로 받은 데이터를 value에 저장
						Object value = jsonObject.get(key);
						JSONObject jsonObj = (JSONObject) value;

						// Extract Player Identity KEY
						// JSON파일 내부의 metadata의 데이터 불러오기
						
						if (key.equals("metadata"))
							{
								// JSONArray(ArrayList)형 partipants(매치참여유저List)
								JSONArray participants = (JSONArray) jsonObj.get("participants");

								// partipants의 데이터를 player List에 저장
								for (int i = 0; i < participants.size(); i++)
									{
										player.add((String) participants.get(i));
									}
							}
						// =======================================================================

						// JSON파일 내부의 info의 데이터 불러오기
						if (key.equals("info"))
							{
								searchParticipants(jsonObj);
							}

					}

				// 테스트 : 플레이어 식별자(participants) 확인
				for (int i = 0; i < player.size(); i++)
					{
						System.out.println("Player" + i + " : " + player.get(i));
					}

			}
		
		private void searchParticipants(JSONObject jsonObj)
		{
			// 게임에 참여한 플레이어들 정보를 받아서 정리하는 함수
			
			JSONArray participants = (JSONArray) jsonObj.get("participants");

			// List 인덱스마다 조회
			for (int i = 0; i < participants.size(); i++)
				{
					// participants: 게임 참자가들
					// Player별 데이터를 조회하기 위해 info 내의 participants(플레이어들) List를 따로 빼냄
					JSONObject partPlayer = (JSONObject) participants.get(i);

					// 챔피언 이름, 라인(포지션), 유저이름#태그, 유저레벨 추출/출력
					String champion = (String) partPlayer.get("championName"); //
					String lane = (String) partPlayer.get("lane"); // individualPosition,
																		// teamPosition
					String userName = partPlayer.get("riotIdGameName") + "#"
									+ partPlayer.get("riotIdTagline");
					Long userLevel = (Long) partPlayer.get("summonerLevel");					

					// info 내의 participants 내의 challenges를 따로 빼냄
					JSONObject playerChall = (JSONObject) partPlayer.get("challenges");

					// 분당 골드 값 추출
					Double goldPerMin = (Double) playerChall.get("goldPerMinute");

					Participants participants1 = new Participants(champion, lane, userName, userLevel, goldPerMin);
					System.out.println(participants1);
					//System.out.printf("%d : %s(%d) : %s(%s) \n", i, userName, userLevel, champion, lane);
				}
		}

		public static void main(String[] args) throws Exception
			{
				PublicMatch match = new PublicMatch("KR_7334845449");

			}

	}
