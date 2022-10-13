package com.uniqueAuction.domain.trade.repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.uniqueAuction.domain.trade.entity.Purchase;

@Repository
public class PurchaseRepositoryImpl implements PurchaseRepository {
	private static final ConcurrentHashMap<Long, Purchase> purchases = new ConcurrentHashMap<>();
	private static final AtomicLong sequence = new AtomicLong();

	@Override
	public void save(Purchase purchase) {
		purchases.put(sequence.addAndGet(1), purchase);
	}
}
