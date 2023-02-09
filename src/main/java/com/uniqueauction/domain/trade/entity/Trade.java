package com.uniqueauction.domain.trade.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.uniqueauction.domain.base.BaseEntity;
import com.uniqueauction.domain.product.entity.Product;

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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;
	@Enumerated(EnumType.STRING)
	private TradeStatus tradeStatus;
	private String productSize;
	private Long price;
	private String shippingAddress;

	@Builder
	public Trade(Long id, Long publisherId, Long buyerId,
		Long sellerId, Product product, TradeStatus tradeStatus,
		String productSize, Long price, String shippingAddress) {
		this.id = id;
		this.publisherId = publisherId;
		this.buyerId = buyerId;
		this.sellerId = sellerId;
		this.product = product;
		this.tradeStatus = tradeStatus;
		this.productSize = productSize;
		this.price = price;
		this.shippingAddress = shippingAddress;
	}
	
	public void updateTradeByBuyer(Long price, Long buyerId) {
		this.price = price;
		this.buyerId = buyerId;
		this.tradeStatus = TradeStatus.BID_COMPLETE;
	}

	public void updateTradeBySeller(Long price, Long sellerId) {
		this.price = price;
		this.sellerId = sellerId;
		this.tradeStatus = TradeStatus.BID_COMPLETE;
	}

	public void updateTrade(Long price, String shippingAddress) {
		this.price = price;
		this.shippingAddress = shippingAddress;
	}

}
