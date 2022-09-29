package com.uniqueAuction.domain.product.repository;

import com.uniqueAuction.domain.product.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private static final ConcurrentHashMap<Long, Product> store = new ConcurrentHashMap<>();
    private static final AtomicLong sequence = new AtomicLong();


    @Override
    public void saveProduct(Product saveProduct) {
        long aa = sequence.addAndGet(1);
        System.out.println("sequence.addAndGet(1):"+aa);
        store.put(aa, saveProduct);

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

    @Override
    public List<Product> findByAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void clear() {
        store.clear();
    }
}
