package com.uniqueauction.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uniqueauction.domain.review.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	Review findByProductId(Long productId);

	Review findByUserId(Long userId);
}
