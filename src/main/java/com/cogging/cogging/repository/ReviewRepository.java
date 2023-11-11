package com.cogging.cogging.repository;

import com.cogging.cogging.controller.ReviewController;
import com.cogging.cogging.entity.Plogging;
import com.cogging.cogging.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
