package com.uniqueauction.domain.product.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String modelNumber;
	private String releasePrice;
	private String category;

	private String imgUrl;

	@Builder
	public Product(String modelNumber, String releasePrice, String category) {
		this.modelNumber = modelNumber;
		this.releasePrice = releasePrice;
		this.category = category;
	}
}
