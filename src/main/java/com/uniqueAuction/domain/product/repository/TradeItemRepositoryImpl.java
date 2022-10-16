package com.uniqueauction.domain.product.repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.uniqueauction.domain.product.entity.TradeItem;

@Repository
public class TradeItemRepositoryImpl implements TradeItemRepository {

	private static final ConcurrentHashMap<Long, TradeItem> store = new ConcurrentHashMap<>();
	private static final AtomicLong sequence = new AtomicLong();

	@Override
	public void save(TradeItem tradeItem) {
		Long id = sequence.addAndGet(1);
		store.put(id, tradeItem);
	}

	@Override
	public void update(TradeItem tradeItem) {
		store.put(tradeItem.getId(), tradeItem);
	}
}
