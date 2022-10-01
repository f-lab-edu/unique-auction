package com.uniqueAuction.domain.product.service;


import com.uniqueAuction.domain.product.entity.Product;
import com.uniqueAuction.domain.product.repository.ProductRepository;
import com.uniqueAuction.exception.advice.product.ProductException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.uniqueAuction.exception.ErrorCode.NOT_FOUND_PRODUCT;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public void saveProduct(Product product) {
        productRepository.saveProduct(product);

    }

    public Product productFindById(Long id) {
        return productRepository.productFindById(id)
                .orElseThrow(() -> new ProductException(NOT_FOUND_PRODUCT));
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
