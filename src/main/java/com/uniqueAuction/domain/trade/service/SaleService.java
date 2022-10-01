package com.uniqueAuction.domain.trade.service;

import org.springframework.stereotype.Service;

import com.uniqueAuction.domain.trade.entity.Sale;
import com.uniqueAuction.domain.trade.entity.SaleType;
import com.uniqueAuction.domain.trade.entity.TradeStatus;
import com.uniqueAuction.domain.trade.repository.SaleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleService {
	private final SaleRepository saleRepository;

	public void saveSaleBid(Sale sale) {
		sale.setSaleType(SaleType.SALE_BID);
		sale.setTradeStatus(TradeStatus.BID_PROGRESS);
		saleRepository.save(sale);
	}
}
