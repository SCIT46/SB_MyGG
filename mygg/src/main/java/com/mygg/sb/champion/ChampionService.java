package com.mygg.sb.champion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mygg.sb.statics.api.RiotApiClient;
import com.mygg.sb.statics.util.JsonToDtoMapper;

import lombok.Getter;

@Getter
@Service
public class ChampionService {
  // ############################ Field & Constructor ############################
  // JPA Repository
  private final ChampionRepository championRepository;
  // 챔피언 맵
  Map<String, ChampionDTO> champion;

  // // 챔피언 맵 초기화/생성(lombok 자동 생성 불가)
  @Autowired
  public ChampionService(ChampionRepository championRepository) {
    this.championRepository = championRepository;
    this.champion = new TreeMap();
  }

  public ChampionService() {
    this.championRepository = null;
    this.champion = new TreeMap();
  }
  // ===========================================================================

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
  // 개별 챔피언 정보 조회
  public Map<String, ChampionDTO> getChampion(String id) throws Exception {
    // 챔피언 맵 초기화
    champion.clear();

    // 챔피언 정보 조회
    ChampionDTO championObj = createChampionDto(id);

    // 챔피언 맵에 챔피언 정보 저장
    champion.put(id, championObj);

    return this.champion;
  }

  // 전체 챔피언 정보 조회(렌더링 시간이 오래걸림)
  public Map<String, ChampionDTO> getChampions() throws Exception {
    // 챔피언 맵 초기화
    champion.clear();

    // 챔피언 아이디 리스트 조회
    List<String> championIds = getChampionIds();

    // 챔피언 아이디 리스트 반복
    for (String id : championIds) {
      // 챔피언 정보 조회
      ChampionDTO championObj = createChampionDto(id);

      // 챔피언 맵에 챔피언 정보 저장
      champion.put(id, championObj);
    }

    JSONObject resultChampion = new JSONObject();

    resultChampion.put("version", RiotApiClient.getLatestVersion());
    resultChampion.put("data", champion);

    return resultChampion;
  }

  // 챔피언 객체 생성/반환
  public ChampionDTO createChampionDto(String id) throws Exception {
    // 챔피언 정보 조회
    JSONObject jsonObject = RiotApiClient.getChampion(id);

    // 챔피언 객체 생성
    ChampionDTO championObj = new ChampionDTO();

    // JSON으로 부터 받아온 정보를 champDto 객체에 설정
    JsonToDtoMapper mapper = new JsonToDtoMapper();
    championObj = mapper.mapToDto(jsonObject, ChampionDTO.class);

    return championObj;
  }

  // 챔피언 아이디 리스트 조회
  public List<String> getChampionIds() throws Exception {
    // 챔피언 정보 조회
    JSONObject jsonObject = RiotApiClient.getChampion("all");

    // 챔피언 아이디 리스트 조회
    List<String> championIds = new ArrayList<>(jsonObject.keySet());

    return championIds;
  }
}
