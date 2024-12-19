package com.mygg.sb.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
        Set<ItemEntity> itemTmp = new HashSet<>(itemRepo.findByNameStartingWith(query));
        if (itemTmp.size() < limit) {
            itemTmp.addAll(new HashSet<>(itemRepo.findByNameContaining(query)));
            itemTmp = itemTmp.stream().limit(limit).collect(Collectors.toSet());
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
        Set<ChampionEntity> champTmp = new HashSet<>(champRepo.findByNameStartingWith(query));
        if (champTmp.size() < limit) {
            champTmp.addAll(new HashSet<>(champRepo.findByNameContaining(query)));
            champTmp = champTmp.stream().limit(limit).collect(Collectors.toSet());
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
        Set<UserEntity> userTmp = new HashSet<>();
        log.info("query: {}", query);
        if (query.contains("#")) {
            String[] splitQuery = query.split("#");
            String gameName = splitQuery[0];
            String nameTag = splitQuery.length > 1 ? splitQuery[1] : "";
            userTmp.addAll(userRepo.findByGameNameAndTagLineContaining(gameName, nameTag));
        } else {
            userTmp.addAll(userRepo.findByGameNameStartingWith(query));
        }
        if (userTmp.size() < limit) {
            userTmp.addAll(userRepo.findByGameNameContaining(query, Sort.by(Direction.DESC, "searchCount")));
            userTmp = userTmp.stream().limit(limit).collect(Collectors.toSet());
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