package com.uniqueauction.domain.product.repository;

import com.uniqueauction.domain.product.entity.TradeItem;

public interface TradeItemRepository {
	void save(TradeItem tradeItem);

	void update(TradeItem tradeItem);

}
