package com.uniqueAuction.domain.product.repository;

import com.uniqueAuction.domain.product.entity.TradeItem;

public interface TradeItemRepository {
	void save(TradeItem tradeItem);

	void update(TradeItem tradeItem);

}
