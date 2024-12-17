package com.mygg.sb.match.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mygg.sb.match.Entity.UserMatchEntity;

public interface UserMatchesRepository extends JpaRepository<UserMatchEntity, Integer>
	{
		UserMatchEntity findFirstByOrderByIdDesc();
	}
