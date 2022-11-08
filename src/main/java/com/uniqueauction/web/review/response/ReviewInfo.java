package com.uniqueauction.web.review.response;

import lombok.Getter;

@Getter
public class ReviewInfo {

	private int score;
	private String content;

	public ReviewInfo(int score, String content) {
		this.score = score;
		this.content = content;
	}
}
