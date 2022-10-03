package com.uniqueAuction.web.trade.request;

import javax.validation.constraints.NotBlank;

import com.uniqueAuction.domain.trade.entity.Purchase;

import lombok.Getter;

@Getter
public class PurchaseNowRequest {

	@NotBlank(message = "유저ID는 필수값입니다.")
	private Long userId;

	@NotBlank(message = "모델번호는 필수값입니다.")
	private String modelNumber;

	@NotBlank(message = "사이즈는 필수값입니다.")
	private String productSize;

	@NotBlank(message = "배송주소는 필수값입니다.")
	private String shippingAddress;

	public Purchase toEntity() {
		return Purchase.builder()
			.userId(this.userId)
			.modelNumber(this.modelNumber)
			.productSize(this.productSize)
			.shippingAddress(this.shippingAddress)
			.build();
	}
}
