package com.uniqueauction.domain.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {

	private final ProductRepository productRepository;

	public long save(Product product) {
		return productRepository.save(product);
	}

	public Product findById(Long id) {
		return productRepository.findById(id);
	}

	public Product update(Product updateProduct) {

		return productRepository.update(updateProduct);
	}

	public void deleteProduct(Long id) {
		productRepository.delete(id);
	}

	public List<Product> findByAll() {
		return productRepository.findByAll();
	}

	public void deleteAll() {
		productRepository.deleteAll();
	}
}
