package com.uniqueauction.domain.product.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
public class Product {
	@Id
	@Setter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String modelNumber;
	private String releasePrice;
	private Category category;
	private String brand;
	private String imgUrl;

	@Builder
	public Product(Long id, String name, String brand, String modelNumber, String releasePrice, Category category,
		String imgUrl) {
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.modelNumber = modelNumber;
		this.releasePrice = releasePrice;
		this.category = category;
		this.imgUrl = imgUrl;
	}
}
