package com.uniqueauction.domain.product.service;

import static com.uniqueauction.exception.ErrorCode.*;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.product.repository.ProductRepository;
import com.uniqueauction.exception.advice.CommonNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {

	private final ProductRepository productRepository;

	public Product update(Product product) {
		productRepository.findById(product.getId())
			.orElseThrow(() -> new CommonNotFoundException(NOT_FOUND_PRODUCT));
		Long pId = productRepository.save(product).getId();
		return productRepository.findById(pId).get();
	}

	public List<Product> findByNameOrModelNumber(String searProduct) {
		return productRepository.findByNameOrModelNumber(searProduct, searProduct);
	}

	public Long save(Product product) {
		return productRepository.save(product).getId();
	}

	public void delete(Long id) {
		Optional<Product> product = productRepository.findById(id);
		productRepository.delete(product.get());
	}

	public Product findByModelNumber(String modelNumber) {
		return productRepository.findByModelNumber(modelNumber);
	}

	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}
}
