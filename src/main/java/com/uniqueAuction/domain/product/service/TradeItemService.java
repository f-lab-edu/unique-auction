package com.uniqueAuction.domain.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uniqueAuction.domain.product.entity.TradeItem;
import com.uniqueAuction.domain.product.repository.TradeItemRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TradeItemService {

	private final TradeItemRepository tradeItemRepository;

	public void save(List<TradeItem> tradeItems) {
		for (TradeItem tradeItem : tradeItems) {
			tradeItemRepository.save(tradeItem);
		}
	}

	public void update(List<TradeItem> tradeItems) {
		for (TradeItem tradeItem : tradeItems) {
			tradeItemRepository.update(tradeItem);
		}
	}
}
