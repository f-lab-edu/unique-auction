package com.uniqueauction.domain.review.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.review.entity.Review;
import com.uniqueauction.domain.review.repository.ReviewRepository;
import com.uniqueauction.domain.user.entity.User;
import com.uniqueauction.web.review.response.ReviewByProductResponse;
import com.uniqueauction.web.review.response.ReviewByUserResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReviewService {

	private final ReviewRepository reviewRepository;

	@Transactional()
	public Long save(Review review) {

		return reviewRepository.save(review).getId();
	}

	@Transactional(readOnly = true)
	public ReviewByProductResponse findByProductId(Product product) {

		ReviewByProductResponse response = ReviewByProductResponse.of(
			reviewRepository.findByProductId(product.getId()));

		response.addProductInfo(product);

		return response;
	}

	@Transactional(readOnly = true)
	public ReviewByUserResponse findByUserId(User user) {

		ReviewByUserResponse response = ReviewByUserResponse.of(reviewRepository.findByUserId(user.getId()));
		response.addUserInfo(user);
		return response;

	}
}
