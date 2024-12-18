package com.mygg.sb.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mygg.sb.BaseDTO;
import com.mygg.sb.champion.ChampionDTO;
import com.mygg.sb.champion.ChampionEntity;
import com.mygg.sb.champion.ChampionRepository;
import com.mygg.sb.item.ItemDTO;
import com.mygg.sb.item.ItemEntity;
import com.mygg.sb.item.ItemRepository;
import com.mygg.sb.user.UserDTO;
import com.mygg.sb.user.UserEntity;
import com.mygg.sb.user.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchService {
    // 0.5초마다 검색창 업데이트 되는 기능의 일부
    // 3개(item, chap, user) 조회해서 like 연산해서 JSON 프론트에 반환
    private final ItemRepository itemRepo;
    private final ChampionRepository champRepo;
    private final UserRepository userRepo;

    // query를 받아서 검색하여 BaseDTO를 상속받는 객체(ItemDTO, ChampionDTO, UserDTO)를 반환하는 메서드
    public Map<String, List<? extends BaseDTO>> search(String query) {
        // 검색어 결과 JSON으로 반환해줄 객체
        Map<String, List<? extends BaseDTO>> result = new HashMap<>();
        // 아이템, 챔피언, 유저 정보를 각각 찾아서 넣음
        result.put("item", itemFind(query));
        result.put("champion", champFind(query));
        result.put("user", userFind(query));

        return result;
    }

    public List<ItemDTO> itemFind(String query) {
        int limit = 3;
        List<ItemEntity> itemTmp = itemRepo.findByNameStartingWith(query);
        itemTmp.subList(0, Math.min(limit, itemTmp.size()));
        // log.info("itemTmp: {}", itemTmp);
        if (itemTmp.size() < limit) {
            List<ItemEntity> itemTmp2 = (itemRepo.findByNameContaining(query)).stream()
                    .filter(item -> !item.getName().startsWith(query))
                    .collect(Collectors.toList());
            // log.info("itemTmp2: {}", itemTmp2);
            itemTmp.addAll(itemTmp2.subList(0, Math.min(limit, itemTmp2.size())));
        }
        List<ItemDTO> result = new ArrayList<>();
        if (itemTmp.isEmpty())
            return null;
        for (ItemEntity entity : itemTmp) {
            result.add(ItemDTO.toDTO(entity));
        }
        return result;
    }

    public List<ChampionDTO> champFind(String query) {
        int limit = 3;
        // champTmp : query로 시작하는 챔피언, 3명 이하
        List<ChampionEntity> champTmp = champRepo.findByNameStartingWith(query);
        champTmp.subList(0, Math.min(limit, champTmp.size()));
        // log.info("champTmp: {}", champTmp);
        // query로 시작하는 챔피언이 3명 이하라면 query가 포함되는 챔피언도 3명 추가
        if (champTmp.size() < limit) {
            // 중복제거 query가 포함되는 챔피언 중 query로 시작하는 챔피언은 제거
            List<ChampionEntity> champTmp2 = (champRepo.findByNameContaining(query)).stream()
                    .filter(champ -> !champ.getName().startsWith(query))
                    .collect(Collectors.toList());
            champTmp.addAll(champTmp2.subList(0, Math.min(limit, champTmp2.size())));
        }
        List<ChampionDTO> result = new ArrayList<>();
        if (champTmp.isEmpty())
            return null;
        for (ChampionEntity entity : champTmp) {
            result.add(ChampionDTO.toDTO(entity));
        }
        return result;
    }

    public List<UserDTO> userFind(String query) {
        int limit = 3;
        List<UserEntity> userTmp = new ArrayList<>();
        if (query.contains("#")) {
            String gameName = query.split("#")[0];
            String nameTag = query.split("#")[1];
            // userTmp : gameName이 정확하고 tagLine에 포함되는 유저
            userTmp = userRepo.findByGameNameAndTagLineContaining(gameName, nameTag);
        } else {
            // userTmp2 : gameName이 query로 시작하는 유저
            List<UserEntity> userTmp2 = userRepo.findByGameNameStartingWith(query);
            // query로 시작하는 유저가 3명 이하라면 query가 포함되는 유저도 3명 추가
            if (userTmp2.size() < limit) {
                // userTmp3 : gameName에 query가 포함되는 유저
                List<UserEntity> userTmp3 = userRepo.findByGameNameContaining(query);
                // 중복제거(userTmp2에 있는 유저는 제거)
                userTmp3 = userTmp3.stream()
                        .filter(user -> !user.getGameName().startsWith(query))
                        .collect(Collectors.toList());
                // userTmp2에 userTmp3의 3명 이하를 추가
                userTmp2.addAll(userTmp3.subList(0, Math.min(limit, userTmp3.size())));
            }
            // userTmp(최종결과)를 3명으로 제한
            userTmp = userTmp2.subList(0, Math.min(limit, userTmp2.size()));
        }
        List<UserDTO> result = new ArrayList<>();
        if (userTmp.isEmpty())
            return null;
        for (UserEntity entity : userTmp) {
            result.add(UserDTO.toDTO(entity));
        }
        return result;
    }
}
