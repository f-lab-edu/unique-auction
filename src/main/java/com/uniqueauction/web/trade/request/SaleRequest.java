package com.uniqueauction.web.trade.request;

import javax.validation.constraints.NotBlank;

import com.uniqueauction.domain.trade.entity.Sale;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaleRequest {

	@NotBlank(message = "유저ID는 필수값입니다.")
	private Long userId;

	@NotBlank(message = "모델번호는 필수값입니다.")
	private String productId;

	@NotBlank(message = "사이즈는 필수값입니다.")
	private String productSize;

	@NotBlank(message = "입찰가격은 필수값입니다.")
	private String bidPrice;

	@NotBlank(message = "반송주소는 필수값입니다.")
	private String returnAddress;

	public Sale convert() {
		return Sale.builder()
			.userId(this.userId)
			.bidPrice(this.bidPrice)
			.productSize(this.productSize)
			.returnAddress(this.returnAddress)
			.build();
	}
}
