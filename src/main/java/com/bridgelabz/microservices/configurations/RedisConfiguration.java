package com.bridgelabz.microservices.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfiguration {
	
	 @Bean
	    public JedisConnectionFactory jedisConnectionFactory() {
	        return new JedisConnectionFactory();
	    }
	
	@Bean
	public RedisTemplate<String, String> redisTemplate(){
		RedisTemplate<String,String> redisTemplate=new RedisTemplate<String,String>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
	    return redisTemplate;
	}
}
