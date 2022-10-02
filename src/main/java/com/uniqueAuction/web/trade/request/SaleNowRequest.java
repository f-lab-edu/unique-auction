package com.uniqueAuction.web.trade.request;

import javax.validation.constraints.NotBlank;

import com.uniqueAuction.domain.trade.entity.Sale;

import lombok.Getter;

@Getter
public class SaleNowRequest {

	@NotBlank(message = "유저ID는 필수값입니다.")
	private Long userId;

	@NotBlank(message = "모델번호는 필수값입니다.")
	private String modelNumber;

	@NotBlank(message = "사이즈는 필수값입니다.")
	private String size;

	@NotBlank(message = "반송주소는 필수값입니다.")
	private String returnAddress;

	public Sale toEntity() {
		return Sale.builder()
			.userId(this.userId)
			.modelNumber(this.modelNumber)
			.size(this.size)
			.returnAddress(this.returnAddress)
			.build();
	}
}
