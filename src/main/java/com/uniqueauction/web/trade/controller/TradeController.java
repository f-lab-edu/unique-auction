package com.uniqueauction.web.trade.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.uniqueauction.domain.trade.service.TradeService;
import com.uniqueauction.web.response.CommonResponse;
import com.uniqueauction.web.trade.request.TradeRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TradeController {

	private final TradeService tradeService;

	@PostMapping("/purchase")
	@ResponseStatus(HttpStatus.CREATED)
	public CommonResponse<?> requestPurchase(@RequestBody @Validated TradeRequest tradeRequest,
		BindingResult result) {
		Long purchaseId = tradeService.requestPurchase(tradeRequest);
		return CommonResponse.success(purchaseId);
	}
}
