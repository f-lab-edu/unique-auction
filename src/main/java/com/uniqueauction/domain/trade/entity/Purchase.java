package com.uniqueauction.domain.trade.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
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
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "purchase_id")
	private Long id;
	private Long userId;
	private String productSize;
	private String bidPrice;
	private String shippingAddress;
	private TradeStatus tradeStatus;

	@Embedded
	BaseEntity baseEntity;

	@Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

	@Builder
	public Purchase(Long id, Long userId, String productSize, String bidPrice, String shippingAddress, Product product,
		TradeStatus tradeStatus) {
		this.id = id;
		this.userId = userId;
		this.productSize = productSize;
		this.bidPrice = bidPrice;
		this.shippingAddress = shippingAddress;
		this.product = product;
		this.tradeStatus = tradeStatus;
	}

	public void changeTradeStatus(TradeStatus tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
}
