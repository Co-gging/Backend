package com.cogging.cogging.repository;

import com.cogging.cogging.entity.Community;
import com.cogging.cogging.entity.Member;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityRepository extends JpaRepository<Community, Integer> {
    List<Community> findAll(Sort sort);
}
