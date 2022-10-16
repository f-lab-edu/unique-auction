package com.uniqueauction.domain.product.service;

import org.springframework.stereotype.Service;

import com.uniqueauction.domain.product.entity.Image;
import com.uniqueauction.domain.product.repository.ImageRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {

	private final ImageRepository imageRepository;

	public long save(Image image) {
		return imageRepository.save(image);
	}

	public Image update(Image image) {
		return imageRepository.update(image);
	}

}
