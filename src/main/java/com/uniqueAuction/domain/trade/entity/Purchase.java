package com.uniqueAuction.domain.trade.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Purchase {

	//private Long id;
	private Long userId;
	private Long productId;
	private String size;
	private String price;
	private String shippingAddress;
	private PurchaseType purchaseType;
	private PurchaseStatus purchaseStatus;

	public enum PurchaseType {
		PURCHASE_NOW, /* 즉시 구매 */
		PURCHASE_BID /* 구매 입찰 */
	}

	public enum PurchaseStatus {
		BID_PROGRESS, /* 입찰 진행중 */
		BID_SUCCESS /* 입찰 체결 */
	}

	public Purchase(Long userId, Long productId, String size, String price, PurchaseType purchaseType,
		PurchaseStatus purchaseStatus) {
		this.userId = userId;
		this.productId = productId;
		this.size = size;
		this.price = price;
		this.purchaseType = purchaseType;
		this.purchaseStatus = purchaseStatus;
	}
}
