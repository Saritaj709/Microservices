package com.bridgelabz.microservices.note.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.bridgelabz.microservices.note.model.Label;

public interface ElasticRepositoryForLabel extends ElasticsearchRepository<Label,String>{

	List<Label> findByLabelNameAndUserId(String labelName, String userId);

	List<Label> findByLabelIdAndUserId(String labelId, String userId);

	Optional<Label> findByLabelId(String labelId);

	void deleteByLabelId(String labelId);

	List<Label> findByUserId(String userId);

}
