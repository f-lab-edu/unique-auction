package com.uniqueAuction.domain.product.repository;

import com.uniqueAuction.domain.product.entity.Product;
import com.uniqueAuction.web.product.request.ProductSaveRequest;
import com.uniqueAuction.web.product.request.ProductUpdateRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface ProductRepository {
    void saveProduct(ProductSaveRequest ProductSaveRequest);


    Optional<Product> productFindById(Long id);

    void update(Long id, Product updateProduct);

    void delete(Long id);
}
