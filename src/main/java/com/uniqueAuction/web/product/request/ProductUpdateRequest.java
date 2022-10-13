package com.uniqueAuction.web.product.request;

import javax.validation.constraints.NotBlank;

import com.uniqueAuction.domain.product.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
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
			.size(this.size)
			.category(this.category)
			.stock(this.stock).build();
	}

}
