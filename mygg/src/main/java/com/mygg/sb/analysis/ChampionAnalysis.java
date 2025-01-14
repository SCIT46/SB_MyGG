package com.mygg.sb.analysis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChampionAnalysis {
    // 챔피언 아이디
    private String championId;
    
    // 승패수
    private int win;
    private int lose;
    
    // KDA
    private double kill;
    private double death;
    private double assist;
    private double totalKAPercent;  // 킬관여율

    // Score/Rank
    private double averageScore; // 평균 점수
    private double averageRank;  // 평균 순위
    
    private double lineScore;  // 라인 점수
    private double lineLeadPercent;  // 라인 리드 퍼센트

    // DMG
    private int damagePerMin;  // 분당 데미지(딜량)
    private int totalDamagePercent;  // 데미지 관여율

    // Ward
    private int visionScore;  // 시야 점수
    private int controlWardPlaced; // 와드 설치 수
    private int controlWardKill;  // 와드 제거 수

    // CS
    private int averageCS;  // 평균 챔스터 점수
    private int csPerMin;  // 분당 챔스터 점수

    // Gold
    private int averageGold;  // 평균 골드
    private int goldPerMin;  // 분당 골드

    // ChainKill
    private ChainKillScore chainKillScore;  // 체인킬 수
    
}
