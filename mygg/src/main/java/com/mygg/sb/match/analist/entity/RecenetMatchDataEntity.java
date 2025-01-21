package com.mygg.sb.match.analist.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RecenetMatchDataEntity 
{
    private int championId;       // 사용한 캐릭터
    private String championName;  // 사용한 캐릭터
    private int minionKilled;     // cs 개수
    private int neutralKilled;    // 몹 킬 개수
    private int kill;             // 킬 
    private int death;            // 데스
    private int assist;           // 어시스트 (오타 수정)
    private float kda;			  // kda
    private int winCnt;           // 승리수
    private int loseCnt;          // 패배수
    private int gameCnt;          // 게임수
}

