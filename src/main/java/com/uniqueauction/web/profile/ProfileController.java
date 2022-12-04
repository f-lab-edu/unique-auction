package com.uniqueauction.web.profile;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProfileController {

	private final Environment env;

	//profile 조회
	//  @GetMapping(value = "/profile")
	// public String getProfile() {
	// 	return Arrays.stream(env.getActiveProfiles())
	// 		.findFirst()
	// 		.orElse("");
	// }

	@GetMapping("/profile")
	public String profile() {
		List<String> profiles = Arrays.asList(env.getActiveProfiles());
		List<String> realProfiles = Arrays.asList("real1", "real2");

		String defaultProfile = profiles.isEmpty() ? "real2" : profiles.get(0);

		return profiles.stream()
			.filter(realProfiles::contains)
			.findAny()
			.orElse(defaultProfile);
	}
}
