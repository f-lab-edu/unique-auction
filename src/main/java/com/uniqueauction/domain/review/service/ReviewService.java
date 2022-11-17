package com.uniqueauction.domain.review.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.review.entity.Review;
import com.uniqueauction.domain.review.repository.ReviewRepository;
import com.uniqueauction.domain.user.entity.User;
import com.uniqueauction.event.ReviewEventPublisher;
import com.uniqueauction.web.review.request.SaveReviewRequest;
import com.uniqueauction.web.review.response.ReviewByProductResponse;
import com.uniqueauction.web.review.response.ReviewByUserResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReviewService {

	private final ReviewRepository reviewRepository;
	private final ReviewEventPublisher reviewEventPublisher;

	@Transactional()
	public Review save(SaveReviewRequest reviewRequest) {

		Product product = reviewEventPublisher.getProduct(reviewRequest.getProductId());
		User user = reviewEventPublisher.getUser(reviewRequest.getUserId());

		Review review = Review.createReview(user, product, reviewRequest);

		return reviewRepository.save(review);

	}

	@Transactional(readOnly = true)
	public ReviewByProductResponse findByProductId(Long productId) {

		ReviewByProductResponse response = ReviewByProductResponse.of(reviewRepository.findByProductId(productId));

		response.addProductInfo(reviewEventPublisher.getProduct(productId));

		return response;
	}

	@Transactional(readOnly = true)
	public ReviewByUserResponse findByUserId(Long userId) {

		ReviewByUserResponse response = ReviewByUserResponse.of(reviewRepository.findByUserId(userId));
		response.addUserInfo(reviewEventPublisher.getUser(userId));
		return response;

	}

}
