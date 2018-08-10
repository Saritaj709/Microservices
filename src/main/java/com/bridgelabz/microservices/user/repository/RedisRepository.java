package com.bridgelabz.microservices.user.repository;

public interface RedisRepository {

	public void save(String UUID,String email);

	public String get(String UUID);

	public void delete(String uuid);
}
