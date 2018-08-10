package com.bridgelabz.microservices.user.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisRepositoryImpl implements RedisRepository{

	@Value("${Key}")
	private String KEY;
			
	private RedisTemplate<String, String> redisTemplate;
	private HashOperations<String,String,String> hashOperations;
	
	@Autowired
	RedisRepositoryImpl(RedisTemplate<String,String> redisTemplate){
		this.redisTemplate=redisTemplate;
		hashOperations=this.redisTemplate.opsForHash();

	}

	@Override
	public void save(String randomString,String email) {
     
		hashOperations.put(KEY,randomString, email);
		
	}

	@Override
	public String get(String uuid) {
		return hashOperations.get(KEY, uuid);
	}

	@Override
	public void delete(String uuid) {
		hashOperations.delete(KEY, uuid);
	}

}
