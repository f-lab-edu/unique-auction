package com.uniqueAuction.web.trade.controller;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.uniqueAuction.domain.trade.service.PurchaseService;
import com.uniqueAuction.web.response.CommonResponse;
import com.uniqueAuction.web.trade.request.PurchaseBidRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PurchaseController {

	private final PurchaseService purchaseService;

	@PostMapping("/purchaseBid")
	@ResponseStatus(HttpStatus.CREATED)
	public CommonResponse savePurchaseBid(@RequestBody @Validated PurchaseBidRequest purchaseBidRequest,
		BindingResult result) {
		if (result.hasErrors()) {
			throw new RuntimeException(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
		}
		purchaseService.savePurchaseBid(purchaseBidRequest);
		return CommonResponse.success();
	}
}
