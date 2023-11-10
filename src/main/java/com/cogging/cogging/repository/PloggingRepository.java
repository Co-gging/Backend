package com.cogging.cogging.repository;

import com.cogging.cogging.entity.Member;
import com.cogging.cogging.entity.Plogging;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PloggingRepository extends JpaRepository<Plogging, Integer> {
}
