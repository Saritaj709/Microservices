package com.bridgelabz.microservices.note.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
import com.bridgelabz.microservices.note.model.Response;
import com.bridgelabz.microservices.note.model.UpdateDTO;
import com.bridgelabz.microservices.note.model.UrlMetaData;
import com.bridgelabz.microservices.note.model.ViewNoteDTO;
import com.bridgelabz.microservices.note.services.NoteService;

/**
 * @author Sarita
 *
 */

@RestController
@RequestMapping("/api/notes")
public class NoteController {

	@Autowired
	private NoteService noteService;

	// -------------Create A New Note----------------------
	/**
	 * 
	 * @param req
	 * @param createDto
	 * @return Note
	 * @throws NoteCreationException
	 * @throws NoteNotFoundException
	 * @throws UnAuthorizedException
	 * @throws DateException
	 * @throws LabelNotFoundException
	 * @throws NullValueException
	 * @throws MalFormedException 
	 */
	@PostMapping(value = "/create")
	public ResponseEntity<ViewNoteDTO> createNote(HttpServletRequest req, @RequestBody CreateDTO createDto)
			throws NoteCreationException, NoteNotFoundException, UnAuthorizedException, DateException,
			LabelNotFoundException, NullValueException, MalFormedException {

		String userId = (String) req.getAttribute("userId");

		ViewNoteDTO viewNoteDto = noteService.createNote(userId, createDto);

		return new ResponseEntity<>(viewNoteDto, HttpStatus.CREATED);
	}
	
	
	/**
	 * @param req
	 * @param noteId
	 * @param url
	 * @return UrlMetaData
	 * @throws NoteNotFoundException
	 * @throws UnAuthorizedException
	 * @throws MalFormedException
	 */
	@PostMapping(value="/scrap")
	public ResponseEntity<List<UrlMetaData>> createContent(HttpServletRequest req,@RequestParam(value="link-url")String url) throws IOException, NoteNotFoundException, UnAuthorizedException, MalFormedException{
		String userId=(String) req.getAttribute("userId");
		List<UrlMetaData> urlMetaData=noteService.addContent(url);
		return new ResponseEntity<>(urlMetaData,HttpStatus.CREATED);
	}

	//--------------------Add Content To Particular Note------------
	@PostMapping(value="/addscrap-to-note")
	public ResponseEntity<Response> addContentTo(HttpServletRequest req,@RequestParam(value="noteId")String noteId,@RequestParam(value="link-url")String url) throws IOException, NoteNotFoundException, UnAuthorizedException, MalFormedException, UrlAdditionException{
		
		String userId=(String) req.getAttribute("userId");
		noteService.addContentToNote(userId,noteId,url);
	
		Response response = new Response();

		response.setMessage("Content succesfully added to note");
		response.setStatus(31);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}

	// ---------------Add Label To Notes-----------------------

	/**
	 * 
	 * @param req
	 * @param labelName
	 * @param noteId
	 * @return response
	 * @throws NoteNotFoundException
	 * @throws UnAuthorizedException
	 * @throws NoteTrashedException
	 * @throws LabelAdditionException
	 */
	@PostMapping(value = "/add-label/{noteId}")
	public ResponseEntity<Response> addLabelToNotes(HttpServletRequest req,
			@RequestParam(value = "labelName") String labelName, @PathVariable(value = "noteId") String noteId)
			throws NoteNotFoundException, UnAuthorizedException, NoteTrashedException, LabelAdditionException {

		String userId = (String) req.getAttribute("userId");

		noteService.addLabel(userId, labelName, noteId);

		Response response = new Response();

		response.setMessage("Label is successfully added");
		response.setStatus(15);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	// -------------Delete A Particular Label From A Particular Note-----

	/**
	 * 
	 * @param req
	 * @param noteId
	 * @param labelId
	 * @return response
	 * @throws Exception
	 */
	@DeleteMapping(value = "/deletelabel-from-particular-note/{labelId}")
	public ResponseEntity<Response> deleteLabelFromParticularNote(HttpServletRequest req,
			@RequestParam(value = "NoteId") String noteId, @PathVariable(value = "labelId") String labelId)
			throws Exception {

		String userId = (String) req.getAttribute("userId");

		noteService.removeLabelFromNote(userId, noteId, labelId);

		Response response = new Response();

		response.setMessage("Label from Note is successfully deleted");
		response.setStatus(20);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	// ------------Update An Existing Note-------------------

	/**
	 * 
	 * @param req
	 * @param updateDto
	 * @return response
	 * @throws NoteNotFoundException
	 * @throws UnAuthorizedException
	 * @throws NoteTrashedException
	 */
	@PutMapping(value = "/update")
	public ResponseEntity<Response> updateNote(HttpServletRequest req, @RequestBody UpdateDTO updateDto)
			throws NoteNotFoundException, UnAuthorizedException, NoteTrashedException {

		String userId = (String) req.getAttribute("userId");

		noteService.updateNote(userId, updateDto);

		Response response = new Response();

		response.setMessage("Note successfully updated");
		response.setStatus(2);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// --------------------View trashed Notes--------------------

	/**
	 * 
	 * @return List of Trashed Notes
	 * @throws UnAuthorizedException
	 * @throws NoteNotFoundException
	 * @throws NoteTrashedException
	 * @throws NullValueException
	 */
	@GetMapping("/view-trashed")
	public ResponseEntity<List<ViewNoteDTO>> viewTrashedNotes(HttpServletRequest req)
			throws UnAuthorizedException, NoteNotFoundException, NoteTrashedException, NullValueException {

		String userId=(String) req.getAttribute("userId");
		noteService.viewTrashed(userId);

		return new ResponseEntity<>(noteService.viewTrashed(userId), HttpStatus.OK);
	}

	// ------------------Move To Trash Or Restore A Note-----------------------

	/**
	 * 
	 * @param req
	 * @param noteId
	 * @param choice
	 * @return response
	 * @throws NoteNotFoundException
	 * @throws UnAuthorizedException
	 * @throws UntrashedException
	 * @throws NoteTrashedException
	 */
	@PostMapping(value = "/delete-restore/{noteId}")
	public ResponseEntity<Response> deleteOrRestoreNote(HttpServletRequest req,
			@PathVariable(value = "noteId") String noteId,
			@RequestParam(value = "choice,true-delete/false-restore") boolean choice)
			throws NoteNotFoundException, UnAuthorizedException, UntrashedException, NoteTrashedException {

		String userId = (String) req.getAttribute("userId");

		noteService.deleteOrRestoreNote(userId, noteId, choice);

		Response response = new Response();

		if (choice) {
			response.setMessage("Note is successfully trashed");
			response.setStatus(117);
		}

		else {
			response.setMessage("Note is successfully restored");
			response.setStatus(119);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// ----------Delete An Existing Note From Trash--------------

	/**
	 * 
	 * @param noteId
	 * @param req
	 * @return response
	 * @throws NoteNotFoundException
	 * @throws UnAuthorizedException
	 * @throws UntrashedException
	 * @throws NoteTrashedException
	 */
	@DeleteMapping(value = "/delete-note-forever/{noteId}")
	public ResponseEntity<Response> deleteNoteForever(@PathVariable(value = "noteId") String noteId,
			HttpServletRequest req)
			throws NoteNotFoundException, UnAuthorizedException, UntrashedException, NoteTrashedException {

		String userId = (String) req.getAttribute("userId");

		noteService.deleteNoteForever(userId, noteId);

		Response response = new Response();

		response.setMessage("Note successfully deleted");
		response.setStatus(4);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// ---------------To Archive Notes----------------------------

	/**
	 * 
	 * @param noteId
	 * @param req
	 * @return response
	 * @throws NoteNotFoundException
	 * @throws UnAuthorizedException
	 * @throws NoteTrashedException
	 * @throws NoteArchievedException
	 * @throws NoteUnArchievedException
	 */
	@PutMapping(value = "/archive-unarchive/{noteId}")
	public ResponseEntity<Response> archieveOrUnArchieveNote(HttpServletRequest req,
			@PathVariable(value = "noteId") String noteId,
			@RequestParam(value = "choice,true-archieve,false-unarchieve") boolean choice) throws NoteNotFoundException,
			UnAuthorizedException, NoteTrashedException, NoteArchievedException, NoteUnArchievedException {

		String userId = (String) req.getAttribute("userId");

		noteService.archieveOrUnArchieveNote(userId, noteId, choice);

		Response response = new Response();

		if (choice) {
			response.setMessage("Note is successfully archieved");
			response.setStatus(11);
		}

		else {
			response.setMessage("Note is successfully unarchieved");
			response.setStatus(24);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// --------------View Archived Notes--------------------------------

	/**
	 * 
	 * @return List of Archived Notes
	 * @throws NullValueException
	 */
	@GetMapping(value = "/view-archived-notes")
	public ResponseEntity<List<ViewNoteDTO>> viewArchievedNotes(HttpServletRequest req) throws NullValueException {

		String userId=(String) req.getAttribute("userId");
		noteService.viewArchieved(userId);

		return new ResponseEntity<>(noteService.viewArchieved(userId), HttpStatus.OK);
	}

	// -------------------To Pin Or Unpin Notes---------------------------------

	/**
	 * 
	 * @param noteId
	 * @param req
	 * @return response
	 * @throws NoteNotFoundException
	 * @throws UnAuthorizedException
	 * @throws NotePinnedException
	 * @throws NoteTrashedException
	 * @throws NoteUnPinnedException
	 */
	@PostMapping(value = "/pin-unpin/{noteId}")
	public ResponseEntity<Response> pinorunpinNote(HttpServletRequest req, @PathVariable String noteId,
			@RequestParam(value = "choice,true-pin/false-unpin") boolean choice) throws NoteNotFoundException,
			UnAuthorizedException, NotePinnedException, NoteTrashedException, NoteUnPinnedException {

		String userId = (String) req.getAttribute("userId");

		noteService.pinOrUnpinNote(userId, noteId, choice);

		Response response = new Response();

		if (choice) {
			response.setMessage("Note is successfully pinned");
			response.setStatus(12);
		}

		else {
			response.setMessage("Note is successfully unpinned");
			response.setStatus(22);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// ------------------View Pinned Notes----------------------------

	/**
	 * 
	 * @return List of Pinned Notes
	 * @throws NullValueException
	 */
	@GetMapping(value = "/view-pinned-notes")
	public ResponseEntity<List<ViewNoteDTO>> viewPinnedNotes(HttpServletRequest req) throws NullValueException {

		String userId=(String) req.getAttribute("userId");
		noteService.viewPinned(userId);

		return new ResponseEntity<>(noteService.viewPinned(userId), HttpStatus.OK);
	}

	// ----------------Read Entire Details Of All The Notes------------

	/**
	 * 
	 * @return List of Notes
	 * @throws NullValueException
	 * @throws NoteNotFoundException
	 * @throws NoteCreationException
	 * @throws UnAuthorizedException
	 */
	@GetMapping("/read-all-notes")
	public ResponseEntity<List<ViewNoteDTO>> readAllNotes()
			throws NullValueException, NoteNotFoundException, NoteCreationException, UnAuthorizedException {

		noteService.readAllNotes();

		return new ResponseEntity<>(noteService.readAllNotes(), HttpStatus.OK);
	}

	// -----------------Read Notes Of A Particular User------------------

	/**
	 * 
	 * @param req
	 * @return List of Notes of A Particular User
	 * @throws NullValueException
	 * @throws NoteNotFoundException
	 * @throws NoteCreationException
	 * @throws UnAuthorizedException
	 */
	@GetMapping("/read-user-notes")
	public ResponseEntity<List<ViewNoteDTO>> readUserNotes(HttpServletRequest req)
			throws NullValueException, NoteNotFoundException, NoteCreationException, UnAuthorizedException {

		String userId = (String) req.getAttribute("userId");
		noteService.readUserNotes(userId);

		return new ResponseEntity<>(noteService.readUserNotes(userId), HttpStatus.OK);
	}

	// ----------Read A Particular Note-------------------------------

	/**
	 * 
	 * @param req
	 * @param noteId
	 * @return A Particular Note By Its Id
	 * @throws UnAuthorizedException
	 * @throws NoteNotFoundException
	 * @throws NoteTrashedException
	 */
	@PostMapping("/getnote/{noteId}")
	public ResponseEntity<ViewNoteDTO> readParticularNote(HttpServletRequest req, @PathVariable("noteId") String noteId)
			throws UnAuthorizedException, NoteNotFoundException, NoteTrashedException {

		String userId = (String) req.getAttribute("userId");

		noteService.findNoteById(userId, noteId);

		return new ResponseEntity<>(noteService.findNoteById(userId, noteId), HttpStatus.OK);
	}

	// -----------Add A Color To A Note-----------------------------------

	/**
	 * 
	 * @param req
	 * @param color
	 * @param noteId
	 * @return response
	 * @throws NoteCreationException
	 * @throws UnAuthorizedException
	 * @throws NoteNotFoundException
	 * @throws NoteTrashedException
	 * @throws DateException
	 */
	@RequestMapping(value = "/add-color/{noteId}", method = RequestMethod.PUT)
	public ResponseEntity<Response> addNoteColor(HttpServletRequest req, @RequestParam(value = "color") String color,
			@PathVariable String noteId) throws NoteCreationException, UnAuthorizedException, NoteNotFoundException,
			NoteTrashedException, DateException {

		String userId = (String) req.getAttribute("userId"); //

		noteService.addColor(userId, color, noteId);

		Response response = new Response();

		response.setMessage("Color is successfully set!!");
		response.setStatus(21);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// ---------Add A Reminder To A Particular Note---------------------

	/**
	 * 
	 * @param req
	 * @param date
	 * @param noteId
	 * @return response
	 * @throws NoteCreationException
	 * @throws UnAuthorizedException
	 * @throws NoteNotFoundException
	 * @throws NoteTrashedException
	 * @throws DateException
	 */
	@RequestMapping(value = "/add-reminder/{noteId}", method = RequestMethod.PUT)
	public ResponseEntity<Response> addNoteReminder(HttpServletRequest req,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, @PathVariable String noteId)
			throws NoteCreationException, UnAuthorizedException, NoteNotFoundException, NoteTrashedException,
			DateException {

		String userId = (String) req.getAttribute("userId");

		noteService.addReminder(userId, date, noteId);

		Response response = new Response();

		response.setMessage("Reminder is successfully set!!");
		response.setStatus(7);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// -------Delete Reminder From A Note---------------------------------

	/**
	 * 
	 * @param req
	 * @param noteId
	 * @return response
	 * @throws NullValueException
	 * @throws UnAuthorizedException
	 * @throws NoteNotFoundException
	 * @throws NoteTrashedException
	 */
	@RequestMapping(value = "/delete-reminder/{noteId}", method = RequestMethod.PUT)
	public ResponseEntity<Response> deleteNoteReminder(HttpServletRequest req, @PathVariable String noteId)
			throws NullValueException, UnAuthorizedException, NoteNotFoundException, NoteTrashedException {

		String userId = (String) req.getAttribute("userId");

		noteService.deleteReminder(userId, noteId);

		Response response = new Response();

		response.setMessage("Reminder is successfully removed!!");
		response.setStatus(9);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
