package com.uniqueauction.web.product.response;

import com.uniqueauction.domain.product.entity.Product;

import lombok.Getter;

@Getter
public class SearchProductResponse {

	private final String productName;
	private final String modelNumber;
	private final String releasePrice;
	private final String brand;

	public SearchProductResponse(Product product) {
		this.productName = product.getName();
		this.modelNumber = product.getModelNumber();
		this.releasePrice = product.getReleasePrice();
		this.brand = product.getBrand();
	}
}
