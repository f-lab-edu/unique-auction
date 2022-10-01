package com.uniqueAuction.domain.trade.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class Trade {

	private Long id;
	private Long productId;
	private Long purhcaseId;
	private Long saleId;
	@Setter
	private TradeStatus status;
}
