package com.uniqueauction.web.review.response;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ReviewInfo {

	private final int score;
	private final String content;

	public ReviewInfo(int score, String content) {
		this.score = score;
		this.content = content;
	}
}
