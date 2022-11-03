package com.uniqueauction.domain.review.service;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.review.entity.Review;
import com.uniqueauction.domain.review.repository.ReviewRepository;
import com.uniqueauction.domain.user.entity.User;
import com.uniqueauction.event.ProductEvent;
import com.uniqueauction.event.UserEvent;
import com.uniqueauction.web.review.request.SaveReviewRequest;
import com.uniqueauction.web.review.response.ReviewByProductResponse;
import com.uniqueauction.web.review.response.ReviewByUserResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReviewService {

	private final ReviewRepository reviewRepository;
	private final ApplicationEventPublisher eventPublisher;

	@Transactional()
	public Long save(SaveReviewRequest reviewRequest) {

		Product product = getProduct(reviewRequest.getProductId());
		User user = getUser(reviewRequest.getUserId());

		Review review = Review.createReview(user, product, reviewRequest);

		return reviewRepository.save(review).getId();
	}

	@Transactional(readOnly = true)
	public List<ReviewByProductResponse> findByProductId(Long productId) {
		return reviewRepository.findByProductId(productId);
	}

	@Transactional(readOnly = true)
	public List<ReviewByUserResponse> findByUserId(Long userId) {
		return reviewRepository.findByUserId(userId);

	}

	private User getUser(Long userId) {
		UserEvent uEvnet = UserEvent.of(userId);

		eventPublisher.publishEvent(uEvnet);

		return uEvnet.getUser();
	}

	private Product getProduct(Long productId) {

		ProductEvent pEvent = ProductEvent.of(productId);

		eventPublisher.publishEvent(pEvent);

		return pEvent.getProduct();
	}

}
