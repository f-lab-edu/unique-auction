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
public class PurchaseService {
	private final PurchaseRepository purchaseRepository;
	private final SaleRepository saleRepository;
	private final TradeRepository tradeRepository;

	public void savePurchase(Purchase purchase) {
		/* 구매 등록 */
		purchase.setTradeStatus(TradeStatus.BID_PROGRESS);
		Long purchaseId = purchaseRepository.save(purchase).getId();
		Sale sale = saleRepository.findByProductAndProductSize(purchase.getProduct(), purchase.getProductSize());

		Trade trade = Trade.builder().purchase(purchase)
			.sale(sale)
			.status(TradeStatus.BID_COMPLETE)
			.build();
		tradeRepository.save(trade);
	}
}
