package com.bridgelabz.microservices.note.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.microservices.note.model.CreateLabelDTO;
import com.bridgelabz.microservices.note.model.Label;

@Repository
public interface LabelRepository extends MongoRepository<Label,String>{

	Optional<Label> findByLabelId(String labelId);
	
	List<Label> findByLabelName(String labelId);

	void save(CreateLabelDTO createLabelDto);

	void deleteByLabelId(String labelId);

	List<Label> findByUserId(String userId);

	List<Label> findAllByUserId(String userId);

	Optional<Label> findAllByLabelId(List<Label> label);

	void deleteByLabelName(String labelName);

	List<Label> findByLabelNameAndUserId(String labelName, String userId);

	List<Label> findByLabelIdAndUserId(String labelId, String userId);

}
