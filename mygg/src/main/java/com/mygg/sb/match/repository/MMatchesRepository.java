package com.mygg.sb.match.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mygg.sb.match.entity.MMatchEntity;

@Repository
public interface MMatchesRepository extends MongoRepository<MMatchEntity, String>
	{
		public Optional<MMatchEntity> findByInfoParticipantsPuuid(String puuid);

		//@Query(value = "{ 'info.participants.puuid': ?0 }", sort = "{ 'info.gameEndTimestamp': 1 }")
		public Page<MMatchEntity> findByInfoParticipantsPuuidOrderByInfoGameEndTimestamp(String puuid, Pageable pageable);

		public Page<MMatchEntity> findByInfoParticipantsPuuid(String puuid, Pageable page);	
	}
