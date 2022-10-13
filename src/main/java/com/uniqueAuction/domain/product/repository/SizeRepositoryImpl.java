package com.uniqueAuction.domain.product.repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.uniqueAuction.domain.product.entity.Size;

@Repository
public class SizeRepositoryImpl implements SizeRepository {

	private static final ConcurrentHashMap<Long, Size> store = new ConcurrentHashMap<>();
	private static final AtomicLong sequence = new AtomicLong();

	@Override
	public void save(Size size) {
		Long id = sequence.addAndGet(1);
		store.put(id, size);
	}

	@Override
	public void update(Size size) {
		store.put(size.getId(), size);
	}
}
