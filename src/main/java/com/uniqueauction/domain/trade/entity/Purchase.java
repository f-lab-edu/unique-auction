package com.uniqueauction.domain.trade.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class Purchase {

	private Long id;
	private Long userId;
	private String productId;
	private String productSize;
	private String bidPrice;
	private String shippingAddress;

	@Setter
	private TradeStatus tradeStatus;
}