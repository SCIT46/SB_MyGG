package com.mygg.sb.match.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mygg.sb.match.entity.MMatchEntity;

@Repository
public interface MMatchesRepository extends MongoRepository<MMatchEntity, String>
	{

	}
