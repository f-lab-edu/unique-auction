package com.uniqueAuction.domain.product.repository;

import com.uniqueAuction.domain.product.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private static final ConcurrentHashMap<Long, Product> store = new ConcurrentHashMap<>();
    private static final AtomicLong sequence = new AtomicLong();


    @Override
    public void saveProduct(Product saveProduct) {
        store.put(sequence.addAndGet(1), saveProduct);

    }

    @Override
    public Product productFindById(Long id) {
        return store.get(id);
    }

    @Override
    public Product update(Long id, Product updateProduct) {
        store.put(id, updateProduct);
        return store.get(id);
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }
}
