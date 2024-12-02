package com.mygg.sb.item;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mygg.sb.statics.api.RiotApiClient;
import com.mygg.sb.statics.util.JsonToDtoMapper;
import com.mygg.sb.statics.util.UrlToJson;

import lombok.Getter;

@Getter
public class ItemApi {
    
    // 아이템 리스트
    ArrayList<itemDto> item;

    // 아이템 아이디로 아이템 정보 받아오기
    public ItemApi(String id) throws Exception{
        // 아이템 리스트 초기화
        item = new ArrayList<>();
        // 아이디로 아이템 정보 추출/저장하여 객체화 시켜주는 메서드 호출
        item.add(createItemDto(id));
    }

    // 전체 아이템 정보 받아오기
    public ItemApi() throws Exception{
        // 아이템 리스트 초기화
        item = new ArrayList<>();
        // 전체 아이템 아이디 리스트 초기화
        ArrayList<String> itemIdList;

        // 아이템의 전체 정보 받아오기
        JSONObject jsonObject = RiotApiClient.getItem("all");

        // 아이템의 전체 아이디 받아오기/저장
        itemIdList = new ArrayList<>(jsonObject.keySet());

        // ID 리스트로 전체 정보 받아와 아이템 리스트에 저장
        for(String id : itemIdList){
            item.add(createItemDto(id));
        }
    }

    // 아이디로 아이템 정보 추출/저장하여 객체화 시켜주는 메서드
    public itemDto createItemDto(String id) throws Exception{
        // 아이템 정보 초기화
        itemDto item = new itemDto();

        // 아이템 정보 조회
        JSONObject jsonObject = RiotApiClient.getItem(id);

        // JSON으로 부터 받아온 정보를 itemDto 객체에 설정
        //item.setId(id);
        //item.setName((String) jsonObject.get("name"));
        //item.setDescription((String) jsonObject.get("description"));
        //item.setFrom(UrlToJson.jsonArrayToStringArray((JSONArray) jsonObject.get("from")));
        //item.setInto(UrlToJson.jsonArrayToStringArray((JSONArray) jsonObject.get("into")));
        //item.getGold().setBase(((Long) ((JSONObject) jsonObject.get("gold")).get("base")).intValue());
        //item.getGold().setSell(((Long) ((JSONObject) jsonObject.get("gold")).get("sell")).intValue());
        //item.getGold().setTotal(((Long) ((JSONObject) jsonObject.get("gold")).get("total")).intValue());
        //item.getGold().setPurchasable((boolean) ((JSONObject) jsonObject.get("gold")).get("purchasable"));
        //item.setTags((ArrayList<String>) jsonObject.get("tags"));
        //item.setStats(UrlToJson.jsonObjectToHashMap((JSONObject) jsonObject.get("stats")));

        JsonToDtoMapper mapper = new JsonToDtoMapper();
        item = mapper.mapToDto(jsonObject, itemDto.class);
		
        return item;
    }
}
