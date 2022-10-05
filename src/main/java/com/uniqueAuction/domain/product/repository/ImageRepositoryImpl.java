package com.uniqueAuction.domain.product.repository;

import com.uniqueAuction.domain.product.entity.Image;
import com.uniqueAuction.domain.product.entity.Size;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

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
