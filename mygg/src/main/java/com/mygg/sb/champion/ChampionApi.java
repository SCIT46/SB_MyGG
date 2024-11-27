package com.mygg.sb.champion;

import org.json.simple.JSONObject;

import com.mygg.sb.statics.api.RiotApiClient;
import com.mygg.sb.statics.util.UrlToJson;

import lombok.Getter;

@Getter
public class ChampionApi {

    champDto champ;
    public ChampionApi(String id) throws Exception{
        // 챔피언 정보 초기화
        champ = new champDto();

        // 챔피언 정보 조회
        JSONObject jsonObject = RiotApiClient.getChampion(id);

        // JSON으로 부터 받아온 정보를 champDto 객체에 설정
        champ.setId(id);
        champ.setName((String) jsonObject.get("name"));
        champ.setTitle((String) jsonObject.get("title"));
        champ.setKey((String) jsonObject.get("key"));
        champ.setBlurb((String) jsonObject.get("blurb"));
        champ.getInfo().setAttack(((Long) ((JSONObject) jsonObject.get("info")).get("attack")).intValue());
        champ.getInfo().setDefense(((Long) ((JSONObject) jsonObject.get("info")).get("defense")).intValue());
        champ.getInfo().setMagic(((Long) ((JSONObject) jsonObject.get("info")).get("magic")).intValue());
        champ.getInfo().setDifficulty(((Long) ((JSONObject) jsonObject.get("info")).get("difficulty")).intValue());
        champ.setStats(UrlToJson.jsonObjectToHashMap((JSONObject) jsonObject.get("stats")));
    }

}
