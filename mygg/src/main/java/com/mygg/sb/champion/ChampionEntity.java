package com.mygg.sb.champion;

import java.util.List;
import java.util.Map;

import jakarta.persistence.Id;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "champion")
public class ChampionEntity {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "c_key")
    private String key;

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "blurb")
    private String blurb;

    @Embedded
    private InfoDTO info;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "champion_stats", joinColumns = @JoinColumn(name = "champion_id"))
    @Column(name = "stats")
    private Map<String, Number> stats;

    @Embedded
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "champion_spells", joinColumns = @JoinColumn(name = "champion_id"))
    @Column(name = "spells")
    private List<SpellDTO> spells;

    public ChampionEntity toEntity(ChampionDTO dto){
        return ChampionEntity.builder()
                .id(dto.getId())
                .key(dto.getKey())
                .name(dto.getName())
                .title(dto.getTitle())
                .blurb(dto.getBlurb())
                .info(dto.getInfo())
                .stats(dto.getStats())
                .spells(dto.getSpells())
                .build();
    } 
}
