package com.uniqueauction.domain.review.entity;

import static com.utils.RegExpCode.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.user.entity.User;
import com.uniqueauction.web.review.request.SaveReviewRequest;
import com.utils.annotation.RegExp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

	@RegExp(regExpCode = SCORE)
	private int score;

	@RegExp(regExpCode = REVIEW_CONTENT)
	private String content;

	@Builder
	public Review(User user, Product product, int score, String content) {
		this.user = user;
		this.product = product;
		this.score = score;
		this.content = content;
	}

	//==생성 메서드==//
	public static Review createReview(User user, Product product, SaveReviewRequest reviewRequest) {
		return Review.builder()
			.user(user)
			.product(product)
			.score(reviewRequest.getScore())
			.content(reviewRequest.getContent())
			.build();
	}
}
