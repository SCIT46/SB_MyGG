package com.mygg.sb.match;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class PublicMatch {

	final static String RIOT_API_URL = "https://asia.api.riotgames.com";
	final static String RIOT_API_MATCH = "/lol/match/v5/matches/";
	final static String API_KEY = "";


	public PublicMatch(String matchId) {
		try{
			JSONParser parser = new JSONParser();
			String url = String.format("%s%s%s?api_key=%s",RIOT_API_URL,RIOT_API_MATCH,matchId,API_KEY);
			System.out.println(url);
			URL request_url = new URL(url);
			BufferedReader bf = new BufferedReader(new InputStreamReader(request_url.openStream(),"UTF-8"));
			String result=bf.readLine();

			JSONObject jsonObject = (JSONObject)parser.parse(result);
			for(Object key : jsonObject.keySet()){
				Object value = jsonObject.get(key);
				if(key instanceof JSONArray){
					// for(Object obj : value){
					// }
				}
				System.out.println(key+" : "+value);
			}
			// JSONObject matchInfo = (JSONObject)jsonObject.get("info");
			// String infoGameResult = (String)matchInfo.get("endOfGameResult");
			// Long infoGameCreation = (Long)matchInfo.get("gameCreation");
			// Long infoGameDuration = (Long)matchInfo.get("gameDuration");
			// Long infoGameEndtime = (Long)matchInfo.get("gameEndTimestamp");
			// Long infoGameId = (Long)matchInfo.get("gameId");
			// String infoGameMode = (String)matchInfo.get("gameMode");
			// String infoGameName = (String)matchInfo.get("gameName");
			// Long infoGameStarttime = (Long)matchInfo.get("gameStartTimestamp");
			// String infoGameType = (String)matchInfo.get("gameType");
			// String infoGameVersion = (String)matchInfo.get("gameVersion");
			// Long infoGameMap = (Long)matchInfo.get("mapId");
			// JSONArray infoParticipants = (JSONArray)matchInfo.get("participants");

			// for(int i=0; i<infoParticipants.size(); i++){
			// 	JSONObject partOne = (JSONObject)infoParticipants.get(i);
			// 	Long partAllInPings = (Long)partOne.get("allInPings");
			// 	Long partAssistMePings = (Long)partOne.get("assistMePings");
			// 	System.out.println(partAllInPings);
			// }
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args){
		System.out.println("start");
		PublicMatch match = new PublicMatch("KR_7334845449");
	}

}
