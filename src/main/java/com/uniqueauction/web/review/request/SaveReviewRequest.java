package com.uniqueauction.web.review.request;

import static com.utils.RegExpCode.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.utils.annotation.RegExp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveReviewRequest {

	@NotNull(message = "유저아이디")
	private Long userId;

	@NotNull(message = "유저아이디")
	private Long productId;

	@NotNull(message = "평점")
	@Min(1)
	@Max(5)
	private int score;

	@RegExp(regExpCode = REVIEW_CONTENT)
	private String content;
}
