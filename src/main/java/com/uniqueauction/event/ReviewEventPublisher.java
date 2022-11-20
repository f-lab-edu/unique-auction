package com.uniqueauction.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.user.entity.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReviewEventPublisher {

	private final ApplicationEventPublisher eventPublisher;

	public User getUser(Long userId) {
		UserEvent uEvnet = UserEvent.of(userId);

		eventPublisher.publishEvent(uEvnet);

		return uEvnet.getUser();
	}

	public Product getProduct(Long productId) {

		ProductEvent pEvent = ProductEvent.of(productId);

		eventPublisher.publishEvent(pEvent);

		return pEvent.getProduct();
	}

}
