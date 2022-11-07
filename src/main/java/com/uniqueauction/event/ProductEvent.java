package com.uniqueauction.event;

import com.uniqueauction.domain.product.entity.Product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class ProductEvent {

	private Long productId;

	@Setter
	private Product product;

	public ProductEvent(Long userId) {
		this.productId = userId;
	}

	public static ProductEvent of(Long productId) {
		return new ProductEvent(productId);
	}

}
