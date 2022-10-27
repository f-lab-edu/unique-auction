package com.uniqueauction.web.review;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uniqueauction.domain.review.service.ReviewRepository;
import com.uniqueauction.domain.review.service.ReviewService;
import com.uniqueauction.web.response.CommonResponse;
import com.uniqueauction.web.review.request.SaveReviewRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ReviewController {

	private final ReviewService reviewService;
	private final ReviewRepository reviewRepository;

	@GetMapping("/review")
	public CommonResponse<?> saveReview(SaveReviewRequest saveReviewRequest, BindingResult result) {
		reviewService.save(saveReviewRequest);
		return CommonResponse.success();
	}

	@PostMapping("/review/productRevies")
	public CommonResponse<?> selectProductReviews(Long productId) {
		return CommonResponse.success(reviewService.findByProductId(productId));
	}

	@PostMapping("/review/userProductReviews")
	public CommonResponse<?> selectUserProductReviews(Long userId) {
		return CommonResponse.success(reviewService.findByUserId(userId));
	}

}
