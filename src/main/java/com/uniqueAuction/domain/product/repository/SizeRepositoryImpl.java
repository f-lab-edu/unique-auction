package com.uniqueAuction.domain.product.repository;

import com.uniqueAuction.domain.product.entity.Size;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class SizeRepositoryImpl implements SizeRepository {

    private static final ConcurrentHashMap<Long, Size> store = new ConcurrentHashMap<>();
    private static final AtomicLong sequence = new AtomicLong();


    @Override
    public Long save(Size size) {
        Long id = sequence.addAndGet(1);
        store.put(id, size);

        return id;

    }

    @Override
    public Size update(Long id, Size size) {
        store.put(id, size);
        return store.get(id);
    }


}
