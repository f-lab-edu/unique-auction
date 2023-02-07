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

	/* 구매요청 - 입찰 */
	@PostMapping("/purchase/bid")
	@ResponseStatus(HttpStatus.CREATED)
	public CommonResponse<?> bidPurchase(@RequestBody @Validated TradeRequest.SaveBidRequest saveBidRequest,
		BindingResult result) {
		Long purchaseId = tradeService.bidPurchase(saveBidRequest);
		return CommonResponse.success(purchaseId);
	}

	/* 판매요청 - 입찰 */
	@PostMapping("/sale/bid")
	@ResponseStatus(HttpStatus.CREATED)
	public CommonResponse<?> bidSale(@RequestBody @Validated TradeRequest.SaveBidRequest saveBidRequest,
		BindingResult result) {
		Long purchaseId = tradeService.bidSale(saveBidRequest);
		return CommonResponse.success(purchaseId);
	}

	/* 구매 - 요청된 판매요청 데이터로 거래 체결 */
	@PostMapping("/purchase")
	@ResponseStatus(HttpStatus.CREATED)
	public CommonResponse<?> createPurchase(@RequestBody @Validated TradeRequest.SaveTradeRequest saveTradeRequest,
		BindingResult result) {
		tradeService.createPurchase(saveTradeRequest);
		return CommonResponse.success();
	}

	/* 판매 - 요청된 구매요청 데이터로 거래 체결 */
	@PostMapping("/sale")
	@ResponseStatus(HttpStatus.CREATED)
	public CommonResponse<?> createSale(@RequestBody @Validated TradeRequest.SaveTradeRequest saveTradeRequest,
		BindingResult result) {
		tradeService.createSale(saveTradeRequest);
		return CommonResponse.success();
	}
}
