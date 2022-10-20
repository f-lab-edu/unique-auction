package com.uniqueauction.domain.product.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.uniqueauction.domain.product.entity.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	private static final ConcurrentHashMap<Long, Product> store = new ConcurrentHashMap<>();
	private static Long sequence = 0L;

	@Override
	public long save(Product saveProduct) {
		sequence++;
		store.put(sequence, saveProduct);
		return sequence;

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

	@Override
	public List<Product> findByNameOrModelNumber(String serachProduct) {
		return findByAll().stream()
			.filter(p -> p.getName().equals(serachProduct) || p.getModelNumber().equals(serachProduct))
			.collect(Collectors.toList());
	}

}
