package com.uniqueauction.domain.product.repository;

import java.util.List;

import com.uniqueauction.domain.product.entity.Product;

public interface ProductRepository {
	long save(Product saveProduct);

	Product findById(Long id);

	Product update(Product updateProduct);

	void delete(Long id);

	void deleteAll();

	List<Product> findByAll();
}
