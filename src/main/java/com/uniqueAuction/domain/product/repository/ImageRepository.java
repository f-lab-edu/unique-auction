package com.uniqueAuction.domain.product.repository;

import com.uniqueAuction.domain.product.entity.Image;

public interface ImageRepository {
    Long save(Image image);

    Image update(Long id, Image Size);

}
