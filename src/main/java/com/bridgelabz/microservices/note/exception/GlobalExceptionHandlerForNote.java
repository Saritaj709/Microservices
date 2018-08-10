package com.bridgelabz.microservices.note.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.microservices.note.controller.NoteController;
import com.bridgelabz.microservices.note.model.Response;

@ControllerAdvice
public class GlobalExceptionHandlerForNote {

	public static final Logger logger = LoggerFactory.getLogger(NoteController.class);

	@ExceptionHandler(NoteCreationException.class)
	public ResponseEntity<Response> noteExceptionHandler(NoteCreationException e) {
		logger.error("note entry error");
		Response response = new Response();
		response.setMessage("note entry error, " + e.getMessage());
		response.setStatus(101);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoteNotFoundException.class)
	public ResponseEntity<Response> noteNotFoundExceptionHandler(NoteNotFoundException e) {
		logger.error("note not found error");
		Response response = new Response();
		response.setMessage("note not found exception, " + e.getMessage());
		response.setStatus(201);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UnAuthorizedException.class)
	public ResponseEntity<Response> userNotFoundExceptionHandler(UnAuthorizedException e) {
		logger.error("UnAuthorized error");
		Response response = new Response();
		response.setMessage("UnAuthorized exception, " + e.getMessage());
		response.setStatus(301);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NullValueException.class)
	public ResponseEntity<Response> nullEntryExceptionHandler(NullValueException e) {
		logger.error("null value error");
		Response response = new Response();
		response.setMessage("null value exception, " + e.getMessage());
		response.setStatus(401);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UntrashedException.class)
	public ResponseEntity<Response> untrashedExceptionHandler(UntrashedException e) {
		logger.error("note untrashed error");
		Response response = new Response();
		response.setMessage("note untrashed exception, " + e.getMessage());
		response.setStatus(401);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoteTrashedException.class)
	public ResponseEntity<Response> trashedNoteExceptionHandler(NoteTrashedException e) {
		logger.error("note trashed error");
		Response response = new Response();
		response.setMessage("note trashed exception, " + e.getMessage());
		response.setStatus(601);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoRemiderToSetException.class)
	public ResponseEntity<Response> noReminderToSetExceptionHandler(NoRemiderToSetException e) {
		logger.error("no reminder to set error");
		Response response = new Response();
		response.setMessage("no reminder to set exception, " + e.getMessage());
		response.setStatus(701);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoteArchievedException.class)
	public ResponseEntity<Response> noteArchievedExceptionHandler(NoteArchievedException e) {
		logger.error("no reminder to set error");
		Response response = new Response();
		response.setMessage("no reminder to set exception, " + e.getMessage());
		response.setStatus(801);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NotePinnedException.class)
	public ResponseEntity<Response> notePinnedExceptionHandler(NotePinnedException e) {
		logger.error("no reminder to set error");
		Response response = new Response();
		response.setMessage("note pinned exception, " + e.getMessage());
		response.setStatus(901);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchLabelException.class)
	public ResponseEntity<Response> noSuchLabelExceptionHandler(NoSuchLabelException e) {
		logger.error("no label to set error");
		Response response = new Response();
		response.setMessage("no label available to set exception, " + e.getMessage());
		response.setStatus(111);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(LabelCreationException.class)
	public ResponseEntity<Response> labelCreationExceptionHandler(LabelCreationException e) {
		logger.error("no label to set error");
		Response response = new Response();
		response.setMessage("no label available to set exception, " + e.getMessage());
		response.setStatus(112);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DateException.class)
	public ResponseEntity<Response> dateExceptionHandler(DateException e) {
		logger.error("date error");
		Response response = new Response();
		response.setMessage("date exception, " + e.getMessage());
		response.setStatus(113);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(LabelAdditionException.class)
	public ResponseEntity<Response> LabelAdditionExceptionHandler(LabelAdditionException e) {
		logger.error("label addition error in list");
		Response response = new Response();
		response.setMessage("label addition exception, " + e.getMessage());
		response.setStatus(114);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(LabelNotFoundException.class)
	public ResponseEntity<Response> labelNotFoundExceptionHandler(LabelNotFoundException e) {
		logger.error("label not found error in list");
		Response response = new Response();
		response.setMessage("label not found exception, " + e.getMessage());
		response.setStatus(116);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoteUnPinnedException.class)
	public ResponseEntity<Response> noteUnPinnedExceptionHandler(NoteUnPinnedException e) {
		logger.error("note unpinned error");
		Response response = new Response();
		response.setMessage("note unpinned exception, " + e.getMessage());
		response.setStatus(119);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoteUnArchievedException.class)
	public ResponseEntity<Response> noteUnArchievedExceptionHandler(NoteUnArchievedException e) {
		logger.error("note unarchieved error");
		Response response = new Response();
		response.setMessage("note unarchieved exception, " + e.getMessage());
		response.setStatus(120);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MalFormedException.class)
	public ResponseEntity<Response> malformedExceptionHandler(MalFormedException e) {
		logger.error("malformed error jsoup");
		Response response = new Response();
		response.setMessage("malformed exception jsoup, " + e.getMessage());
		response.setStatus(121);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UrlAdditionException.class)
	public ResponseEntity<Response> urlAdditionExceptionHandler(UrlAdditionException
			 e) {
		logger.error("malformed error jsoup");
		Response response = new Response();
		response.setMessage("malformed exception jsoup, " + e.getMessage());
		response.setStatus(122);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	
	/*@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseDTO> controller(Exception e) {
		logger.error("other exceptions");
		ResponseDTO response=new ResponseDTO();
		response.setMessage("Some exceptions occured, "+e.getMessage());
		response.setStatus(-1);
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}*/
}
