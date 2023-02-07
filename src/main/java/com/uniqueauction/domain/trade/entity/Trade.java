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
import com.uniqueauction.domain.user.entity.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
public class Trade extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "publisher_id")
	private User publisher;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "buyer_id")
	private User buyer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seller_id")
	private User seller;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

	@Enumerated(EnumType.STRING)
	private TradeStatus tradeStatus;

	private String productSize;

	private Long price;

	private String shippingAddress;

	@Builder
	public Trade(Long id, User publisher, User buyer,
		User seller, Product product, TradeStatus tradeStatus,
		String productSize, Long price, String shippingAddress) {
		this.id = id;
		this.publisher = publisher;
		this.buyer = buyer;
		this.seller = seller;
		this.product = product;
		this.tradeStatus = tradeStatus;
		this.productSize = productSize;
		this.price = price;
		this.shippingAddress = shippingAddress;
	}

	public void updateTradeStatus(TradeStatus tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public void makePurchase(User buyer, String shippingAddress) {
		this.shippingAddress = shippingAddress;
		this.buyer = buyer;
		this.tradeStatus = TradeStatus.BID_COMPLETE;
	}

	public void makeSale(User seller, String shippingAddress) {
		this.shippingAddress = shippingAddress;
		this.seller = seller;
		this.tradeStatus = TradeStatus.BID_COMPLETE;
	}

	public void createPurchase(User buyer, String shippingAdress) {
		this.buyer = buyer;
		this.shippingAddress = shippingAdress;
		this.tradeStatus = TradeStatus.BID_COMPLETE;
	}

	public void createSale(User seller, String shippingAdress) {
		this.seller = seller;
		this.shippingAddress = shippingAdress;
		this.tradeStatus = TradeStatus.BID_COMPLETE;
	}
}
