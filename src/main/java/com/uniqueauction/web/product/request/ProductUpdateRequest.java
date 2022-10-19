package com.uniqueauction.web.product.request;

import javax.validation.constraints.NotBlank;

import com.uniqueauction.domain.product.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductUpdateRequest {

	@NotBlank(message = "모델번호는 공백은 입력할 수 없습니다.")
	private String modelNumber;

	@NotBlank(message = "발매가는 공백은 입력할 수 없습니다.")
	private String releasePrice;

	@NotBlank(message = "사이즈는 공백은 입력할 수 없습니다.")
	private String size;

	@NotBlank(message = "카테고리는 공백은 입력할 수 없습니다.")
	private String category;

	@NotBlank(message = "제고는 공백은 입력할 수 없습니다.")
	private String stock;

	public Product convert() {
		return Product.builder()
			.modelNumber(this.modelNumber)
			.releasePrice(this.releasePrice)
			.category(this.category).build();
	}

}
