package com.uniqueauction.domain.trade.entity;

import static com.uniqueauction.domain.trade.entity.TradeStatus.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.uniqueauction.domain.base.BaseEntity;
import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.web.trade.request.PurchaseRequest;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Purchase extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "purchase_id")
	private Long id;
	private Long userId;
	private String productSize;
	private String bidPrice;
	private String shippingAddress;
	private TradeStatus tradeStatus;

	private String bidDueDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

	@Builder
	public Purchase(Long id, Long userId, String productSize, String bidPrice, String shippingAddress, Product product,
		TradeStatus tradeStatus, String bidDueDate) {
		this.id = id;
		this.userId = userId;
		this.productSize = productSize;
		this.bidPrice = bidPrice;
		this.shippingAddress = shippingAddress;
		this.product = product;
		this.tradeStatus = tradeStatus;
		this.bidDueDate = bidDueDate;
	}

	public Purchase updatePurchase(Purchase purchase) {
		this.userId = purchase.getUserId();
		this.productSize = purchase.getProductSize();
		this.bidPrice = purchase.getBidPrice();
		this.shippingAddress = purchase.getShippingAddress();
		this.product = purchase.getProduct();
		this.tradeStatus = purchase.getTradeStatus();
		this.bidDueDate = purchase.getBidDueDate();
		return this;
	}

	public void updateTradeStatus(TradeStatus tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void savePurchase(Purchase purchase, PurchaseRequest purchaseRequest) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate today = LocalDate.now();
		LocalDate purchaseBidDueDate = LocalDate.parse(purchase.getBidDueDate(), formatter);

		/* due date가 지나지 않았다면 update */
		if (!purchaseBidDueDate.isBefore(today) && purchase.getTradeStatus() == BID_PROGRESS) {
			/* update */
			purchase.updatePurchase(purchaseRequest.toEntity());
		}
		purchase.updateTradeStatus(BID_PROGRESS);
		purchase.setProduct(product);
	}

}
