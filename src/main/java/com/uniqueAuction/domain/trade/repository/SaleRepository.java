package com.uniqueAuction.domain.trade.repository;

import com.uniqueAuction.domain.trade.entity.Sale;

public interface SaleRepository {
	Long save(Sale sale);

	Long getSaleId(String modelNumber, String size);
}
