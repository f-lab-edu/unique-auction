package com.uniqueAuction.domain.trade.repository;

import com.uniqueAuction.domain.trade.entity.Purchase;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PurchaseRepositoryImpl implements PurchaseRepository {
    private static final ConcurrentHashMap<Long, Purchase> purchases = new ConcurrentHashMap<>();
    private static final AtomicLong sequence = new AtomicLong();

    @Override
    public void save(Purchase purchase) {
        purchases.put(sequence.addAndGet(1) , purchase);
    }
}
