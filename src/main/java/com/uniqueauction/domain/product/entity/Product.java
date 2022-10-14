package com.uniqueauction.domain.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class Product {

	private Long id;
	private String modelNumber;
	private String releasePrice;
	private String size;
	private String category;
	private String stock;
}