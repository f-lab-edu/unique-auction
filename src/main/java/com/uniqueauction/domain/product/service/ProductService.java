package com.uniqueauction.domain.product.service;

import static com.uniqueauction.exception.ErrorCode.*;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.product.repository.ProductRepository;
import com.uniqueauction.exception.advice.CommonNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {

	private final ProductRepository productRepository;

	public void update(Product product) {
		productRepository.findById(product.getId())
			.orElseThrow(() -> new CommonNotFoundException(NOT_FOUND_PRODUCT));
		productRepository.save(product);
	}

	public List<Product> findByNameOrModelNumber(String name, String modelNumber) {
		return productRepository.findByNameOrModelNumber(name, modelNumber);
	}
}
