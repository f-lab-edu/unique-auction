package com.uniqueauction.domain.product.service;

import org.springframework.stereotype.Service;

import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {

	private final ProductRepository productRepository;

	public void saveProduct(Product product) {
		productRepository.saveProduct(product);

	}

	public Product productFindById(Long id) {
		return productRepository.productFindById(id);
	}

	public Product updateProduct(Long id, Product updateProduct) {

		//상품 유무 체크
		productFindById(id);

		return productRepository.update(id, updateProduct);
	}

	public void deleteProduct(Long id) {
		productRepository.delete(id);
	}
}
