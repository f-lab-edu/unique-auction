package com.uniqueauction.web.trade.request;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
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

	public Trade convertForBuyer(Long userId, Product product, String address) {
		return Trade.builder()
			.price(this.price)
			.productSize(this.productSize)
			.tradeStatus(TradeStatus.PURCHASE_PROGRESS)
			.product(product)
			.publisherId(userId)
			.buyerId(userId)
			.shippingAddress(address)
			.build();
	}

	public Trade convertForSeller(Long userId, Product product, String address) {
		return Trade.builder()
			.price(this.price)
			.productSize(this.productSize)
			.tradeStatus(TradeStatus.SALE_PROGRESS)
			.product(product)
			.publisherId(userId)
			.sellerId(userId)
			.shippingAddress(address)
			.build();
	}

}
