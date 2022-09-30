package com.uniqueAuction.domain.product.service;


import com.uniqueAuction.domain.product.entity.Product;
import com.uniqueAuction.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public void saveProduct(Product product) {
        productRepository.saveProduct(product);

    }

    public Product productFindById(Long id) {
        return null;

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
