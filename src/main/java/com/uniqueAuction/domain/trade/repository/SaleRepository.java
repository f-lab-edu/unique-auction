package com.uniqueAuction.domain.trade.repository;

import com.uniqueAuction.domain.trade.entity.Purchase;
import com.uniqueAuction.domain.trade.entity.Sale;

public interface SaleRepository {
    void save(Sale sale);
}
