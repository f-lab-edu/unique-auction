package com.uniqueauction.domain.product.repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.uniqueauction.domain.product.entity.Image;

@Repository
public class ImageRepositoryImpl implements ImageRepository {

	private static final ConcurrentHashMap<Long, Image> store = new ConcurrentHashMap<>();
	private static final AtomicLong sequence = new AtomicLong();

	@Override
	public Long save(Image image) {
		Long imageId = sequence.addAndGet(1);
		store.put(imageId, image);

		return imageId;

	}

	@Override
	public Image update(Image image) {
		store.put(image.getId(), image);
		return store.get(image.getId());
	}
}
