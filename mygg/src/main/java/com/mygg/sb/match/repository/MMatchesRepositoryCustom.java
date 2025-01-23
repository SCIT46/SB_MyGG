package com.mygg.sb.match.repository;

import com.mygg.sb.match.analist.entity.RecenetMatchDataEntity;

import java.util.List;

public interface MMatchesRepositoryCustom {
    List<RecenetMatchDataEntity> getRecentMatchStatsForUser(String puuid);
}
