package com.uniqueAuction.domain.trade.repository;

import com.uniqueAuction.domain.trade.entity.Sale;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class SaleRepositoryImpl implements SaleRepository {
    private static final ConcurrentHashMap<Long, Sale> sales = new ConcurrentHashMap<>();
    private static final AtomicLong sequence = new AtomicLong();

    @Override
    public void save(Sale sale) {
        sales.put(sequence.addAndGet(1) , sale);
    }
}
