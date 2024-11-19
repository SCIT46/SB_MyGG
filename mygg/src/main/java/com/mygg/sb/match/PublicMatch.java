package com.mygg.sb.match;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

 /*
    Match Field
		유저 초상화
		유저 이름/태그)
		유저 레벨

		등급
		lp 점수
		승패, 승률

		챔피언별 통계(KDA, 승률, 게임수)
		같이 플레이한 유저(이름/태그, 레벨, 승률, 게임수(승/패))

		매치 당 데이터
			게임 타입(일반게임(솔로))
			조회 날짜로부터 경과한 시간
			경기시간
			챔피언(이미지) + 경기 중 달성 레벨
			사용한 스펠, 룬(이미지), 아이템
			KDA
			시야 기여도
			골드 총 금액

			매치에 같이 참여한 유저들(participants)
  */
  // riot api로 부터 받아온 match JSON 파일을 DB에 저장(matchId / match.JSON)
  // /api/user/{userId} -> DB에 userId.JSON이 있는가? DB에서 JSON 불러오기 : riot API에서 JSON 불러오기 / DB에 기록 -> JSON parsing / return 
  // /api/match/public/{matchId} -> DB에 matchId.JSON이 있는가? DB에서 JSON 불러오기 : riot API에서 JSON 불러오기 / DB에 기록 -> JSON parsing / return 

public class PublicMatch {

 	ArrayList<String> arr = new ArrayList<>();

	public PublicMatch(String matchId) {
		try{
			JSONParser parser = new JSONParser();
			BufferedReader bf;
			URL request_url;
			//RIOT API JSON
      		String match_url = String.format("%s%s%s?api_key=%s",statics.RIOT_API_URL,statics.RIOT_API_MATCH,matchId,statics.API_KEY);
			request_url = new URL(match_url);
			bf = new BufferedReader(new InputStreamReader(request_url.openStream(),"UTF-8"));
			String matchJSON=bf.readLine();

			//Local JSON TEST
			//FileReader result = new FileReader("mygg/src/main/resources/testMatch.json");

			ArrayList<String> player = new ArrayList<String>();
			//JSON Parsing

			JSONObject jsonObject = (JSONObject)parser.parse(matchJSON);
			for(Object key : jsonObject.keySet()){
				Object value = jsonObject.get(key);

				//Extract Player Identity KEY
				if(key.equals("metadata")){
					JSONObject jsonObj = (JSONObject)value;
					JSONArray participants = (JSONArray)jsonObj.get("participants");
					for(int i=0; i<participants.size(); i++){
						player.add((String)participants.get(i));
					}
				}
				
				//
				System.out.println(value.getClass());

				if(key.equals("info")){
					JSONObject jsonObj = (JSONObject)value;
					JSONArray participants = (JSONArray)jsonObj.get("participants");
					for(int i=0; i<participants.size(); i++){
						JSONObject partPlayer = (JSONObject) participants.get(i);

						
						String champion = (String) partPlayer.get("championName"); //
						String position = (String) partPlayer.get("lane");	//individualPosition, teamPosition
						String userName = partPlayer.get("riotIdGameName")+"#"+partPlayer.get("riotIdTagline");
						Long userLevel = (Long) partPlayer.get("summonerLevel");
						System.out.printf("%d : %s(%d) : %s(%s)",i,userName,userLevel,champion,position);

						JSONObject playerChall = (JSONObject) partPlayer.get("challenges");
						Double goldPerMin = (Double) playerChall.get("goldPerMinute");
						System.out.println();

           			 	//TODO
					}
				}

			}
			for(int i=0; i<player.size(); i++){
				System.out.println("Player"+i+" : "+player.get(i));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args){
		PublicMatch match = new PublicMatch("KR_7334845449");

    // String versionJSON = new BufferedReader(new InputStreamReader(new URL("https://ddragon.leagueoflegends.com/api/versions.json").openStream(),"UTF-8")).readLine();
    // String languageJSON = new BufferedReader(new InputStreamReader(new URL("https://ddragon.leagueoflegends.com/cdn/languages.json").openStream(),"UTF-8")).readLine();
	}

}
