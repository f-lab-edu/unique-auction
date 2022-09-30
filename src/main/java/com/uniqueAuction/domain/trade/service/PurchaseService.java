package com.uniqueAuction.domain.trade.service;

import com.uniqueAuction.domain.trade.entity.Purchase;
import com.uniqueAuction.domain.trade.entity.PurchaseType;
import com.uniqueAuction.domain.trade.entity.TradeStatus;
import com.uniqueAuction.domain.trade.repository.PurchaseRepository;
import com.uniqueAuction.web.trade.request.PurchaseBidRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    public void savePurchaseBid(PurchaseBidRequest purchaseBidRequest) {
        Purchase purchase = purchaseBidRequest.toEntity();
        purchase.setPurchaseType(PurchaseType.PURCHASE_BID);
        purchase.setTradeStatus(TradeStatus.BID_PROGRESS);
        purchaseRepository.save(purchase);
    }
}
