package com.bridgelabz.microservices.note.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class RandomStringUUID {

	public String generateUUId() {
		UUID uuid = UUID.randomUUID();
		String randomUUIDString = uuid.toString();
		return randomUUIDString;
	}
	
	public UUID generateUUId(String userId) {
		UUID uuid = UUID.fromString(userId);
		return uuid;
	}
}
