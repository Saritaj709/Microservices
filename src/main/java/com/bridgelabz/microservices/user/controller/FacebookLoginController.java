package com.bridgelabz.microservices.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.microservices.user.services.SocialLoginService;

@RestController
@RequestMapping("/facebook")
public class FacebookLoginController {

	@Autowired
	SocialLoginService facebookService;

	@GetMapping("/createFacebookAuthorization")
	public String createFacebookAuthorization() {
		return facebookService.createFacebookAuthorizationURL();
	}

	@GetMapping("/facebook")
	public void createFacebookAccessToken(@RequestParam("code") String code) {
		facebookService.createFacebookAccessToken(code);
	}

	@GetMapping("/getName")
	public String getNameResponse() {
		return facebookService.getName();
	}
}
