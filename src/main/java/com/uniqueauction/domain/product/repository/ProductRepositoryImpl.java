package com.uniqueauction.domain.product.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.uniqueauction.domain.product.entity.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	private static final ConcurrentHashMap<Long, Product> store = new ConcurrentHashMap<>();
	private static final AtomicLong sequence = new AtomicLong();

	@Override
	public long save(Product saveProduct) {
		Long id = sequence.addAndGet(1);
		store.put(id, saveProduct);
		return id;

	}

	@Override
	public Product findById(Long id) {
		return store.get(id);
	}

	@Override
	public Product update(Product updateProduct) {
		store.put(updateProduct.getId(), updateProduct);
		return store.get(updateProduct.getId());
	}

	@Override
	public void delete(Long id) {
		store.remove(id);
	}

	@Override
	public void deleteAll() {
		store.clear();
	}

	@Override
	public List<Product> findByAll() {
		return new ArrayList<>(store.values());
	}

}
