package com.mygg.sb.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	List<UserEntity> findByGameNameContaining(String gameName);
    List<UserEntity> findByGameNameContains(String gameName);
}
