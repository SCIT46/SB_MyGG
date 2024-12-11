package com.mygg.sb.user;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")    //인덱스
    private Long id; //약 21.4억명의 유저(양의 정수 기준) 저장가능

    @Column(name = "leagueId")
    private Integer leagueId;

    @Column(name = "profileIconId")
    private Integer profileIconId;

    @Column(name = "gameName")
    private String gameName;

    @Column(name = "tagLine")
    private String tagLine;
    
    @Column(name = "summonerlevel")
    private Integer summonerLevel;
    
    @Column(name = "tier")
    private String tier;

    @Column(name = "_rank") //rank는 DB 예약어이기 때문에 사용불가
    private String rank;

    @Column(name = "lastUpdate")
    private LocalDateTime lastUpdate;

}
