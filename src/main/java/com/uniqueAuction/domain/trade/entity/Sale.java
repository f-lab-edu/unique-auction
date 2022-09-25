package com.uniqueAuction.domain.trade.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Sale {

	//private Long id;
	private Long userId;
	private Long productId;
	private String size;
	private String price;
	private String returnAddress;
	private SaleType saleType;
	private SaleStatus saleStatus;

	public enum SaleType {
		SALE_NOW, /* 즉시 구매 */
		SALE_BID /* 구매 입찰 */
	}

	public enum SaleStatus {
		BID_PROGRESS, /* 입찰 진행중 */
		BID_SUCCESS /* 입찰 체결 */
	}

	public Sale(Long userId, Long productId, String size, String price, SaleType saleType, SaleStatus saleStatus) {
		this.userId = userId;
		this.productId = productId;
		this.size = size;
		this.price = price;
		this.saleType = saleType;
		this.saleStatus = saleStatus;
	}
}
