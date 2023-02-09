package com.uniqueauction.web.trade.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.trade.entity.Trade;
import com.uniqueauction.domain.trade.entity.TradeStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TradeRequest {
	@NotNull(message = "유저ID")
	private Long userId;

	@NotNull(message = "상품번호")
	private Long productId;

	@NotBlank(message = "사이즈")
	private String productSize;

	@NotNull(message = "입찰가격")
	private Long price;

	@NotBlank(message = "배송주소")
	private String shippingAddress;

	@Builder
	public TradeRequest(Long userId, Long productId, String productSize, Long price, String shippingAddress) {
		this.userId = userId;
		this.productId = productId;
		this.productSize = productSize;
		this.price = price;
		this.shippingAddress = shippingAddress;
	}

	public Trade convertForBuyer(Product product) {
		return Trade.builder()
			.price(this.price)
			.productSize(this.productSize)
			.tradeStatus(TradeStatus.PURCHASE_PROGRESS)
			.product(product)
			.publisherId(this.userId)
			.buyerId(this.userId)
			.shippingAddress(this.shippingAddress)
			.build();
	}

	public Trade convertForSeller(Product product) {
		return Trade.builder()
			.price(this.price)
			.productSize(this.productSize)
			.tradeStatus(TradeStatus.SALE_PROGRESS)
			.product(product)
			.publisherId(this.userId)
			.sellerId(this.userId)
			.shippingAddress(this.shippingAddress)
			.build();
	}

}
