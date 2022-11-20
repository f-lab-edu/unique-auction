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

import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.product.service.ProductService;
import com.uniqueauction.domain.review.entity.Review;
import com.uniqueauction.domain.review.service.ReviewService;
import com.uniqueauction.domain.user.entity.User;
import com.uniqueauction.domain.user.service.UserService;
import com.uniqueauction.web.response.CommonResponse;
import com.uniqueauction.web.review.request.SaveReviewRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ReviewController {

	private final ReviewService reviewService;
	private final UserService userService;
	private final ProductService productService;

	@PostMapping("/reviews")
	@ResponseStatus(HttpStatus.CREATED)
	public CommonResponse saveReview(@RequestBody @Validated SaveReviewRequest saveReviewRequest,
		BindingResult result) {

		User user = userService.findById(saveReviewRequest.getUserId());
		Product product = productService.findById(saveReviewRequest.getProductId());

		reviewService.save(Review.createReview(user, product, saveReviewRequest));
		return CommonResponse.success();
	}

	@GetMapping("/reviews/{productId}/products")
	@ResponseStatus(HttpStatus.OK)
	public CommonResponse selectProductReviews(@PathVariable Long productId) {
		Product product = productService.findById(productId);

		return CommonResponse.success(reviewService.findByProductId(product));
	}

	@GetMapping("/reviews/{userId}/users")
	@ResponseStatus(HttpStatus.OK)
	public CommonResponse selectUserProductReviews(@PathVariable Long userId) {
		User user = userService.findById(userId);
		return CommonResponse.success(reviewService.findByUserId(user));
	}
}
