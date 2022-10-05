package com.uniqueAuction.domain.product.repository;

import com.uniqueAuction.domain.product.entity.Product;
import com.uniqueAuction.domain.product.entity.Size;

public interface SizeRepository {
    Long save(Size size);

    Size update(Long id, Size Size);

}
