package com.uniqueauction.domain.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.uniqueauction.domain.base.BaseEntity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@ToString
@Getter
@NoArgsConstructor
@Entity
public class Product extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
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

	public Product updateProduct(Product product) {
		this.name = product.getName();
		this.modelNumber = product.getModelNumber();
		this.releasePrice = product.getReleasePrice();
		this.category = product.getCategory();
		this.brand = product.getBrand();
		this.imgUrl = product.getImgUrl();

		return this;
	}
}
