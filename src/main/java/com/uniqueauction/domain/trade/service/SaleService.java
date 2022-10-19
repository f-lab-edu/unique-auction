package com.uniqueauction.domain.trade.service;

import org.springframework.stereotype.Service;

import com.uniqueauction.domain.trade.entity.Purchase;
import com.uniqueauction.domain.trade.entity.Sale;
import com.uniqueauction.domain.trade.entity.Trade;
import com.uniqueauction.domain.trade.entity.TradeStatus;
import com.uniqueauction.domain.trade.repository.PurchaseRepository;
import com.uniqueauction.domain.trade.repository.SaleRepository;
import com.uniqueauction.domain.trade.repository.TradeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleService {
	private final PurchaseRepository purchaseRepository;
	private final SaleRepository saleRepository;
	private final TradeRepository tradeRepository;

	public void saveSale(Sale sale) {
		/* 판매 등록 */
		sale.setTradeStatus(TradeStatus.BID_PROGRESS);
		Long saleId = saleRepository.save(sale).getId();
		Purchase purchase = purchaseRepository.findByProductAndProductSize(sale.getProduct(), sale.getProductSize());

		Trade trade = Trade.builder().purchase(purchase)
			.sale(sale)
			.status(TradeStatus.BID_COMPLETE)
			.build();
		tradeRepository.save(trade);

	}
}
