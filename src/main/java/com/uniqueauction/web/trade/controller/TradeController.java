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

	/* 구매 - 요청된 판매요청 데이터로 거래 체결 */
	@PostMapping("/purchase")
	@ResponseStatus(HttpStatus.CREATED)
	public CommonResponse<?> createPurchase(@RequestBody @Validated TradeRequest tradeRequest,
		BindingResult result) {
		tradeService.createPurchase(tradeRequest);
		return CommonResponse.success();
	}

	/* 판매 - 요청된 구매요청 데이터로 거래 체결 */
	@PostMapping("/sale")
	@ResponseStatus(HttpStatus.CREATED)
	public CommonResponse<?> createSale(@RequestBody @Validated TradeRequest tradeRequest,
		BindingResult result) {
		tradeService.createSale(tradeRequest);
		return CommonResponse.success();
	}
}
