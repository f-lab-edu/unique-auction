package com.uniqueAuction.domain.trade.repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import com.uniqueAuction.domain.trade.entity.Trade;

public class TradeRepositoryImpl implements TradeRepository {
	private static final ConcurrentHashMap<Long, Trade> trades = new ConcurrentHashMap<>();
	private static final AtomicLong sequence = new AtomicLong();

	@Override
	public Long save(Trade trade) {
		trades.put(sequence.addAndGet(1), trade);
		return sequence.addAndGet(1);
	}
}
