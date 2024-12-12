package com.mygg.sb.champion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampionRepository extends JpaRepository<ChampionEntity, String> {
    List<ChampionEntity> findByNameContaining(String name);
    List<ChampionEntity> findByNameContains(String name);
    List<ChampionEntity> findByNameIsContaining(String name);
}
