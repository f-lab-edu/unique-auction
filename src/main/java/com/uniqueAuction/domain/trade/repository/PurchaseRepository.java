package com.uniqueAuction.domain.trade.repository;

import com.uniqueAuction.domain.trade.entity.Purchase;

public interface PurchaseRepository {
	Long save(Purchase purchase);

	Long findByProductIdAndProductSize(String modelNumber, String size);
}
