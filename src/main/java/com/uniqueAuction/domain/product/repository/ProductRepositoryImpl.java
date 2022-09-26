package com.uniqueAuction.domain.product.repository;

import com.uniqueAuction.domain.product.entity.Product;
import com.uniqueAuction.domain.user.entity.User;
import com.uniqueAuction.web.product.request.ProductSaveRequest;
import com.uniqueAuction.web.product.request.ProductUpdateRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import static com.uniqueAuction.web.product.request.ProductSaveRequest.saveToProduct;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private static final ConcurrentHashMap<Long, Product> store = new ConcurrentHashMap<>();
    private static final AtomicLong sequence = new AtomicLong();


    @Override
    public void saveProduct(ProductSaveRequest productSaveRequest) {
        store.put(sequence.addAndGet(1) , saveToProduct(productSaveRequest));
    }

    @Override
    public Optional<Product> productFindById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public void update(Long id, Product updateProduct) {
        store.put(id,updateProduct);
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }
}
