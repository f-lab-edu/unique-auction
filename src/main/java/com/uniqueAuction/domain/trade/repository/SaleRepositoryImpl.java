package com.uniqueAuction.domain.trade.repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.uniqueAuction.domain.trade.entity.Sale;

@Repository
public class SaleRepositoryImpl implements SaleRepository {
	private static final ConcurrentHashMap<Long, Sale> sales = new ConcurrentHashMap<>();
	private static final AtomicLong sequence = new AtomicLong();

	@Override
	public void save(Sale sale) {
		sales.put(sequence.addAndGet(1), sale);
	}
}
