package com.uniqueauction.domain.product.repository;

import com.uniqueauction.domain.product.entity.Product;

public interface ProductRepository {
	void saveProduct(Product saveProduct);

	Product productFindById(Long id);

	Product update(Long id, Product updateProduct);

	void delete(Long id);
}
