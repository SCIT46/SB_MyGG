package com.mygg.sb.match;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class PublicMatch {

	// final static String RIOT_API_URL = "https://asia.api.riotgames.com";
	// final static String RIOT_API_MATCH = "/lol/match/v5/matches/";
	// final static String API_KEY = "RGAPI-bb0375ec-3fc1-4ec0-933a-8800ef3a673e";


	public PublicMatch(String matchId) {
		try{
			JSONParser parser = new JSONParser();

			//RIOT API JSON
			// String url = String.format("%s%s%s?api_key=%s",RIOT_API_URL,RIOT_API_MATCH,matchId,API_KEY);
			// URL request_url = new URL(url);
			// BufferedReader bf = new BufferedReader(new InputStreamReader(request_url.openStream(),"UTF-8"));
			// String result=bf.readLine();

			//Local JSON TEST
			FileReader result = new FileReader("C:\\Spring_git\\sb_MyGG\\mygg\\src\\main\\resources\\testMatch.json");

			ArrayList player = new ArrayList<String>();
			//JSON Parsing

			JSONObject jsonObject = (JSONObject)parser.parse(result);
			for(Object key : jsonObject.keySet()){
				Object value = jsonObject.get(key);

				//Extract Player Identity KEY
				if(key.equals("metadata")){
					JSONObject jsonObj = (JSONObject)value;
					JSONArray participants = (JSONArray)jsonObj.get("participants");
					for(int i=0; i<participants.size(); i++){
						player.add(participants.get(i));
					}
				}
				
				//
				System.out.println(value.getClass());

				if(key.equals("info")){
					JSONObject jsonObj = (JSONObject)value;
					JSONArray participants = (JSONArray)jsonObj.get("participants");
					for(int i=0; i<participants.size(); i++){
						JSONObject partPlayer = (JSONObject) participants.get(i);

						
						String champion = (String) partPlayer.get("championName");
						String position = (String) partPlayer.get("lane");	//individualPosition, teamPosition
						String userName = partPlayer.get("riotIdGameName")+"#"+partPlayer.get("riotIdTagline");
						Long userLevel = (Long) partPlayer.get("summonerLevel");
						System.out.printf("%d : %s(%d) : %s(%s)",i,userName,userLevel,champion,position);

						JSONObject playerChall = (JSONObject) partPlayer.get("challenges");
						Double goldPerMin = (Double) playerChall.get("goldPerMinute");
						System.out.println();
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
	}

}
