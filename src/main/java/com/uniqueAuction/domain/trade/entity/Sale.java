package com.uniqueAuction.domain.trade.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class Sale {

	private Long id;
	private Long userId;
	private String productId;
	private String productSize;
	private String bidPrice;
	private String returnAddress;

	@Setter
	private TradeStatus tradeStatus;
}
