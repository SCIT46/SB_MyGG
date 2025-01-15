package com.mygg.sb.junseongTest;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface JunUserRpeository2 extends MongoRepository<JunTestUser2, String>
	{
		List<JunTestUser> findByName(String name);
	}