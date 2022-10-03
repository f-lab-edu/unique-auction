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
	public Long save(Sale sale) {
		sales.put(sequence.addAndGet(1), sale);
		return sequence.addAndGet(1);
	}

	@Override
	public Long getSaleId(String modelNumber, String size) {
		return sales.entrySet()
			.stream()
			.filter(e -> e.getValue().getModelNumber().equals(modelNumber) && e.getValue().getProductSize().equals(size)).findFirst()
			.map(e -> e.getValue().getId())
			.orElse(0L);
	}
}
