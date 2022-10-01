package com.uniqueAuction.domain.trade.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class Sale {

	private Long id;
	private Long userId;
	private String modelNumber;
	private String size;
	private String bidPrice;
	private String returnAddress;

	@Setter
	private SaleType saleType;
	@Setter
	private TradeStatus tradeStatus;
}
