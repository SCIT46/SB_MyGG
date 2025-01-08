package com.mygg.sb.user;

import java.time.LocalDateTime;

import com.mygg.sb.search.SearchBaseDTO;

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
public class UserDTO extends SearchBaseDTO {
    private Long id;

    private String leagueId;
    private String puuid;
    private String summonerId;
    // 유저 이미지(초상화)
    private int profileIconId;
    // 유저 이름
    private String gameName;
    // 유저 태그
    private String tagLine;
    // 유저 레벨
    private int summonerLevel;
    // 유저 등급
    private String tier;
    private String rank;
    // 유저 lp점수
    private int leaguePoints;
    // 유저 승패
    private int wins;
    private int losses;
    // 최근 갱신 날짜
    private LocalDateTime revisionDate;
    // 검색 횟수
    private long searchCount;
    // 최근 매치 목록
    private String[] matchList;

    public static UserDTO toDTO(UserEntity entity) {
        return UserDTO.builder()
                .id(entity.getId())
                .leagueId(entity.getLeagueId())
                .puuid(entity.getPuuid())
                .summonerId(entity.getSummonerId())
                .profileIconId(entity.getProfileIconId())
                .gameName(entity.getGameName())
                .tagLine(entity.getTagLine())
                .summonerLevel(entity.getSummonerLevel())
                .tier(entity.getTier())
                .rank(entity.getRank())
                .leaguePoints(entity.getLeaguePoints())
                .wins(entity.getWins())
                .losses(entity.getLosses())
                .revisionDate(entity.getRevisionDate())
                .searchCount(entity.getSearchCount())
                .build();
    }
}
