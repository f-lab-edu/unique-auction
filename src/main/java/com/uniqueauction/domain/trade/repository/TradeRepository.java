package com.uniqueauction.domain.trade.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.trade.entity.Trade;
import com.uniqueauction.domain.trade.entity.TradeStatus;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {
	Optional<Trade> findByPublisherIdAndProductAndProductSizeAndTradeStatus(Long publisherId, Product product,
		String productSize,
		TradeStatus tradeStatus);

	Optional<Trade> findByProductAndProductSizeAndTradeStatusAndPriceLessThanEqual(Product product,
		String productSize,
		TradeStatus tradeStatus,
		Long price);

	Optional<Trade> findByProductAndProductSizeAndTradeStatusAndPriceGreaterThanEqual(Product product,
		String productSize,
		TradeStatus tradeStatus,
		Long price);
}
