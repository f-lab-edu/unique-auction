package com.uniqueAuction.domain.product.service;


import com.uniqueAuction.domain.product.entity.Size;
import com.uniqueAuction.domain.product.repository.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class SizeService {

    private final SizeRepository sizeRepository;


    public long save(Size size) {
        return sizeRepository.save(size);
    }

}
