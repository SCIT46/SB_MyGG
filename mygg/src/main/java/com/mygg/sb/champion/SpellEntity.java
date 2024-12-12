package com.mygg.sb.champion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SpellEntity {
    // 챔피언 스킬 아이디(챔피언이름+Q/W/E/R)
    @Id
    @Column(name="id")
    private String id;

    // 챔피언 스킬 이름
    @Column(name="name")
    private String name;

    // 챔피언 스킬 설명
    @Column(name="description")
    private String description;

    // 챔피언 스킬 최대 레벨
    @Column(name="maxrank")
    private String maxrank;

    // 챔피언 스킬 쿨타임(문자열)
    @Column(name="cooldownBurn")
    private String cooldownBurn;

    // 챔피언 스킬 소모량(문자열)
    @Column(name="costBurn")
    private String costBurn;
}