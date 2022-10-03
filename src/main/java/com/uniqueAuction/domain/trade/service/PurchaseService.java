package com.uniqueAuction.domain.trade.service;

import static com.uniqueAuction.exception.ErrorCode.*;

import org.springframework.stereotype.Service;

import com.uniqueAuction.domain.trade.entity.Purchase;
import com.uniqueAuction.domain.trade.entity.PurchaseType;
import com.uniqueAuction.domain.trade.entity.Trade;
import com.uniqueAuction.domain.trade.entity.TradeStatus;
import com.uniqueAuction.domain.trade.repository.PurchaseRepository;
import com.uniqueAuction.domain.trade.repository.SaleRepository;
import com.uniqueAuction.domain.trade.repository.TradeRepository;
import com.uniqueAuction.exception.advice.CommonNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseService {
	private final PurchaseRepository purchaseRepository;
	private final SaleRepository saleRepository;
	private final TradeRepository tradeRepository;

	public void savePurchaseBid(Purchase purchase) {
		purchase.setPurchaseType(PurchaseType.PURCHASE_BID);
		purchase.setTradeStatus(TradeStatus.BID_PROGRESS);
		purchaseRepository.save(purchase);
	}

	public void savePurchaseNow(Purchase purchase) {
		/* Purchase 생성, 즉시 구매, 입찰이 아닌 거래 완료 상태로 생성 */
		purchase.setPurchaseType(PurchaseType.PURCHASE_NOW);
		purchase.setTradeStatus(TradeStatus.TRADE_COMPLETE);

		/* purchaseId, saleId 세팅 */
		Long purchaseId = purchaseRepository.save(purchase);
		Long saleId = getSaleId(purchase);

		/* 즉시 구매 거래 생성, 거래 상태는 입찰 완료 (낙찰) */
		if (saleId > 0) {
			Trade trade = Trade.builder().purhcaseId(purchaseId)
				.saleId(saleId)
				.status(TradeStatus.BID_COMPLETE)
				.build();
			tradeRepository.save(trade);
		} else {
			throw new CommonNotFoundException(NOT_FOUND_SALE_BID);
		}
	}

	private Long getSaleId(Purchase purchase) {
		return saleRepository.getSaleId(purchase.getModelNumber(), purchase.getProductSize());
	}
}
