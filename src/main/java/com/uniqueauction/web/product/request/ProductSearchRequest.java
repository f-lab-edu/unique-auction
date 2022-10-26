package com.uniqueauction.web.product.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductSearchRequest {

	@NotBlank(message = "모델명는 공백은 입력할 수 없습니다.")
	private String productName;

	@NotBlank(message = "모델번호는 공백은 입력할 수 없습니다.")
	private String modelNumber;

}
