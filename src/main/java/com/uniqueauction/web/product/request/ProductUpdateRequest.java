package com.uniqueauction.web.product.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.uniqueauction.domain.product.entity.Category;
import com.uniqueauction.domain.product.entity.Product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProductUpdateRequest {

	@NotBlank(message = "모델명는 공백은 입력할 수 없습니다.")
	private String productName;

	@NotNull(message = "상품ID는 공백은 입력할 수 없습니다.")
	private Long productId;

	@NotEmpty(message = "모델번호는 공백은 입력할 수 없습니다.")
	private String modelNumber;

	@NotEmpty(message = "발매가는 공백은 입력할 수 없습니다.")
	private String releasePrice;

	@NotNull(message = "카테고리는 공백은 입력할 수 없습니다.")
	private Category category;

	@NotEmpty(message = "브랜드는 공백은 입력할 수 없습니다.")
	private String brand;

	@NotBlank(message = "이미지는 공백은 입력할 수 없습니다.")
	private String imgUrl;

	@Builder
	private ProductUpdateRequest(Long productId, String productName, String modelNumber, String releasePrice,
		Category category, String imgUrl, String brand) {
		this.productId = productId;
		this.productName = productName;
		this.modelNumber = modelNumber;
		this.releasePrice = releasePrice;
		this.category = category;
		this.imgUrl = imgUrl;
		this.brand = brand;
	}

	public Product toEntity() {
		return Product.builder()
			.id(this.productId)
			.name(productName)
			.modelNumber(this.modelNumber)
			.releasePrice(this.releasePrice)
			.category(this.category)
			.brand(this.brand)
			.imgUrl(imgUrl)
			.build();
	}
}
