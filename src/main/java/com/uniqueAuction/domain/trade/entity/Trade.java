package com.uniqueAuction.domain.trade.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Trade {

	private Long id;
	private Long productId;
	private Long purhcaseId;
	private Long saleId;
	private TradeStatus status;

	public enum TradeStatus {
		BID_COMPLETE, /* 입찰 체결 */
		TRADE_COMPLETE /* 거래 완료*/
	}

	public Trade(Long id, Long productId, Long purhcaseId, Long saleId,
		TradeStatus status) {
		this.id = id;
		this.productId = productId;
		this.purhcaseId = purhcaseId;
		this.saleId = saleId;
		this.status = status;
	}
}
