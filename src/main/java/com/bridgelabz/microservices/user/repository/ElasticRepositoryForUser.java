package com.bridgelabz.microservices.user.repository;

import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.bridgelabz.microservices.user.model.User;

public interface ElasticRepositoryForUser extends ElasticsearchRepository<User,String>{

	Optional<User> findByEmail(String email);

	void deleteByEmail(String email);

}
