package com.mygg.sb.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

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

@Service
@RequiredArgsConstructor
public class SearchService {
    
    private final ItemRepository itemRepo;
    private final ChampionRepository champRepo;
    private final UserRepository userRepo;

    public Map<String, List> search(String query){
        // 검색어 결과 JSON으로 반환해줄 객체
        Map<String, List> result = new HashMap<>();
        // 아이템, 챔피언, 유저 정보를 각각 찾아서 넣음
        result.put("item",itemFind(query));
        result.put("champion",champFind(query));
        result.put("user",userFind(query));

        return result;
    }

    public List<ItemDTO> itemFind(String query){
        List<ItemEntity> itemTmp = itemRepo.findByNameContaining(query);
        List<ItemDTO> result = new ArrayList<>();
        if(itemTmp.isEmpty())   return null;
        for(ItemEntity entity : itemTmp){
            result.add(ItemDTO.toDTO(entity));
        }
        return result;
    }

    public List<ChampionDTO> champFind(String query){
        List<ChampionEntity> champTmp = champRepo.findByNameContaining(query);
        List<ChampionDTO> result = new ArrayList<>();
        if(champTmp.isEmpty())   return null;
        for(ChampionEntity entity : champTmp){
            result.add(ChampionDTO.toDTO(entity));
        }
        return result;
    }

    public List<UserDTO> userFind(String query){
        // TODO: 검색어 상위 우선순위 반영하여 3명 반환 
        List<UserEntity> userTmp = userRepo.findByGameNameContaining(query);
        List<UserDTO> result = new ArrayList<>();
        if(userTmp.isEmpty())   return null;
        for(UserEntity entity : userTmp){
            result.add(UserDTO.toDTO(entity));
        }
        return result;
    }
}
