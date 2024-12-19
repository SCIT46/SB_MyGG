package com.mygg.sb.item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, String> {
    List<ItemEntity> findByNameContaining(String name);

    List<ItemEntity> findByNameContains(String name);

    List<ItemEntity> findByNameIsContaining(String name);

    List<ItemEntity> findByNameStartingWith(String name);
}
