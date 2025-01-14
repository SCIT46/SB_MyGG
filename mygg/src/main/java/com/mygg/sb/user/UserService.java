package com.mygg.sb.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mygg.sb.exception.custom.DataNotFoundException;
import com.mygg.sb.statics.api.RiotApiClient;
import com.mygg.sb.statics.util.DateTimeUtils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Getter
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
  // ############################ Field & Constructor ############################
  private final UserRepository userRepository;

  // ===========================================================================

  // ################################# JPA CRUD ################################
  public void create(UserDTO dto) {
    userRepository.save(UserEntity.toEntity(dto));
  }

  public UserDTO readOne(String name, String tag) {
    Optional<UserEntity> tmp = userRepository.findByGameNameAndTagLine(name, tag);
    if (tmp.isPresent()) {
      UserEntity entity = tmp.get();
      return UserDTO.toDTO(entity);
    }
    return null;
  }

  public List<UserDTO> readAll() {
    List<UserEntity> tmp = userRepository.findAll();
    List<UserDTO> list = new ArrayList<>();
    if (tmp.isEmpty())
      return null;
    for (UserEntity item : tmp) {
      list.add(UserDTO.toDTO(item));
    }
    return list;
  }

  @Transactional
  public void update(UserDTO dto) {
    Optional<UserEntity> tmp = userRepository.findById(dto.getId());
    if (tmp.isPresent()) {
      userRepository.save(UserEntity.toEntity(dto));
    }
  }

  public void delete(Long id) {
    userRepository.deleteById(id);
  }
  // ===========================================================================


  // ################################### API ###################################
  @Transactional(noRollbackFor = {Exception.class})
  public UserDTO searchUser(String gameName, String tagLine) throws Exception {
    // DB에 저장된 유저 정보를 받아오기
    Optional<UserEntity> tmp = userRepository.findByGameNameAndTagLine(gameName, tagLine);
    if (tmp.isEmpty()) {
      // DB에 유저 정보가 없으면 API로부터 유저 정보를 받아와 DB에 저장
      UserDTO user = getUserInfo(gameName, tagLine);
      // throw new DataNotFoundException("유저를 찾을 수 없습니다");
      create(user);
      return user;
      
    }
    UserEntity user = tmp.get();

    // 유저 검색 횟수 증가/유저 정보를 반환
    user.setSearchCount(user.getSearchCount() + 1);
    return UserDTO.toDTO(user);
  }


  // 이름과 태그를 통해 소환사 정보를 불러올 때 사용하는 생성자
  public UserDTO getUserInfo(String gameName, String tagLine) throws Exception {
    UserDTO user = new UserDTO();
    // 이름과 태그를 puuid로 변환
    user.setGameName(gameName);
    user.setTagLine(tagLine);

    user.setPuuid(RiotApiClient.getPuuidNameAndTag(gameName, tagLine));

    init(user);
    return user;
  }

  // puuid를 통해 소환사 정보를 불러올 때 사용하는 생성자 (미사용)
  public UserDTO getUserInfo(String puuid) throws Exception {
    UserDTO user = new UserDTO();

    user.setPuuid(puuid);
    String[] nametag = RiotApiClient.getNametag(puuid);
    user.setGameName(nametag[0]);
    user.setTagLine(nametag[1]);
    init(user);
    return user;
  }

  // 인스턴스 초기화 구문
  // 3개의 api 정보를 이용해서 1개의 유저정보로 초기화
  private void init(UserDTO user) throws Exception {
    // 소환사 정보 JSON
    JSONObject jsonObject = RiotApiClient.getSummonerInfo(user.getPuuid()); // (JSONObject) parser.parse(summoJSON);
    user.setSummonerId((String) jsonObject.get("id"));
    user.setProfileIconId(((Long) jsonObject.get("profileIconId")).intValue());
    user.setRevisionDate(DateTimeUtils.epochToLocalDateTime((long) jsonObject.get("revisionDate")));
    user.setSummonerLevel(((Long) jsonObject.get("summonerLevel")).intValue());

    // summonerId를 통해 리그정보 JSON 받아옴
    JSONObject jsonObject2 = RiotApiClient.getLeagueBySummonerId(user.getSummonerId());
    user.setLeagueId((String) jsonObject2.get("leagueId"));
    user.setTier((String) jsonObject2.get("tier"));
    user.setRank((String) jsonObject2.get("rank"));
    user.setLeaguePoints(((Long) jsonObject2.get("leaguePoints")).intValue());
    user.setWins(((Long) jsonObject2.get("wins")).intValue());
    user.setLosses(((Long) jsonObject2.get("losses")).intValue());
    // 최근 매치 목록
    user.setMatchList(RiotApiClient.getMatchList(user.getPuuid()));
  }
}
