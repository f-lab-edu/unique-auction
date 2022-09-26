package com.uniqueAuction.domain.trade.service;

import com.uniqueAuction.domain.trade.entity.Purchase;
import com.uniqueAuction.domain.trade.entity.Sale;
import com.uniqueAuction.domain.trade.entity.TradeStatus;
import com.uniqueAuction.domain.trade.entity.TradeType;
import com.uniqueAuction.domain.trade.repository.PurchaseRepository;
import com.uniqueAuction.domain.trade.repository.SaleRepository;
import com.uniqueAuction.web.trade.request.PurchaseBidRequest;
import com.uniqueAuction.web.trade.request.SaleBidRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;

    public void saveSaleBid(SaleBidRequest saleBidRequest) {
        Sale sale = saleBidRequest.toEntity();
        sale.setTradeType(TradeType.SALE_BID);
        sale.setTradeStatus(TradeStatus.BID_PROGRESS);
        saleRepository.save(sale);
    }
}
