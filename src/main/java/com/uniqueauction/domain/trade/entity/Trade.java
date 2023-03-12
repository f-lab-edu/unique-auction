package com.uniqueauction.domain.trade.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.uniqueauction.domain.base.BaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Trade extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long publisherId;
	private Long buyerId;
	private Long sellerId;
	private Long productId;
	@Enumerated(EnumType.STRING)
	private TradeStatus tradeStatus;
	private String productSize;
	private Long price;
	private String shippingAddress;

	@Builder
	public Trade(Long id, Long publisherId, Long buyerId,
		Long sellerId, Long productId, TradeStatus tradeStatus,
		String productSize, Long price, String shippingAddress) {
		this.id = id;
		this.publisherId = publisherId;
		this.buyerId = buyerId;
		this.sellerId = sellerId;
		this.productId = productId;
		this.tradeStatus = tradeStatus;
		this.productSize = productSize;
		this.price = price;
		this.shippingAddress = shippingAddress;
	}

	public void tradeComplete(Long price, Long userId, TradeStatus tradeStatus) {
		this.price = price;
		this.buyerId = tradeStatus == TradeStatus.PURCHASE_PROGRESS ? userId : this.buyerId;
		this.sellerId = tradeStatus == TradeStatus.SALE_PROGRESS ? userId : this.sellerId;
		this.tradeStatus = TradeStatus.BID_COMPLETE;
	}

	public void updateTrade(Long price, String shippingAddress) {
		this.price = price;
		this.shippingAddress = shippingAddress;
	}

}
