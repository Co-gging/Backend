package com.cogging.cogging.repository;

import com.cogging.cogging.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PloggingRepository extends JpaRepository<Member, Integer> {
}
