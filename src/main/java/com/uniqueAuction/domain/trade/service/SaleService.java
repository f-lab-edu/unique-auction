package com.uniqueAuction.domain.trade.service;

import static com.uniqueAuction.exception.ErrorCode.*;

import org.springframework.stereotype.Service;

import com.uniqueAuction.domain.trade.entity.Sale;
import com.uniqueAuction.domain.trade.entity.SaleType;
import com.uniqueAuction.domain.trade.entity.Trade;
import com.uniqueAuction.domain.trade.entity.TradeStatus;
import com.uniqueAuction.domain.trade.repository.PurchaseRepository;
import com.uniqueAuction.domain.trade.repository.SaleRepository;
import com.uniqueAuction.domain.trade.repository.TradeRepository;
import com.uniqueAuction.exception.advice.CommonNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleService {
	private final PurchaseRepository purchaseRepository;
	private final SaleRepository saleRepository;
	private final TradeRepository tradeRepository;

	public void saveSaleBid(Sale sale) {
		sale.setSaleType(SaleType.SALE_BID);
		sale.setTradeStatus(TradeStatus.BID_PROGRESS);
		saleRepository.save(sale);
	}

	public void saveSaleNow(Sale sale) {
		/* Sale 생성, 즉시 판매, 입찰이 아닌 거래 완료 상태로 생성 */
		sale.setSaleType(SaleType.SALE_NOW);
		sale.setTradeStatus(TradeStatus.TRADE_COMPLETE);

		/* purchaseId, saleId 세팅 */
		Long saleId = saleRepository.save(sale);
		Long purchaseId = getPurchaseId(sale);

		/* 즉시 판매 거래 생성, 거래 상태는 입찰 완료 (낙찰) */
		if (purchaseId > 0) {
			Trade trade = Trade.builder().purhcaseId(purchaseId)
				.saleId(saleId)
				.status(TradeStatus.BID_COMPLETE)
				.build();
			tradeRepository.save(trade);
		} else {
			throw new CommonNotFoundException(NOT_FOUND_PURCHASE_BID);
		}
	}

	private Long getPurchaseId(Sale sale) {
		return purchaseRepository.getPurchaseId(sale.getModelNumber(), sale.getProductSize());
	}
}
