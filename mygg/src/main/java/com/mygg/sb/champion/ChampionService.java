package com.mygg.sb.champion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.mygg.sb.statics.api.RiotApiClient;
import com.mygg.sb.statics.util.JsonToDtoMapper;

import lombok.Getter;

@Getter
@Service
public class ChampionService {

    // 챔피언 맵
    Map<String, ChampDTO> champion;
    
    // 챔피언 맵 초기화/생성(lombok 자동 생성 불가)
    public ChampionService() throws Exception{
      champion = new HashMap<>();
    }

    public Map<String, ChampDTO> getChampion(String id) throws Exception {
      // 챔피언 맵 초기화
      champion.clear();
      
      // 챔피언 정보 조회
      ChampDTO championObj = createChampionDto(id);

      champion.put(id, championObj);

      return this.champion;
    }

    public Map<String, ChampDTO> getChampions() throws Exception {
      // 챔피언 맵 초기화
      champion.clear();
      
      // 챔피언 아이디 리스트 조회
      List<String> championIds = getChampionIds();

      for(String id : championIds){
        // 챔피언 정보 조회
        ChampDTO championObj = createChampionDto(id);
        champion.put(id, championObj);
      }

      return this.champion;
    }

    public ChampDTO createChampionDto(String id) throws Exception{
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

      return championObj;
    }

    public List<String> getChampionIds() throws Exception{
       // 챔피언 정보 조회
       JSONObject jsonObject = RiotApiClient.getChampion("all");
       
       // 챔피언 아이디 리스트 조회
       List<String> championIds = new ArrayList<>(jsonObject.keySet());

       return championIds;
    }
}
