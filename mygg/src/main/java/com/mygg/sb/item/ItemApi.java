package com.mygg.sb.item;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mygg.sb.statics.api.RiotApiClient;
import com.mygg.sb.statics.util.UrlToJson;

import lombok.Getter;

@Getter
public class ItemApi {
    itemDto item;

    public ItemApi(String id) throws Exception{
        // 아이템 정보 초기화
        item = new itemDto();

        // 아이템 정보 조회
        JSONObject jsonObject = RiotApiClient.getItem(id);

        // JSON으로 부터 받아온 정보를 itemDto 객체에 설정
        item.setId(id);
        item.setName((String) jsonObject.get("name"));
        item.setDescription((String) jsonObject.get("description"));
        item.setFrom(UrlToJson.jsonArrayToStringArray((JSONArray) jsonObject.get("from")));
        item.setInto(UrlToJson.jsonArrayToStringArray((JSONArray) jsonObject.get("into")));
        item.getGold().setBase(((Long) ((JSONObject) jsonObject.get("gold")).get("base")).intValue());
        item.getGold().setSell(((Long) ((JSONObject) jsonObject.get("gold")).get("sell")).intValue());
        item.getGold().setTotal(((Long) ((JSONObject) jsonObject.get("gold")).get("total")).intValue());
        item.getGold().setPurchasable((boolean) ((JSONObject) jsonObject.get("gold")).get("purchasable"));
        //item.setTags((ArrayList<String>) jsonObject.get("tags"));
        item.setStats(UrlToJson.jsonObjectToHashMap((JSONObject) jsonObject.get("stats")));
    }

}
