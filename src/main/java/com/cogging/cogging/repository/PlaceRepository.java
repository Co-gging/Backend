package com.cogging.cogging.repository;

import com.cogging.cogging.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Integer> {
    List<Place> findByNameOrAddressContaining(String nameKeyword, String addressKeyword);
    List<Place> findTop10ByOrderByScrapCountDesc();
}
