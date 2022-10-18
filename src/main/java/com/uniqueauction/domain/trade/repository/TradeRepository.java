package com.uniqueauction.domain.trade.repository;

import com.uniqueauction.domain.trade.entity.Trade;

public interface TradeRepository {
	Long save(Trade trade);
}
