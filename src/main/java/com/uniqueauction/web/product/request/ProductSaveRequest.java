package com.uniqueauction.web.product.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.uniqueauction.domain.product.entity.Category;
import com.uniqueauction.domain.product.entity.Product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProductSaveRequest {

	@NotBlank(message = "상품명")
	private String productName;

	@NotBlank(message = "모델번호")
	private String modelNumber;

	@NotBlank(message = "발매가")
	private String releasePrice;

	@NotNull(message = "카테고리")
	private Category category;

	@NotBlank(message = "이미지주소")
	private String imgUrl;

	@NotBlank(message = "브랜드")
	private String brand;

	@Builder
	private ProductSaveRequest(String productName, String modelNumber, String releasePrice,
		Category category, String imgUrl, String brand) {
		this.productName = productName;
		this.modelNumber = modelNumber;
		this.releasePrice = releasePrice;
		this.category = category;
		this.imgUrl = imgUrl;
		this.brand = brand;
	}

	public Product toEntity() {
		return Product.builder()
			.name(productName)
			.modelNumber(this.modelNumber)
			.releasePrice(this.releasePrice)
			.category(this.category)
			.brand(this.brand)
			.imgUrl(imgUrl)
			.build();
	}
}
