package com.uniqueauction.web.product.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.uniqueauction.domain.product.entity.Category;
import com.uniqueauction.domain.product.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductSaveRequest {

	@NotBlank(message = "모델명는 공백은 입력할 수 없습니다.")
	private String productName;

	@NotBlank(message = "모델번호는 공백은 입력할 수 없습니다.")
	private String modelNumber;

	@NotBlank(message = "발매가는 공백은 입력할 수 없습니다.")
	private String releasePrice;

	@NotNull(message = "카테고리는 공백은 입력할 수 없습니다.")
	private Category category;

	@NotBlank(message = "이미지주소는 공백은 입력할 수 없습니다.")
	private String imgUrl;

	@NotBlank(message = "브랜드는 공백은 입력할 수 없습니다.")
	private String brand;

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
