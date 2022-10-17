package com.uniqueauction.domain.trade.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class Trade {
	private Long id;
	private Long purhcaseId;
	private Long saleId;
	@Setter
	private TradeStatus status;
}
