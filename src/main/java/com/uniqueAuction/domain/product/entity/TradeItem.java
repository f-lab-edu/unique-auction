package com.uniqueAuction.domain.product.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TradeItem {

	private Long id;
	private Long productId;
	private String size;

}
