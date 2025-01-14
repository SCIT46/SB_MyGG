package com.mygg.sb.analysis;

import java.util.Map;

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
@Data
public class UserAnalysisDTO {
    private String puuid;
    private Map<String, ChampionAnalysis> championAnalysis;

    public static UserAnalysisDTO toDTO(UserAnalysisEntity entity) {
        return UserAnalysisDTO.builder()
            .puuid(entity.getPuuid())
            .championAnalysis(entity.getChampionAnalysis())
            .build();
    }
}   
