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

        JSONObject jsonObject = RiotApiClient.getItem(id);
        System.out.println(jsonObject);

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
        item.setStats(new statsDto((JSONObject) jsonObject.get("stats")));
    }

}
