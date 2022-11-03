package com.uniqueauction.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.product.service.ProductService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductHandler {

	private final ProductService productService;

	@EventListener
	public void findUser(ProductEvent productEvent) {
		Product product = productService.findById(productEvent.getProductId());
		productEvent.setProduct(product);
	}

}
