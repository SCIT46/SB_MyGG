package com.mygg.sb.user;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")    //인덱스
    private Long id; //약 21.4억명의 유저(양의 정수 기준) 저장가능

    @Column(name = "leagueId")
    private String leagueId;

    @Column(name = "puuid")
    private String puuid;
   
    @Column(name = "summonerId")
    private String summonerId;

    @Column(name = "profileIconId")
    private int profileIconId;

    @Column(name = "gameName")
    private String gameName;

    @Column(name = "tagLine")
    private String tagLine;
    
    @Column(name = "summonerlevel")
    private int summonerLevel;
    
    @Column(name = "tier")
    private String tier;

    @Column(name = "_rank") //rank는 DB 예약어이기 때문에 사용불가
    private String rank;

    @Column(name = "leaguePoints")
    private int leaguePoints;

    @Column(name = "wins")
    private int wins;

    @Column(name = "losses")
    private int losses;

    @Column(name = "revisionDate")
    private LocalDateTime revisionDate;

    public static UserEntity toEntity(UserDTO dto){
        return UserEntity.builder()
                .id(dto.getId())
                .leagueId(dto.getLeagueId())
                .puuid(dto.getPuuid())
                .summonerId(dto.getSummonerId())
                .profileIconId(dto.getProfileIconId())
                .gameName(dto.getGameName())
                .tagLine(dto.getTagLine())
                .summonerLevel(dto.getSummonerLevel())
                .tier(dto.getTier())
                .rank(dto.getRank())
                .leaguePoints(dto.getLeaguePoints())
                .wins(dto.getWins())
                .losses(dto.getLosses())
                .revisionDate(dto.getRevisionDate())
                .build();
    }
}
