package com.uniqueauction.domain.trade.repository;

import com.uniqueauction.domain.trade.entity.Purchase;

public interface PurchaseRepository {
	Long save(Purchase purchase);

	Long findByProductIdAndProductSize(String modelNumber, String size);
}
