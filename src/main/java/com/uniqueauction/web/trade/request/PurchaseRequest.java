package com.uniqueauction.web.trade.request;

import javax.validation.constraints.NotBlank;

import com.uniqueauction.domain.trade.entity.Purchase;

import lombok.Getter;

@Getter
public class PurchaseRequest {

	@NotBlank(message = "유저ID")
	private Long userId;

	@NotBlank(message = "모델번호")
	private String productId;

	@NotBlank(message = "사이즈")
	private String productSize;

	@NotBlank(message = "입찰가격")
	private String bidPrice;

	@NotBlank(message = "배송주소")
	private String shippingAddress;

	public Purchase toEntity() {
		return Purchase.builder()
			.userId(this.userId)
			.productId(this.productId)
			.bidPrice(this.bidPrice)
			.productSize(this.productSize)
			.shippingAddress(this.shippingAddress)
			.build();
	}
}

