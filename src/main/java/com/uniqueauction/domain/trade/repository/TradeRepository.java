package com.uniqueauction.domain.trade.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uniqueauction.domain.trade.entity.Trade;
import com.uniqueauction.domain.trade.entity.TradeStatus;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {
	Optional<Trade> findByPublisherIdAndProductIdAndProductSizeAndTradeStatus(Long publisherId, Long productId,
		String productSize,
		TradeStatus tradeStatus);

	Optional<Trade> findByProductIdAndProductSizeAndTradeStatusAndPriceLessThanEqual(Long productId,
		String productSize,
		TradeStatus tradeStatus,
		Long price);

	Optional<Trade> findByProductIdAndProductSizeAndTradeStatusAndPriceGreaterThanEqual(Long productId,
		String productSize,
		TradeStatus tradeStatus,
		Long price);
}
