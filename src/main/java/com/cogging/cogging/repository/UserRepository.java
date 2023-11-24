package com.cogging.cogging.repository;

import com.cogging.cogging.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findById(int id);
    Optional<User> findByEmail(String email);
    Optional<User> findByNickname(String nickname);
    List<User> findAll(Sort sort);
}
