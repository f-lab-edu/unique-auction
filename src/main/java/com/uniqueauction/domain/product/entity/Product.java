package com.uniqueauction.domain.product.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Product {
	private Long id;
	private String modelNumber;
	private String releasePrice;
	private Category category;
	private String brand;
	private String imgUrl;
}
