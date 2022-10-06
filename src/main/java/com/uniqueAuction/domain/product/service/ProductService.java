package com.uniqueAuction.domain.product.service;


import com.uniqueAuction.domain.product.entity.Product;
import com.uniqueAuction.domain.product.repository.ImageRepository;
import com.uniqueAuction.domain.product.repository.ProductRepository;
import com.uniqueAuction.domain.product.repository.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;


    public long save(Product product) {
       return productRepository.save(product);
    }

    public Product productFindById(Long id) {
        return productRepository.findById(id);
    }

    public Product updateProduct(Product updateProduct) {

        return productRepository.update(updateProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.delete(id);
    }
}
