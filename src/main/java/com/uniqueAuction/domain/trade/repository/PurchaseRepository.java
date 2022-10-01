package com.uniqueAuction.domain.trade.repository;

import com.uniqueAuction.domain.trade.entity.Purchase;

public interface PurchaseRepository {
    void save(Purchase purchase);
}
