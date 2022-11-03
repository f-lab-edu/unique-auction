package com.uniqueauction.web.review.response;

import java.util.List;

import com.uniqueauction.domain.product.entity.Category;
import com.uniqueauction.domain.product.entity.Product;

import lombok.Getter;

@Getter
public class ReviewByProductResponse {

	private String productName;
	private Category category;
	private String brand;
	private String imgUrl;
	List<ReviewInfo> reviews;

	public ReviewByProductResponse(List<ReviewInfo> reviews) {
		this.reviews = reviews;
	}

	public static ReviewByProductResponse of(List<ReviewInfo> reviews) {
		return new ReviewByProductResponse(reviews);
	}

	public void addProductInfo(Product product) {
		this.productName = product.getName();
		this.category = product.getCategory();
		this.brand = product.getBrand();
		this.imgUrl = product.getImgUrl();
	}
}
