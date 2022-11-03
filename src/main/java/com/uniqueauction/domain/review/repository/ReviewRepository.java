package com.uniqueauction.domain.review.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uniqueauction.domain.review.entity.Review;
import com.uniqueauction.web.review.response.ReviewByProductResponse;
import com.uniqueauction.web.review.response.ReviewByUserResponse;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

	@Query("select  new com.uniqueauction.web.review.response.ReviewByProductResponse"
		+ "(r.score , r.content)"
		+ " from Review r where r.product.id = :productId")
	List<ReviewByProductResponse> findByProductId(@Param("productId") Long productId);

	@Query("select  new com.uniqueauction.web.review.response.ReviewByUserResponse"
		+ "(r.score , r.content)"
		+ " from Review r where r.user.id = :userId")
	List<ReviewByUserResponse> findByUserId(@Param("userId") Long userId);
}
