package com.uniqueauction.domain.product.repository;

import com.uniqueauction.domain.product.entity.Product;

public interface ProductRepository {
	Long save(Product saveProduct);

	Product findById(Long id);

	Product update(Product updateProduct);

	void delete(Long id);
}
