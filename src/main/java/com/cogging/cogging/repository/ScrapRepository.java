package com.cogging.cogging.repository;

import com.cogging.cogging.entity.Place;
import com.cogging.cogging.entity.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapRepository extends JpaRepository<Scrap, Integer> {
}
