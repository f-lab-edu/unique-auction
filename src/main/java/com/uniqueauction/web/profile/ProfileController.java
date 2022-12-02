package com.uniqueauction.web.profile;

import java.util.Arrays;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProfileController {

	private final Environment env;

	//profile 조회
	@GetMapping(value = "/profile")
	public String getProfile() {
		return Arrays.stream(env.getActiveProfiles())
			.findFirst()
			.orElse("");
	}
}
