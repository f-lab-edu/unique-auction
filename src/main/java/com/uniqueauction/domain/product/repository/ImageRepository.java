package com.uniqueauction.domain.product.repository;

import com.uniqueauction.domain.product.entity.Image;

public interface ImageRepository {
	Long save(Image image);

	Image update(Image size);

}
