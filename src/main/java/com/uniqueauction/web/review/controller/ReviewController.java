package com.uniqueauction.web.review.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.uniqueauction.domain.review.entity.Review;
import com.uniqueauction.domain.review.service.ReviewService;
import com.uniqueauction.web.response.CommonResponse;
import com.uniqueauction.web.review.request.SaveReviewRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ReviewController {

	private final ReviewService reviewService;

	@PostMapping("/reviews")
	@ResponseStatus(HttpStatus.CREATED)
	public CommonResponse saveReview(@RequestBody @Validated SaveReviewRequest saveReviewRequest,
		BindingResult result) {
		Review review = reviewService.save(saveReviewRequest);
		return CommonResponse.success(review);
	}

	@GetMapping("/reviews/{productId}/products")
	@ResponseStatus(HttpStatus.OK)
	public CommonResponse selectProductReviews(@PathVariable Long productId) {
		return CommonResponse.success(reviewService.findByProductId(productId));
	}

	@GetMapping("/reviews/{userId}/users")
	@ResponseStatus(HttpStatus.OK)
	public CommonResponse selectUserProductReviews(@PathVariable Long userId) {
		return CommonResponse.success(reviewService.findByUserId(userId));
	}
}
