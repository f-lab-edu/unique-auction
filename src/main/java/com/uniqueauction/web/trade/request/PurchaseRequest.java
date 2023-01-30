package com.uniqueauction.web.trade.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.uniqueauction.domain.trade.entity.Purchase;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PurchaseRequest {

	@NotNull(message = "유저ID")
	private Long userId;

	@NotNull(message = "상품번호")
	private Long productId;

	@NotBlank(message = "사이즈")
	private String productSize;

	@NotBlank(message = "입찰가격")
	private String bidPrice;

	@NotBlank(message = "배송주소")
	private String shippingAddress;

	@Builder
	public PurchaseRequest(Long userId, Long productId, String productSize, String bidPrice, String shippingAddress) {
		this.userId = userId;
		this.productId = productId;
		this.productSize = productSize;
		this.bidPrice = bidPrice;
		this.shippingAddress = shippingAddress;
	}

	public Purchase toEntity() {
		return Purchase.builder()
			.userId(this.userId)
			.id(this.productId)
			.bidPrice(this.bidPrice)
			.productSize(this.productSize)
			.shippingAddress(this.shippingAddress)
			.build();
	}
}
