package com.uniqueauction.domain.product.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TradeItem {

	private Long id;
	private Long productId;
	private String size;

}
