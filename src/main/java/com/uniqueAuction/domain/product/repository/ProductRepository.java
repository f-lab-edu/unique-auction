package com.uniqueAuction.domain.product.repository;

import com.uniqueAuction.domain.product.entity.Product;

import java.util.List;

public interface ProductRepository {
    void saveProduct(Product saveProduct);


    Product productFindById(Long id);

    Product update(Long id, Product updateProduct);

    void delete(Long id);

    List<Product> findByAll();

    void clear();
}
