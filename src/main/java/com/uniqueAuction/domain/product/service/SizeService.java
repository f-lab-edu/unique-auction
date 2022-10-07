package com.uniqueAuction.domain.product.service;


import com.uniqueAuction.domain.product.entity.Size;
import com.uniqueAuction.domain.product.repository.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class SizeService {

    private final SizeRepository sizeRepository;



    public void save(List<Size> size) {
        for (Size s : size) {
            sizeRepository.save(s);
        }
    }

    public void update(List<Size> size) {
        for (Size s : size) {
            sizeRepository.update(s);
        }
    }
}
