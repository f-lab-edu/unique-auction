package com.uniqueAuction.domain.trade.repository;

import com.uniqueAuction.domain.trade.entity.Trade;

public interface TradeRepository {
	Long save(Trade trade);
}
