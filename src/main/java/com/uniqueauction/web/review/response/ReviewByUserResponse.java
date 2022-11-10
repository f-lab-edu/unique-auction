package com.uniqueauction.web.review.response;

import java.util.List;

import com.uniqueauction.domain.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ReviewByUserResponse {

	private String email;
	private String username;
	List<ReviewInfo> reviews;

	public ReviewByUserResponse(List<ReviewInfo> reviews) {
		this.reviews = reviews;
	}

	public static ReviewByUserResponse of(List<ReviewInfo> reviews) {
		return new ReviewByUserResponse(reviews);
	}

	public void addUserInfo(User user) {
		this.email = user.getEmail();
		this.username = user.getUsername();
	}
}
