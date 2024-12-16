package com.mygg.sb.item;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mygg.sb.champion.ChampionDTO;
import com.mygg.sb.champion.ChampionEntity;
import com.mygg.sb.statics.api.RiotApiClient;
import com.mygg.sb.statics.util.JsonToDtoMapper;

import lombok.Getter;

@Getter
@Service
public class ItemService {
    // JPA Repository
    private final ItemRepository itemRepository;
    // 아이템 맵
    Map<String, ItemDTO> item;

    //아이템 맵 초기화/생성(lombok 자동 생성 불가)
    @Autowired
    public ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
        this.item = new TreeMap<>();
    }

    public ItemService() throws Exception{
        //item = new ArrayList<>();
        this.itemRepository = null;
        this.item = new TreeMap<>();
    }

    // ===========================================================================
    public void create(ItemDTO dto){
      itemRepository.save(ItemEntity.toEntity(dto));
    }

    public ItemDTO readOne(String id){
      Optional<ItemEntity> tmp = itemRepository.findById(id);
      if(tmp.isPresent()){
        ItemEntity entity = tmp.get();
        return ItemDTO.toDTO(entity);
      }
      return null;
    }
    
    public List<ItemDTO> readAll(){
      List<ItemEntity> tmp = itemRepository.findAll();
      List<ItemDTO> list = new ArrayList<>();
      if(tmp.isEmpty()) return null;
      for(ItemEntity item : tmp){
        list.add(ItemDTO.toDTO(item));
      }
      return list;
    }

    public void update(ItemDTO dto){
      Optional<ItemEntity> tmp = itemRepository.findById(dto.getId());
      if(tmp.isPresent()){
        itemRepository.save(ItemEntity.toEntity(dto));
      }
    }

    public void delete(String id){
      itemRepository.deleteById(id);
    }
    // ===========================================================================

    // 아이템 아이디로 아이템 정보 받아오기
    public Map<String, ItemDTO> getItem(String id) throws Exception{
        // 아이템 맵 초기화
        item.clear();

        // 아이디로 아이템 정보 추출/저장하여 객체화 시켜주는 메서드 호출
        item.put(id, createItemDto(id));

        return this.item;
    }

    // 전체 아이템 정보 받아오기
    public Map<String, ItemDTO> getItems() throws Exception{
        // 아이템 맵 초기화
        item.clear();

        // 아이템 아이디 리스트 조회
        List<String> itemIdList = getItemIds();
        
        // ID 리스트로 전체 정보 받아와 아이템 맵에 저장
        for(String id : itemIdList){
            item.put(id, createItemDto(id));
        }

        JSONObject resultItem = new JSONObject();

        resultItem.put("version", RiotApiClient.getLatestVersion());
        resultItem.put("data", item);
        
        return resultItem;
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
        item.setId(id);
		
        return item;
    }


    public List<String> getItemIds() throws Exception{
       // 아이템의 전체 정보 받아오기
       JSONObject jsonObject = RiotApiClient.getItem("all");
 
       // 아이템의 전체 아이디 받아오기/저장
       List<String> itemIdList = new ArrayList<>(jsonObject.keySet());

       return itemIdList;
    }
}
