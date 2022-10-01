package com.uniqueAuction.domain.trade.repository;

import com.uniqueAuction.domain.trade.entity.Sale;

public interface SaleRepository {
	Long save(Sale sale);

	Long isExists(String modelNumber, String size);
}
