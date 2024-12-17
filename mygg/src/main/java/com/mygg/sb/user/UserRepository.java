package com.mygg.sb.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByGameNameContaining(String gameName);

    List<UserEntity> findByGameNameContains(String gameName);

    Optional<UserEntity> findByGameNameAndTagLine(String gameName, String tagLine);
}
