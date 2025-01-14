package com.mygg.sb.analysis;

import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
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
@Document(collection = "user_analysis")
@Data
public class UserAnalysisEntity {
    @Id
    private String puuid;

    private Map<String, ChampionAnalysis> championAnalysis;

    public static UserAnalysisEntity toEntity(UserAnalysisDTO dto) {
        return UserAnalysisEntity.builder()
            .puuid(dto.getPuuid())
            .championAnalysis(dto.getChampionAnalysis())
            .build();
    }
}
