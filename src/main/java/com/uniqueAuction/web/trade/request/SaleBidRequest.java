package com.uniqueAuction.web.trade.request;

import javax.validation.constraints.NotBlank;

import com.uniqueAuction.domain.trade.entity.Sale;

import lombok.Getter;

@Getter
public class SaleBidRequest {

	@NotBlank(message = "유저ID는 필수값입니다.")
	private Long userId;

	@NotBlank(message = "모델ID는 필수값입니다.")
	private Long productId;

	@NotBlank(message = "사이즈는 필수값입니다.")
	private String size;

	@NotBlank(message = "입찰가격은 필수값입니다.")
	private String bidPrice;

	@NotBlank(message = "반송주소는 필수값입니다.")
	private String returnAddress;

	public Sale toEntity() {

		return Sale.builder()
			.userId(this.userId)
			.productId(this.productId)
			.bidPrice(this.bidPrice)
			.size(this.size)
			.returnAddress(this.returnAddress)
			.build();
	}
}
