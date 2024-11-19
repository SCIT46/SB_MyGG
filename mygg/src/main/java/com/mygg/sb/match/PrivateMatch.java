package com.mygg.sb.match;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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

	ArrayList<String> arr = new ArrayList<>();
	
	public PrivateMatch(String matchId){

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
