package com.uniqueauction.domain.product.service;

import static com.uniqueauction.exception.ErrorCode.*;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.product.repository.ProductRepository;
import com.uniqueauction.exception.advice.CommonNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Product update(Product product) {
        //select for update - pessmistic lock.
        //
        //
        //select * from product where id=? for update
        Product findProduct = productRepository.findById(product.getId())
                .orElseThrow(() -> new CommonNotFoundException(NOT_FOUND_PRODUCT));

        findProduct.updateProduct(product);

        //db update //mybatis
        productRepository.save(findProduct);

        return findProduct;
    }

    public Product incrementPrice(Product product, int price) {
        int updatedPrice = Integer.parseInt(product.getReleasePrice()) + price;
        product.setReleasePrice("" + updatedPrice);
        update(product);
        return product;
    }

    @Transactional(readOnly = true)
    public List<Product> findByNameOrModelNumber(String searProduct) {
        return productRepository.findByNameOrModelNumber(searProduct, searProduct);
    }

    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public void delete(Long id) {
        Optional<Product> product = productRepository.findById(id);
        product.ifPresent(
                productRepository::delete
        );
    }

    @Transactional(readOnly = true)
    public Product findByModelNumber(String modelNumber) {
        return productRepository.findByModelNumber(modelNumber);
    }

    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()
                        -> new CommonNotFoundException(NOT_FOUND_PRODUCT)
                );
    }
}
