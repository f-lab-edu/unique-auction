package com.uniqueAuction.domain.trade.service;

import static com.uniqueAuction.exception.ErrorCode.*;

import org.springframework.stereotype.Service;

import com.uniqueAuction.domain.trade.entity.Sale;
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

	public void saveSale(Sale sale) {
		/* 판매 등록 */
		sale.setTradeStatus(TradeStatus.BID_PROGRESS);
		Long saleId = saleRepository.save(sale);
		Long purchaseId = getPurchaseId(sale);

		/* 거래 등록 - 판매 희망가에 대한 구매 요청이 있는 경우 */
		if (purchaseId > 0) {
			Trade trade = Trade.builder().purhcaseId(purchaseId)
				.saleId(saleId)
				.status(TradeStatus.BID_COMPLETE)
				.build();
			tradeRepository.save(trade);
		}
	}

	private Long getPurchaseId(Sale sale) {
		return purchaseRepository.getPurchaseId(sale.getModelNumber(), sale.getProductSize());
	}
}
