package com.cogging.cogging.repository;

import com.cogging.cogging.entity.Member;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findById(int id);
    Optional<Member> findByEmail(String email);
    Optional<Member> findByNickname(String nickname);

    @Override
    List<Member> findAll(Sort sort);
}
