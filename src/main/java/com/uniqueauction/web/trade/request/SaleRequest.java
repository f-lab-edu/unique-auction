package com.uniqueauction.web.trade.request;

import javax.validation.constraints.NotBlank;

import com.uniqueauction.domain.trade.entity.Sale;

import lombok.Getter;

@Getter
public class SaleRequest {

	@NotBlank(message = "유저ID")
	private Long userId;

	@NotBlank(message = "모델번호")
	private String productId;

	@NotBlank(message = "사이즈")
	private String productSize;

	@NotBlank(message = "입찰가격")
	private String bidPrice;

	@NotBlank(message = "반송주소")
	private String returnAddress;

	public Sale toEntity() {
		return Sale.builder()
			.userId(this.userId)
			.productId(this.productId)
			.bidPrice(this.bidPrice)
			.productSize(this.productSize)
			.returnAddress(this.returnAddress)
			.build();
	}
}
