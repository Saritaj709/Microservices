package com.bridgelabz.microservices.note.services;

import java.util.List;

import com.bridgelabz.microservices.note.exception.LabelAdditionException;
import com.bridgelabz.microservices.note.exception.LabelNotFoundException;
import com.bridgelabz.microservices.note.exception.NoteNotFoundException;
import com.bridgelabz.microservices.note.exception.NoteTrashedException;
import com.bridgelabz.microservices.note.exception.NullValueException;
import com.bridgelabz.microservices.note.exception.UnAuthorizedException;
import com.bridgelabz.microservices.note.model.Label;
import com.bridgelabz.microservices.note.model.LabelDTO;
import com.bridgelabz.microservices.note.model.ViewNoteDTO;

public interface LabelService {

	/**
	 * 
	 * @param token
	 * @param labelName
	 * @return Label
	 * @throws UnAuthorizedException
	 * @throws NullValueException
	 * @throws NoteNotFoundException
	 */
	LabelDTO createLabel(String token, String labelName) throws UnAuthorizedException, NullValueException, NoteNotFoundException;

	/**
	 * 
	 * @param token
	 * @param labelId
	 * @param noteId
	 * @throws NoteNotFoundException
	 * @throws UnAuthorizedException
	 * @throws NoteTrashedException
	 * @throws LabelAdditionException
	 */
	void addLabel(String token, String labelId, String noteId) throws NoteNotFoundException, UnAuthorizedException, NoteTrashedException, LabelAdditionException;

	/**
	 * 
	 * @return List of Labels
	 * @throws NullValueException
	 */
	List<Label> viewLabels() throws NullValueException;

	/**
	 * 
	 * @param userId
	 * @param labelName
	 * @throws NoteNotFoundException
	 * @throws LabelNotFoundException
	 * @throws UnAuthorizedException
	 */
	void removeLabel(String userId, String labelName) throws NoteNotFoundException, LabelNotFoundException, UnAuthorizedException;

	/**
	 * 
	 * @param userId
	 * @param labelId
	 * @param labelName
	 * @throws LabelNotFoundException
	 * @throws UnAuthorizedException
	 */
	void editLabel(String userId, String labelId, String labelName) throws LabelNotFoundException, UnAuthorizedException;

	/**
	 * 
	 * @param userId
	 * @param labelId
	 * @return List of ViewNoteDTO
	 * @throws LabelNotFoundException
	 * @throws UnAuthorizedException
	 * @throws NoteNotFoundException
	 */
	List<ViewNoteDTO> viewLabel(String userId,String labelId) throws LabelNotFoundException, UnAuthorizedException, NoteNotFoundException;
	/**
	 * 
	 * @param userId
	 * @return LabelDTO
	 * @throws NullValueException 
	 */
	List<LabelDTO> viewUserLabels(String userId) throws NullValueException;
	/**
	 * 
	 * @param userId
	 * @param noteId
	 * @param labelId
	 * @throws LabelNotFoundException
	 * @throws NoteNotFoundException
	 * @throws UnAuthorizedException
	 */
	void removeLabelFromNote(String userId, String noteId, String labelId) throws LabelNotFoundException, NoteNotFoundException, UnAuthorizedException;

}
