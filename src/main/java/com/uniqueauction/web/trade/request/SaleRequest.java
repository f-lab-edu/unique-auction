package com.uniqueauction.web.trade.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.uniqueauction.domain.trade.entity.Sale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleRequest {

	@NotNull(message = "유저ID는 필수값입니다.")
	private Long userId;

	@NotNull(message = "모델번호는 필수값입니다.")
	private Long productId;

	@NotBlank(message = "사이즈는 필수값입니다.")
	private String productSize;

	@NotBlank(message = "입찰가격은 필수값입니다.")
	private String bidPrice;

	@NotBlank(message = "반송주소는 필수값입니다.")
	private String returnAddress;

	public Sale toEntity() {
		return Sale.builder()
			.userId(this.userId)
			.bidPrice(this.bidPrice)
			.productSize(this.productSize)
			.returnAddress(this.returnAddress)
			.build();
	}
}
