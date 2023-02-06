package com.uniqueauction.web.trade.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.trade.entity.Trade;
import com.uniqueauction.domain.trade.entity.TradeStatus;
import com.uniqueauction.domain.user.entity.User;

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

	@NotBlank(message = "입찰가격")
	private Long price;

	@NotBlank(message = "배송주소")
	private String shippingAddress;

	@NotBlank(message = "입찰마감기한")
	private String bidDueDate;

	@Builder
	public TradeRequest(Long userId, Long productId, String productSize, String bidPrice, String shippingAddress,
		String bidDueDate) {
		this.userId = userId;
		this.productId = productId;
		this.productSize = productSize;
		this.price = price;
		this.shippingAddress = shippingAddress;
		this.bidDueDate = bidDueDate;
	}

	public Trade convertForBuyer(User user, Product product, String address) {
		return Trade.builder()
			.price(this.price)
			.productSize(this.productSize)
			.tradeStatus(TradeStatus.BID_PROGRESS)
			.product(product)
			.publisher(user)
			.buyer(user)
			.shippingAddress(address)
			.build();
	}

	public Trade convertForSeller(User user, Product product, String address) {
		return Trade.builder()
			.price(this.price)
			.productSize(this.productSize)
			.tradeStatus(TradeStatus.BID_PROGRESS)
			.product(product)
			.publisher(user)
			.seller(user)
			.shippingAddress(address)
			.build();
	}
}
