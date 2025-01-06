package com.mygg.sb.item;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.mygg.sb.exception.DataNotFoundException;
import com.mygg.sb.statics.api.RiotApiClient;
import com.mygg.sb.statics.util.JsonToDTOMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {
  // ############################ Field & Constructor ############################
  // JPA Repository
  private final ItemRepository itemRepository;

  // ################################# JPA CRUD ################################
  public void create(ItemDTO dto) {
    itemRepository.save(ItemEntity.toEntity(dto));
  }

  public ItemDTO readOne(String id) {
    Optional<ItemEntity> tmp = itemRepository.findById(id);
    if (tmp.isPresent()) {
      ItemEntity entity = tmp.get();
      return ItemDTO.toDTO(entity);
    }
    return null;
  }

  public List<ItemDTO> readAll() {
    List<ItemEntity> tmp = itemRepository.findAll();
    List<ItemDTO> list = new ArrayList<>();
    if (tmp.isEmpty())
      return null;
    for (ItemEntity item : tmp) {
      list.add(ItemDTO.toDTO(item));
    }
    return list;
  }

  public void update(ItemDTO dto) {
    Optional<ItemEntity> tmp = itemRepository.findById(dto.getId());
    if (tmp.isPresent()) {
      itemRepository.save(ItemEntity.toEntity(dto));
    }
  }

  public void delete(String id) {
    itemRepository.deleteById(id);
  }
  // ===========================================================================

  // ################################### API ###################################
  // 아이템 아이디로 아이템 정보 받아오기
  // 아이디로 아이템 정보 추출/저장하여 객체화 시켜주는 메서드
  public Map<String, ItemDTO> getItem(String id) throws Exception {
    // 아이템 맵
    Map<String, ItemDTO> item = new TreeMap<>();

    // 아이템의 전체 정보 받아오기
    JSONObject jsonObject = RiotApiClient.getItem("all");
    // 아이템 아이디 리스트
    List<String> itemIdList;
    // 전체 아이템 정보 받아오기
    if(id.equals("all")) {
      // 아이템의 전체 아이디 받아오기/저장
      itemIdList = new ArrayList<>(jsonObject.keySet());
    }
    // 지정 아이템 정보 받아오기
    else{
      // 지정해준 아이템 아이디만 리스트에 저장
      itemIdList = new ArrayList<>();
      itemIdList.add(id);
    }

    // ID 리스트로 전체 정보 받아와 아이템 맵에 저장
    for (String id_tmp : itemIdList) {
      // DB에서 아이템 정보 조회
      ItemDTO tmp = readOne(id_tmp);
      // DB에 있으면 바로 아이템 맵에 저장
      if(tmp != null) {
        item.put(id_tmp, tmp);
      }
      // DB에 없으면 API로부터 아이템 정보 받아와 DB 및 아이템 맵에 저장
      else {
        // JSON으로 부터 받아온 정보를 itemDto 객체에 설정
        JsonToDTOMapper mapper = new JsonToDTOMapper();
        try{
          tmp = mapper.mapToDto(RiotApiClient.getItem(id_tmp), ItemDTO.class);
        }
        catch(NullPointerException e){
          throw new DataNotFoundException("존재하지 않는 아이템입니다.");
        }
        tmp.setId(id_tmp); 
        // 아이템 맵에 저장
        item.put(id_tmp, tmp);
        // DB에 아이템 정보 저장
        create(tmp);
      }
    }

    // 아이템 맵 반환
    return item;
  }
}
