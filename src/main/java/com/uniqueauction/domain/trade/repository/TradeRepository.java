package com.uniqueauction.domain.trade.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uniqueauction.domain.trade.entity.Trade;
import com.uniqueauction.domain.trade.entity.TradeStatus;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {
	Optional<Trade> findByPublisherIdAndProductIdAndProductSizeAndTradeStatus(Long publisherId, Long productId,
		String productSize,
		TradeStatus tradeStatus);

	@Query(value = "select t "
		+ "from Trade t "
		+ "where t.productId = :#{#trade.productId} "
		+ "and t.productSize = :#{#trade.productSize} "
		+ "and t.tradeStatus = :tradeStatus "
		+ "and t.price >= :#{#trade.price} ")
	List<Trade> findPurchaseBid(@Param(value = "trade") Trade trade, @Param("tradeStatus") TradeStatus tradeStatus);

	@Query(value = "select t "
		+ "from Trade t "
		+ "where t.productId = :#{#trade.productId} "
		+ "and t.productSize = :#{#trade.productSize} "
		+ "and t.tradeStatus = :tradeStatus "
		+ "and t.price <= :#{#trade.price} ")
	List<Trade> findSaleBid(@Param(value = "trade") Trade trade, @Param("tradeStatus") TradeStatus tradeStatus);
}
