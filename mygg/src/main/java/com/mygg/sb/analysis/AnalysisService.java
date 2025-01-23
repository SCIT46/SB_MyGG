package com.mygg.sb.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.mygg.sb.exception.custom.DataNotFoundException;
import com.mygg.sb.match.MatchDTO;
import com.mygg.sb.match.service.PublicMatchService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnalysisService {

    private final AnalysisRepository analRepo;
    private final PublicMatchService pmatchService;
    // -------------------------------------- CRUD --------------------------------------

    public void createUserAnalysis(UserAnalysisDTO dto) {
        UserAnalysisEntity entity = UserAnalysisEntity.toEntity(dto);
        analRepo.save(entity);
    }

    public UserAnalysisDTO readUserAnalysis(String puuid) {
        Optional<UserAnalysisEntity> entity = analRepo.findByPuuid(puuid);
        if (entity.isEmpty()) return null;
        
        return UserAnalysisDTO.toDTO(entity.get());
    }

    public void updateUserAnalysis(UserAnalysisDTO dto) throws Exception {
        Optional<UserAnalysisEntity> tmp = analRepo.findByPuuid(dto.getPuuid());
        if (tmp.isEmpty()) throw new DataNotFoundException("통계를 업데이트 할 유저가 없습니다");
        UserAnalysisEntity entity = UserAnalysisEntity.toEntity(dto);
        analRepo.save(entity);
    }

    public void deleteUserAnalysis(String puuid) throws Exception {
        Optional<UserAnalysisEntity> tmp = analRepo.findByPuuid(puuid);
        if (tmp.isEmpty()) throw new DataNotFoundException("통계를 삭제할 유저가 없습니다");
        analRepo.delete(tmp.get());
    }
    // -----------------------------------------------------------------------------------
    // 통계 데이터 획득
    public UserAnalysisDTO getUserAnalysis(String puuid) throws Exception {
        // DB에 통계 데이터가 있는지 확인
        Optional<UserAnalysisEntity> tmp = analRepo.findByPuuid(puuid);
        if (tmp.isPresent()) return UserAnalysisDTO.toDTO(tmp.get());
        // 없으면 생성/저장 후 반환
        return generateAnalysis(puuid);
    }

    // 통계 데이터 전체갱신(부분갱신으로 변경 예정)
    public void updateUserAnalysis(String puuid) throws Exception {
        // 통계 데이터 생성
        UserAnalysisDTO dto = generateAnalysis(puuid);
        // DB에 저장
        analRepo.save(UserAnalysisEntity.toEntity(dto));
    }

    // 통계 데이터 생성
    public UserAnalysisDTO generateAnalysis(String puuid) throws Exception {
        // 매치데이터들을 받아온 list
        List<MatchDTO> list = pmatchService.getMatchDataInDB(puuid);
        // 통계 데이터를 저장할 DTO
        UserAnalysisDTO analList = new UserAnalysisDTO();

        // 통계 데이터 각각 설정
        analList.setPuuid(puuid);
        getChampionAnalysis(list, puuid).forEach(map -> analList.setChampionAnalysis(map));

        // DB에 저장
        //analRepo.save(UserAnalysisEntity.toEntity(analList));

        return analList;
    }

    // 챔피언별 분석 데이터 생성
    public List<Map<String, ChampionAnalysis>> getChampionAnalysis(List<MatchDTO> list, String puuid) {
        Stream<MatchDTO> stream = list.stream();
        // 플레이한 챔피언 목록 추출(중복을 제거하기 위해 Set 사용)
        Set<String> championList = stream.flatMap(  // MatchDTO의 요소를 순차적으로(List) 추출하기(map 구조)
            match -> match.getInfo().getParticipants().stream() // MatchDTO의 info -> List<ParticipantsDTO>를 추출해서 다시 순차적으로 접근하기 위해 stream 사용
                .filter(
                    participants -> participants.getPuuid().equals(puuid)) // List<ParticipantsDTO> 중에서 플레이어의 puuid가 일치하는 1개의 참여자만 추출
                    .map(
                        participant -> participant.getChampionName())) // 추출한 참여자의 챔피언 이름을 추출
            .collect(Collectors.toSet());   // set에 수집
        // TODO : 챔피언별 분석 데이터 생성
        log.info("챔피언 목록 : {}", championList);
        
        List<Map<String, ChampionAnalysis>> mapList = new ArrayList<>();
        championList.forEach(championName -> mapList.add(generateChampionAnalysis(list, championName)));

        return mapList;
    }

    public Map<String, ChampionAnalysis> generateChampionAnalysis(List<MatchDTO> list, String championName) {
        // TODO : 챔피언별 분석 데이터 생성
        Map<String, ChampionAnalysis> map = new HashMap<>();
        
        return map;
    }
}
