package com.uniqueauction.web.review.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;

@Getter
public class SaveReviewRequest {

	@NotNull(message = "유저아이디")
	private Long userId;

	@NotNull(message = "유저아이디")
	private Long productId;

	@NotNull(message = "점수")

	@Min(1)
	@Max(5)
	@NotNull
	private int score;

	@Length(max = 100)
	private String content;

}
