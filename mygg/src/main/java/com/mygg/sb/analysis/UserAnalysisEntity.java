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
    // 유저 puuid
    @Id
    private String puuid;

    // 최종 매치 아이디
    private String finalMatchId;

    // 총 승리/패배 횟수
    private int totalWin;
    private int totalLose;

    // 평균 KDA (비율 계산 공식 : ((킬 + 어시스트) / 데스)/1)
    private double averageKill;
    private double averageDeath;
    private double averageAssist;

    // 최근 60게임 챔피언 Top3 승패 비율, 평점
    //private Map<String, Integer> recentTop3WinRate;

    // 챔피언별 분석 데이터
    private Map<String, ChampionAnalysis> championAnalysis;

    public static UserAnalysisEntity toEntity(UserAnalysisDTO dto) {
        return UserAnalysisEntity.builder()
            .puuid(dto.getPuuid())
            .championAnalysis(dto.getChampionAnalysis())
            .build();
    }
}
