package com.uniqueauction.domain.review.service;

import static com.uniqueauction.exception.ErrorCode.*;

import org.springframework.stereotype.Service;

import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.product.repository.ProductRepository;
import com.uniqueauction.domain.review.entity.Review;
import com.uniqueauction.domain.user.entity.User;
import com.uniqueauction.domain.user.repository.UserRepository;
import com.uniqueauction.exception.advice.CommonNotFoundException;
import com.uniqueauction.web.review.request.SaveReviewRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReviewService {

	private final ReviewRepository reviewRepository;
	private final ProductRepository productRepository;
	private final UserRepository userRepository;

	public Long save(SaveReviewRequest reviewRequest) {
		Product product = getProduct(reviewRequest);
		User user = getUser(reviewRequest);

		Review review = Review.createReview(user, product, reviewRequest);

		return reviewRepository.save(review).getId();
	}

	public Review findByProductId(Long productId) {
		return reviewRepository.findByProductId(productId);
	}

	private User getUser(SaveReviewRequest reviewRequest) {
		return userRepository.findById(reviewRequest.getUserId())
			.orElseThrow(() -> new CommonNotFoundException(NOT_FOUND_USER));
	}

	private Product getProduct(SaveReviewRequest reviewRequest) {
		return productRepository.findById(reviewRequest.getProductId())
			.orElseThrow(() -> new CommonNotFoundException(NOT_FOUND_PRODUCT));
	}

	public Review findByUserId(Long userId) {
		return reviewRepository.findByUserId(userId);

	}
}