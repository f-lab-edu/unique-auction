package com.uniqueAuction.domain.trade.service;

import com.uniqueAuction.domain.trade.entity.Sale;
import com.uniqueAuction.domain.trade.entity.SaleType;
import com.uniqueAuction.domain.trade.entity.TradeStatus;
import com.uniqueAuction.domain.trade.repository.SaleRepository;
import com.uniqueAuction.web.trade.request.SaleBidRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;

    public void saveSaleBid(SaleBidRequest saleBidRequest) {
        Sale sale = saleBidRequest.toEntity();
        sale.setSaleType(SaleType.SALE_BID);
        sale.setTradeStatus(TradeStatus.BID_PROGRESS);
        saleRepository.save(sale);
    }
}
