package com.uniqueauction.domain.trade.repository;

import com.uniqueauction.domain.trade.entity.Sale;

public interface SaleRepository {
	Long save(Sale sale);

	Long findByProductIdAndProductSize(String modelNumber, String size);
}
