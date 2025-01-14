package com.mygg.sb.junseongTest;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface JunUserRpeository extends MongoRepository<JunTestUser, String>
	{
		List<JunTestUser> findByName(String name);
	}