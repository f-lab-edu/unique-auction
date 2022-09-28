package com.uniqueAuction.domain.product.repository;

import com.uniqueAuction.domain.product.entity.Product;
import com.uniqueAuction.web.product.request.ProductSaveRequest;

import java.util.Optional;

public interface ProductRepository {
    void saveProduct(ProductSaveRequest ProductSaveRequest);


    Optional<Product> productFindById(Long id);

    Product update(Long id, Product updateProduct);

    void delete(Long id);
}
