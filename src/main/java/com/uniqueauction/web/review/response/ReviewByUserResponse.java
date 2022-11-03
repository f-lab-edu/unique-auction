package com.uniqueauction.web.review.response;

import lombok.Getter;

@Getter
public class ReviewByUserResponse {
	private int score;
	private String Content;

	public ReviewByUserResponse(int score, String content) {
		this.score = score;
		Content = content;
	}
}
