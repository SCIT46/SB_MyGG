package com.mygg.sb.champion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.json.simple.JSONObject;

import org.springframework.stereotype.Service;

import com.mygg.sb.exception.custom.DataNotFoundException;
import com.mygg.sb.statics.api.RiotApiClient;
import com.mygg.sb.statics.util.JsonToDTOMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChampionService {
  // ############################ Field & Constructor ############################
  // JPA Repository
  private final ChampionRepository championRepository;

  // ################################# JPA CRUD ################################
  public void create(ChampionDTO dto) {
    championRepository.save(ChampionEntity.toEntity(dto));
  }

  public ChampionDTO readOne(String id) {
    Optional<ChampionEntity> tmp = championRepository.findById(id);
    if (tmp.isPresent()) {
      ChampionEntity entity = tmp.get();
      return ChampionDTO.toDTO(entity);
    }
    return null;
  }

  public List<ChampionDTO> readAll() {
    List<ChampionEntity> tmp = championRepository.findAll();
    List<ChampionDTO> list = new ArrayList<>();
    if (tmp.isEmpty())
      return null;
    for (ChampionEntity item : tmp) {
      list.add(ChampionDTO.toDTO(item));
    }
    return list;
  }

  public void update(ChampionDTO dto) {
    Optional<ChampionEntity> tmp = championRepository.findById(dto.getId());
    if (tmp.isPresent()) {
      championRepository.save(ChampionEntity.toEntity(dto));
    }
  }

  public void delete(String id) {
    championRepository.deleteById(id);
  }
  // ===========================================================================

  // ################################### API ###################################
  
  // 챔피언 객체 생성/반환
  public Map<String, ChampionDTO> getChampion(String id) throws Exception {
    // 챔피언 맵 생성
    Map<String, ChampionDTO> champion = new TreeMap<>();

    // 챔피언 아이디 리스트 조회
    List<String> championIdList;

    // 챔피언 전체 정보 조회
    JSONObject jsonObject = RiotApiClient.getChampion("all");

    // 전체 챔피언 아이디 조회
    if(id.equals("all")) {
      // 챔피언 아이디 리스트 조회
      championIdList = new ArrayList<>(jsonObject.keySet());
    }
    // 지정 챔피언 아이디 조회
    else {
      // 챔피언 아이디 리스트 조회
      championIdList = new ArrayList<>();
      championIdList.add(id);
    }

    // 챔피언 아이디 리스트 반복
    for (String id_tmp : championIdList) {
      // 챔피언 정보 조회
      ChampionDTO tmp = readOne(id_tmp);
      // DB에 있으면 바로 챔피언 맵에 저장
      if(tmp != null) {
        champion.put(id_tmp, tmp);
      }
      // DB에 없으면 API로부터 챔피언 정보 받아와 DB 및 챔피언 맵에 저장
      else {
        // JSON으로 부터 받아온 정보를 champDto 객체에 설정
        JsonToDTOMapper mapper = new JsonToDTOMapper();
        try{
          tmp = mapper.mapToDto(RiotApiClient.getChampion(id_tmp), ChampionDTO.class);
        }
        catch(NullPointerException e){
          throw new DataNotFoundException("존재하지 않는 챔피언입니다.");
        }
        tmp.setId(id_tmp); 
        // 챔피언 맵에 저장
        champion.put(id_tmp, tmp);
        // DB에 챔피언 정보 저장
        create(tmp);
      }
    }

    // 챔피언 맵 반환
    return champion;
  }

}
