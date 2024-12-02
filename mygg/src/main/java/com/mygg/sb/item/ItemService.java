package com.mygg.sb.item;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.mygg.sb.statics.api.RiotApiClient;
import com.mygg.sb.statics.util.JsonToDtoMapper;

import lombok.Getter;

@Getter
@Service
public class ItemService {
    
    // 아이템 리스트
    ArrayList<ItemDTO> item;

    public ItemService(){
      // 아이템 리스트 초기화
      item = new ArrayList<>();
    }

    // 아이템 아이디로 아이템 정보 받아오기
    public ArrayList<ItemDTO> getItem(String id) throws Exception{
        // 아이디로 아이템 정보 추출/저장하여 객체화 시켜주는 메서드 호출
        item.add(createItemDto(id));

        return this.item;
    }

    // 전체 아이템 정보 받아오기
    public ArrayList<ItemDTO> getItems() throws Exception{
        // 아이템의 전체 정보 받아오기
        JSONObject jsonObject = RiotApiClient.getItem("all");

        // 아이템의 전체 아이디 받아오기/저장
        JSONObject data = (JSONObject) jsonObject.get("data");

        // 전체 아이템 아이디 리스트 초기화
        List<String> itemIdList = new ArrayList<>(data.keySet());

        // ID 리스트로 전체 정보 받아와 아이템 리스트에 저장
        for(String id : itemIdList){
            item.add(createItemDto(id));
        }

        return this.item;
    }

    // 아이디로 아이템 정보 추출/저장하여 객체화 시켜주는 메서드
    public ItemDTO createItemDto(String id) throws Exception{
        // 아이템 정보 초기화
        ItemDTO item = new ItemDTO();

        // 아이템 정보 조회
        JSONObject jsonObject = RiotApiClient.getItem(id);

        // JSON으로 부터 받아온 정보를 itemDto 객체에 설정
        JsonToDtoMapper mapper = new JsonToDtoMapper();
        item = mapper.mapToDto(jsonObject, ItemDTO.class);
		
        return item;
    }
}
