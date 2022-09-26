package com.uniqueAuction.domain.trade.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class Purchase {

	//private Long id;
	private Long userId;
	private Long productId;
	private String size;
	private String bidPrice;
	private String shippingAddress;

	@Setter
	private TradeType tradeType;
	@Setter
	private TradeStatus tradeStatus;
}
