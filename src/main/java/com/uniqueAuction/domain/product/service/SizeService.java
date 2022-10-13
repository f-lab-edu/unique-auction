package com.uniqueAuction.domain.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uniqueAuction.domain.product.entity.Size;
import com.uniqueAuction.domain.product.repository.SizeRepository;

import lombok.RequiredArgsConstructor;

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
