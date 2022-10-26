package com.uniqueauction.domain.trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uniqueauction.domain.trade.entity.Trade;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {
}
