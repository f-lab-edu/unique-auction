package com.uniqueauction.web.trade.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.uniqueauction.domain.trade.service.SaleService;
import com.uniqueauction.web.response.CommonResponse;
import com.uniqueauction.web.trade.request.SaleRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SaleController {

	private final SaleService saleService;

	@PostMapping("/sale")
	@ResponseStatus(HttpStatus.CREATED)
	public CommonResponse<?> saveSaleNow(@RequestBody @Validated SaleRequest saleRequest,
		BindingResult result) {

		Long saleId = saleService.saveSale(saleRequest);
		return CommonResponse.success(saleId);
	}
}
