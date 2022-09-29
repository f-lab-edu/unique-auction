package com.uniqueAuction.domain.product.repository;

import com.uniqueAuction.domain.product.entity.Product;

import java.util.Optional;

public interface ProductRepository {
    void saveProduct(Product saveProduct);


    Optional<Product> productFindById(Long id);

    Product update(Long id, Product updateProduct);

    void delete(Long id);
}
