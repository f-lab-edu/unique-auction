package com.uniqueauction.web.profile;

import java.util.Arrays;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ProfiileController {

	private final Environment env;

	// 프로젝트가 실행중일 때 default profile 을 조회하는 API
	@GetMapping("/profile")
	public String getProfile() {
		return Arrays.stream(env.getDefaultProfiles())
			.findFirst()
			.orElse("");
	}
}
