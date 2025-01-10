package com.mygg.sb.item;

import java.util.Map;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "item")
public class ItemEntity {

    // 아이템 아이디
    @Id
    @Column(name = "id")
    private String id;

    // 아이템 이름
    @Column(name = "name")
    private String name;

    // 아이템 설명
    @Column(name = "description", length = 1000)
    private String description;

    // 아이템 제작소비 아이템 아이디
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "item_from", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "from_v")
    private List<String> from;

    // 아이템 제작 아이템 아이디
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "item_into", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "into_v")
    private List<String> into;

    // 아이템 골드 정보
    @Embedded
    private GoldDTO gold;

    // 아이템 사용되는 맵
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "item_maps", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "maps")
    private Map<String, String> maps;

    // 아이템 성능(효과) 정보
    @ElementCollection
    @CollectionTable(name = "item_stats", joinColumns = @JoinColumn(name = "item_id"))
    @MapKeyColumn(name = "stat_name")
    @Column(name = "stats", length = 1000)
    private Map<String, Number> stats;

    public static ItemEntity toEntity(ItemDTO dto) {
        return ItemEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .from(dto.getFrom())
                .into(dto.getInto())
                .gold(dto.getGold()) // GoldDTO도 적절히 변환
                .maps(dto.getMaps())
                .stats(dto.getStats())
                .build();
    }
}
