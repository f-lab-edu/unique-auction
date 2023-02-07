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

	@Getter
	@NoArgsConstructor
	public static class SaveBidRequest {

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
		public SaveBidRequest(Long userId, Long productId, String productSize, Long price, String shippingAddress) {
			this.userId = userId;
			this.productId = productId;
			this.productSize = productSize;
			this.price = price;
			this.shippingAddress = shippingAddress;
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

	@Getter
	@NoArgsConstructor
	public static class SaveTradeRequest {

		private Long tradeId;
		private Long userId;
		private Long productId;
		private String shippingAdress;

		@Builder
		public SaveTradeRequest(Long tradeId, Long userId, Long productId, String shippingAdress) {
			this.tradeId = tradeId;
			this.userId = userId;
			this.productId = productId;
			this.shippingAdress = shippingAdress;
		}
	}
}
