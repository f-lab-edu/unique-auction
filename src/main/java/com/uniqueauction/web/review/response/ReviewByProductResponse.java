package com.uniqueauction.web.review.response;

import lombok.Getter;

@Getter
public class ReviewByProductResponse {
	private int score;
	private String Content;

	public ReviewByProductResponse(int score, String content) {
		this.score = score;
		Content = content;
	}
}
