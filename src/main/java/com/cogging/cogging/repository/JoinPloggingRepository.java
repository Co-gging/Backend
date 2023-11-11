package com.cogging.cogging.repository;

import com.cogging.cogging.entity.JoinPlogging;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JoinPloggingRepository extends JpaRepository<JoinPlogging, Integer> {
    Optional<JoinPlogging> findByMemberIdAndPloggingId(Integer memberId, Integer ploggingId);
}
