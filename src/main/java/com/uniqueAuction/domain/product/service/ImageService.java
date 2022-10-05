package com.uniqueAuction.domain.product.service;


import com.uniqueAuction.domain.product.entity.Image;
import com.uniqueAuction.domain.product.entity.Size;
import com.uniqueAuction.domain.product.repository.ImageRepository;
import com.uniqueAuction.domain.product.repository.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;


    public long save(Image image) {
        return imageRepository.save(image);
    }

    public Image update(Image image){
        return imageRepository.update(image);
    }

}
