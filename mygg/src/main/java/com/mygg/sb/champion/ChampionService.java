package com.mygg.sb.champion;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.mygg.sb.statics.api.RiotApiClient;
import com.mygg.sb.statics.util.JsonToDtoMapper;

import lombok.Getter;

@Getter
@Service
public class ChampionService {

    //ChampDTO champion;
    ArrayList<ChampDTO> champion;
    
    public ChampionService() throws Exception{
        // 챔피언 정보 초기화
        champion = new ArrayList<>();
    }

    public ArrayList<ChampDTO> getChampion(String id) throws Exception {
      // 챔피언 정보 조회
      JSONObject jsonObject = RiotApiClient.getChampion(id);

      ChampDTO championObj = new ChampDTO();

      // JSON으로 부터 받아온 정보를 champDto 객체에 설정
      // champion.setId(id);
      // champion.setName((String) jsonObject.get("name"));
      // champion.setTitle((String) jsonObject.get("title"));
      // champion.setKey((String) jsonObject.get("key"));
      // champion.setBlurb((String) jsonObject.get("blurb"));
      // champion.getInfo().setAttack(((Long) ((JSONObject) jsonObject.get("info")).get("attack")).intValue());
      // champion.getInfo().setDefense(((Long) ((JSONObject) jsonObject.get("info")).get("defense")).intValue());
      // champion.getInfo().setMagic(((Long) ((JSONObject) jsonObject.get("info")).get("magic")).intValue());
      // champion.getInfo().setDifficulty(((Long) ((JSONObject) jsonObject.get("info")).get("difficulty")).intValue());
      // champion.setStats(UrlToJson.jsonObjectToHashMap((JSONObject) jsonObject.get("stats")));
      JsonToDtoMapper mapper = new JsonToDtoMapper();
      championObj = mapper.mapToDto(jsonObject, ChampDTO.class);

      champion.add(championObj);

      return this.champion;
    }

    public ArrayList<ChampDTO> getChampions() throws Exception {
      // 챔피언 정보 조회
      JSONObject jsonObject = RiotApiClient.getChampion("all");

      JSONObject data = (JSONObject) jsonObject.get("data");
      ArrayList<String> championIds = new ArrayList<>(data.keySet());

      for(String id : championIds){
        JSONObject championData = (JSONObject) data.get(id);
        ChampDTO championObj = new ChampDTO();
        JsonToDtoMapper mapper = new JsonToDtoMapper();
        championObj = mapper.mapToDto(championData, ChampDTO.class);  

        champion.add(championObj);
      }

      return this.champion;
    }
}
