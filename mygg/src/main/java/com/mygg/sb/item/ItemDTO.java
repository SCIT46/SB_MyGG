package com.mygg.sb.item;

import java.util.List;
import java.util.Map;

import com.mygg.sb.search.SearchBaseDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDTO extends SearchBaseDTO {
    // field
    // 아이템 아이디
    private String id;
    // 아이템 이름
    private String name;
    // 아이템 설명
    private String description;
    // 아이템 설명(평문)
    // private String plaintext;
    // 아이템 제작소비 아이템 아이디
    private List<String> from;
    // 아이템 제작 아이템 아이디
    private List<String> into;
    // 아이템 골드 정보
    private GoldDTO gold;
    // 아이템 태그
    // private ArrayList<String> tags;
    // 아이템 사용되는 맵
    private Map<String, Boolean> maps;
    // 아이템 특수 레시피
    // private String[] specialRecipe;
    // 아이템 성능(효과) 정보
    // private statsDto stats;
    private Map<String, Number> stats;

    public static ItemDTO toDTO(ItemEntity entity) {
        return ItemDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .from(entity.getFrom())
                .into(entity.getInto())
                .gold(entity.getGold()) // GoldDTO도 적절히 변환
                .maps(entity.getMaps())
                .stats(entity.getStats())
                .build();
    }
}
