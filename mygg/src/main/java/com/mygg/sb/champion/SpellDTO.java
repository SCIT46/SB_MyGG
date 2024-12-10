package com.mygg.sb.champion;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class SpellDTO {
    // 챔피언 스킬 아이디(챔피언이름+Q/W/E/R)
    private String id;
    // 챔피언 스킬 이름
    private String name;
    // 챔피언 스킬 설명
    private String description;
    // 챔피언 스킬 최대 레벨
    private String maxrank;
    // 챔피언 스킬 쿨타임(배열)
    //private String[] cooldown;
    // 챔피언 스킬 쿨타임(문자열)
    private String cooldownBurn;
    // 챔피언 스킬 소모량(배열)
    //private String[] cost;
    // 챔피언 스킬 소모량(문자열)
    private String costBurn;
    // 챔피언 스킬 효과(배열)
    //private String[] effect;
    // 챔피언 스킬 효과(문자열)
    //private String[] effectBurn;
}
