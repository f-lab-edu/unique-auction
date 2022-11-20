package com.uniqueauction.web.trade.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.uniqueauction.domain.trade.service.PurchaseService;
import com.uniqueauction.web.response.CommonResponse;
import com.uniqueauction.web.trade.request.PurchaseRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PurchaseController {

	private final PurchaseService purchaseService;

	@PostMapping("/purchase")
	@ResponseStatus(HttpStatus.CREATED)
	public CommonResponse<?> savePurchaseNow(@RequestBody @Validated PurchaseRequest purchaseRequest,
		BindingResult result) {
		Long purchaseId = purchaseService.savePurchase(purchaseRequest);
		return CommonResponse.success(purchaseId);
	}
}
