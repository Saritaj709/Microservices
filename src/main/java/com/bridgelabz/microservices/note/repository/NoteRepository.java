package com.bridgelabz.microservices.note.repository;

import org.springframework.stereotype.Repository;

import com.bridgelabz.microservices.note.model.Note;
import com.bridgelabz.microservices.note.model.ViewNoteDTO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface NoteRepository extends MongoRepository<Note,String>{

	Optional<Note> findByNoteId(String noteId);

	void save(ViewNoteDTO viewNote);

	void deleteByNoteId(String noteId);

	List<Note> findByUserId(String userId);

	List<Note> findAllByUserId(String userId);

	//List<Note> findByAllUserId(String userId);

}
