package com.uniqueAuction.domain.product.service;


import com.uniqueAuction.domain.product.entity.Product;
import com.uniqueAuction.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public void save(Product product) {
        productRepository.save(product);

    }

    public Product productFindById(Long id) {
        return productRepository.findindById(id);
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
