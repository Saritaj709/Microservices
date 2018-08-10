package com.bridgelabz.microservices.note.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.bridgelabz.microservices.note.exception.DateException;
import com.bridgelabz.microservices.note.exception.LabelAdditionException;
import com.bridgelabz.microservices.note.exception.LabelNotFoundException;
import com.bridgelabz.microservices.note.exception.MalFormedException;
import com.bridgelabz.microservices.note.exception.NoteArchievedException;
import com.bridgelabz.microservices.note.exception.NoteCreationException;
import com.bridgelabz.microservices.note.exception.NoteNotFoundException;
import com.bridgelabz.microservices.note.exception.NotePinnedException;
import com.bridgelabz.microservices.note.exception.NoteTrashedException;
import com.bridgelabz.microservices.note.exception.NoteUnArchievedException;
import com.bridgelabz.microservices.note.exception.NoteUnPinnedException;
import com.bridgelabz.microservices.note.exception.NullValueException;
import com.bridgelabz.microservices.note.exception.UnAuthorizedException;
import com.bridgelabz.microservices.note.exception.UntrashedException;
import com.bridgelabz.microservices.note.exception.UrlAdditionException;
import com.bridgelabz.microservices.note.model.CreateDTO;
import com.bridgelabz.microservices.note.model.UpdateDTO;
import com.bridgelabz.microservices.note.model.UrlMetaData;
import com.bridgelabz.microservices.note.model.ViewNoteDTO;

public interface NoteService {
	
	/**
	 * 
	 * @param token
	 * @param create
	 * @return Note
	 * @throws NoteNotFoundException
	 * @throws NoteCreationException
	 * @throws UnAuthorizedException
	 * @throws DateException
	 * @throws LabelNotFoundException
	 * @throws NullValueException
	 * @throws MalFormedException 
	 */
	ViewNoteDTO createNote(String token,CreateDTO create) throws NoteNotFoundException, NoteCreationException, UnAuthorizedException, DateException, LabelNotFoundException, NullValueException, MalFormedException;

	/**
	 * 
	 * @param token
	 * @param update
	 * @throws NoteNotFoundException
	 * @throws UnAuthorizedException
	 * @throws NoteTrashedException
	 */
	void updateNote(String token,UpdateDTO update) throws NoteNotFoundException, UnAuthorizedException, NoteTrashedException;

	/**
	 * 
	 * @return List of Notes of A Particular user
	 * @throws NullValueException
	 */
	List<ViewNoteDTO> readAllNotes() throws NullValueException;
	
	/**
	 * 
	 * @param token
	 * @param noteId
	 * @return List of ViewNoteDTO
	 * @throws UnAuthorizedException
	 * @throws NoteNotFoundException
	 * @throws NoteTrashedException
	 */
	ViewNoteDTO findNoteById(String token,String noteId) throws UnAuthorizedException, NoteNotFoundException, NoteTrashedException;
    
	/**
	 * 
	 * @param token
	 * @param noteId
	 * @throws NoteNotFoundException
	 * @throws UnAuthorizedException
	 * @throws UntrashedException
	 * @throws NoteTrashedException
	 */
	void deleteNoteForever(String token,String noteId) throws NoteNotFoundException, UnAuthorizedException, UntrashedException, NoteTrashedException;

	/**
	 * 
	 * @param token
	 * @param date
	 * @param noteId
	 * @return boolean 
	 * @throws UnAuthorizedException
	 * @throws NoteNotFoundException
	 * @throws NoteTrashedException
	 * @throws DateException
	 */
	void addReminder(String token,Date date,String noteId) throws UnAuthorizedException, NoteNotFoundException, NoteTrashedException, DateException;

	/**
	 * 
	 * @param token
	 * @param noteId
	 * @throws NullValueException
	 * @throws UnAuthorizedException
	 * @throws NoteNotFoundException
	 * @throws NoteTrashedException
	 */
	void deleteReminder(String token,String noteId) throws NullValueException, UnAuthorizedException, NoteNotFoundException, NoteTrashedException;

	/**
	 * 
	 * @param token
	 * @param noteId
	 * @throws NoteNotFoundException
	 * @throws UnAuthorizedException
	 * @throws NoteArchievedException
	 * @throws NoteTrashedException
	 * @throws NoteUnArchievedException 
	 */
	void archieveOrUnArchieveNote(String token, String noteId,boolean choice) throws NoteNotFoundException, UnAuthorizedException, NoteArchievedException, NoteTrashedException, NoteUnArchievedException;

	/**
	 * 
	 * @param token
	 * @param noteId
	 * @throws NoteNotFoundException
	 * @throws UnAuthorizedException
	 * @throws NotePinnedException
	 * @throws NoteTrashedException
	 * @throws NoteUnPinnedException 
	 */
	void pinOrUnpinNote(String userId, String noteId,boolean choice) throws NoteNotFoundException, UnAuthorizedException, NotePinnedException, NoteTrashedException, NoteUnPinnedException;

	/**
	 * 
	 * @return List of Archived Notes
	 * @throws NullValueException
	 */
	List<ViewNoteDTO> viewArchieved(String userId) throws NullValueException;

	/**
	 * 
	 * @return List of Pinned Notes
	 * @throws NullValueException
	 */
	List<ViewNoteDTO> viewPinned(String userId) throws NullValueException;

	/**
	 * 
	 * @return List of Trashed Notes
	 * @throws NullValueException
	 */
	List<ViewNoteDTO> viewTrashed(String userId) throws NullValueException;

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
	 * @param token
	 * @param noteId
	 * @param choice
	 * @throws NoteNotFoundException
	 * @throws UnAuthorizedException
	 * @throws UntrashedException
	 * @throws NoteTrashedException
	 */
	void deleteOrRestoreNote(String token, String noteId, boolean choice) throws NoteNotFoundException, UnAuthorizedException, UntrashedException, NoteTrashedException;

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

	/**
	 * 
	 * @param userId
	 * @return List of ViewNoteDTO
	 * @throws NullValueException
	 */
	List<ViewNoteDTO> readUserNotes(String userId) throws NullValueException;

	/**
	 * 
	 * @param userId
	 * @param color
	 * @param noteId
	 * @throws NoteNotFoundException
	 * @throws UnAuthorizedException
	 * @throws NoteTrashedException
	 */
	void addColor(String userId, String color, String noteId) throws NoteNotFoundException, UnAuthorizedException, NoteTrashedException;
   	
	/**
	 * @param userId
	 * @param metaData
	 * @return UrlMetaData
	 * @throws IOException 
	 * @throws NoteNotFoundException 
	 * @throws UnAuthorizedException 
	 * @throws MalFormedException 
	 */
	List<UrlMetaData> addContent(String url) throws IOException, NoteNotFoundException, UnAuthorizedException, MalFormedException;

	/**
	 * @param userId
	 * @param noteId
	 * @param url
	 * @throws MalFormedException 
	 * @throws NoteNotFoundException 
	 * @throws UnAuthorizedException 
	 * @throws UrlAdditionException 
	 */
	void addContentToNote(String userId,String noteId,String url) throws MalFormedException, NoteNotFoundException, UnAuthorizedException, UrlAdditionException;
}
