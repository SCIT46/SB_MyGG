package com.mygg.sb.analysis;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnalysisRepository extends MongoRepository<UserAnalysisEntity, String> {
    Optional<UserAnalysisEntity> findById(String id);

    Optional<UserAnalysisEntity> findByPuuid(String puuid);
}
