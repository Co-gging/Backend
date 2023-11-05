package com.cogging.cogging.repository;

import com.cogging.cogging.entity.CommunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommunityRepository extends JpaRepository<CommunityEntity, Integer> {

    List<CommunityEntity> findAll();

    Optional<CommunityEntity> findById(Integer id);
}
