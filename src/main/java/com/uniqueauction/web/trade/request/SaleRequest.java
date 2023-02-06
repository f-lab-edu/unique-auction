package com.uniqueauction.web.trade.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.uniqueauction.domain.trade.entity.Sale;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SaleRequest {

	@NotNull(message = "유저ID")
	private Long userId;

	@NotNull(message = "모델번호")
	private Long productId;

	@NotBlank(message = "사이즈")
	private String productSize;

	@NotBlank(message = "입찰가격")
	private String bidPrice;

	@NotBlank(message = "반송주소")
	private String returnAddress;

	@NotBlank(message = "입찰마감기한")
	private String bidDueDate;

	public Sale toEntity() {
		return Sale.builder()
			.userId(this.userId)
			.id(this.productId)
			.bidPrice(this.bidPrice)
			.productSize(this.productSize)
			.returnAddress(this.returnAddress)
			.bidDueDate(this.bidDueDate)
			.build();
	}
}
