package com.uniqueauction.domain.trade.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.uniqueauction.domain.product.entity.Product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sale_id")
	private Long id;
	private Long userId;
	private String productSize;
	private String bidPrice;
	private String returnAddress;
	private TradeStatus tradeStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	@Setter
	private Product product;

	@Builder
	public Sale(Long id, Long userId, String productSize, String bidPrice, String returnAddress,
		TradeStatus tradeStatus) {
		this.id = id;
		this.userId = userId;
		this.productSize = productSize;
		this.bidPrice = bidPrice;
		this.returnAddress = returnAddress;
		this.tradeStatus = tradeStatus;
	}

	public void changeTradeStatus(TradeStatus tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

}
