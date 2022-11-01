package com.uniqueauction.web.review.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.uniqueauction.domain.review.repository.ReviewRepository;
import com.uniqueauction.domain.review.service.ReviewService;
import com.uniqueauction.web.response.CommonResponse;
import com.uniqueauction.web.review.request.SaveReviewRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ReviewController {

	private final ReviewService reviewService;
	private final ReviewRepository reviewRepository;

	@PostMapping("/reviews")
	public CommonResponse<?> saveReview(SaveReviewRequest saveReviewRequest, BindingResult result) {
		reviewService.save(saveReviewRequest);
		return CommonResponse.success();
	}

	@GetMapping("/reviews/productReviwes/{productId}")
	@ResponseStatus(HttpStatus.OK)
	public CommonResponse selectProductReviews(@PathVariable Long productId) {
		return CommonResponse.success(reviewService.findByProductId(productId));
	}

	@GetMapping("/reviews/userProductReviews/{userId}")
	public CommonResponse<?> selectUserProductReviews(@PathVariable Long userId) {
		return CommonResponse.success(reviewService.findByUserId(userId));
	}
}
