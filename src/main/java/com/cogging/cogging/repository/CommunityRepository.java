package com.cogging.cogging.repository;

import com.cogging.cogging.entity.CommunityEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<CommunityEntity, Integer> {

}
