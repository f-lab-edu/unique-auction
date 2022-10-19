package com.uniqueauction.domain.product.service;

import static com.uniqueauction.exception.ErrorCode.*;

import org.springframework.stereotype.Service;

import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.product.repository.ProductRepository;
import com.uniqueauction.exception.advice.CommonNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {

	private final ProductRepository productRepository;

	public void update(Long id) {
		Product product = productRepository.findById(id)
			.orElseThrow(() -> new CommonNotFoundException(NOT_FOUND_PRODUCT));
		productRepository.save(product);
	}
}
