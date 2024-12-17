package com.mygg.sb.champion;

import java.util.List;
import java.util.Map;

import com.mygg.sb.BaseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ChampionDTO extends BaseDTO {
    // field
    private String id;
    private String key;
    private String name;
    private String title;
    private String blurb;
    private InfoDTO info;
    // private String[] tags;
    private Map<String, Number> stats;
    private List<SpellDTO> spells;

    // public ChampDTO(){
    // info = new InfoDTO();
    // spells = new SpellDTO[4];
    // }
    public static ChampionDTO toDTO(ChampionEntity entity) {
        return ChampionDTO.builder()
                .id(entity.getId())
                .key(entity.getKey())
                .name(entity.getName())
                .title(entity.getTitle())
                .blurb(entity.getBlurb())
                .info(entity.getInfo())
                .stats(entity.getStats())
                .spells(entity.getSpells())
                .build();
    }

}
