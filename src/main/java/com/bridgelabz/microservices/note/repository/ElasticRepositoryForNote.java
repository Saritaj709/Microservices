package com.bridgelabz.microservices.note.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.bridgelabz.microservices.note.model.Note;

public interface ElasticRepositoryForNote extends ElasticsearchRepository<Note,String>{

	List<Note> findAllByUserId(String userId);

	Optional<Note> findByNoteId(String noteId);

	void deleteByNoteId(String noteId);

	List<Note> findAllByUserIdAndTrashed(String userId, boolean b);

}
