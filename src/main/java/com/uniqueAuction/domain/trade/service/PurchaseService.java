package com.uniqueAuction.domain.trade.service;

import org.springframework.stereotype.Service;

import com.uniqueAuction.domain.trade.entity.Purchase;
import com.uniqueAuction.domain.trade.entity.Trade;
import com.uniqueAuction.domain.trade.entity.TradeStatus;
import com.uniqueAuction.domain.trade.repository.PurchaseRepository;
import com.uniqueAuction.domain.trade.repository.SaleRepository;
import com.uniqueAuction.domain.trade.repository.TradeRepository;

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
		Long purchaseId = purchaseRepository.save(purchase);
		Long saleId = saleRepository.findByProductIdAndProductSize(purchase.getProductId(), purchase.getProductSize());

		/* 거래 등록 - 구매 희망가에 대한 판매 요청이 있는 경우 */
		if (saleId > 0) {
			Trade trade = Trade.builder().purhcaseId(purchaseId)
				.saleId(saleId)
				.status(TradeStatus.BID_COMPLETE)
				.build();
			tradeRepository.save(trade);
		}
	}
}
