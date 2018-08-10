package com.bridgelabz.microservices.user.services;

public interface SocialLoginService {

	public String createFacebookAuthorizationURL();
	public void createFacebookAccessToken(String code);
	public String getName();
}
