package com.uniqueAuction.web.trade.controller;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.uniqueAuction.domain.trade.service.SaleService;
import com.uniqueAuction.web.response.CommonResponse;
import com.uniqueAuction.web.trade.request.SaleBidRequest;
import com.uniqueAuction.web.trade.request.SaleNowRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SaleController {

	private final SaleService saleService;

	@PostMapping("/saleBid")
	@ResponseStatus(HttpStatus.CREATED)
	public CommonResponse saveSaleBid(@RequestBody @Validated SaleBidRequest saleBidRequest, BindingResult result) {
		if (result.hasErrors()) {
			throw new RuntimeException(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
		}
		saleService.saveSaleBid(saleBidRequest.toEntity());
		return CommonResponse.success();
	}

	@PostMapping("/saleNow")
	@ResponseStatus(HttpStatus.CREATED)
	public CommonResponse saveSaleNow(@RequestBody @Validated SaleNowRequest saleNowRequest,
		BindingResult result) {
		if (result.hasErrors()) {
			throw new RuntimeException(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
		}
		saleService.saveSaleNow(saleNowRequest.toEntity());
		return CommonResponse.success();
	}
}
