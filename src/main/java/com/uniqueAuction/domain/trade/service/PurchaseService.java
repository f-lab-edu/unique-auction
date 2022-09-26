package com.uniqueAuction.domain.trade.service;

import com.uniqueAuction.domain.trade.entity.Purchase;
import com.uniqueAuction.domain.trade.entity.TradeStatus;
import com.uniqueAuction.domain.trade.repository.PurchaseRepository;
import com.uniqueAuction.web.trade.request.PurchaseBidRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.uniqueAuction.domain.trade.entity.TradeType;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    public void savePurchaseBid(PurchaseBidRequest purchaseBidRequest) {
        Purchase purchase = purchaseBidRequest.toEntity();
        purchase.setTradeType(TradeType.PURCHASE_BID);
        purchase.setTradeStatus(TradeStatus.BID_PROGRESS);
        purchaseRepository.save(purchase);
    }
}
