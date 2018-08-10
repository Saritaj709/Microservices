package com.bridgelabz.microservices.note.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.bridgelabz.microservices.note.exception.LabelAdditionException;
import com.bridgelabz.microservices.note.exception.LabelCreationException;
import com.bridgelabz.microservices.note.exception.LabelNotFoundException;
import com.bridgelabz.microservices.note.exception.NoSuchLabelException;
import com.bridgelabz.microservices.note.exception.NoteNotFoundException;
import com.bridgelabz.microservices.note.exception.NoteTrashedException;
import com.bridgelabz.microservices.note.exception.NullValueException;
import com.bridgelabz.microservices.note.exception.UnAuthorizedException;
import com.bridgelabz.microservices.note.model.Label;
import com.bridgelabz.microservices.note.model.LabelDTO;
import com.bridgelabz.microservices.note.model.Note;
import com.bridgelabz.microservices.note.model.ViewNoteDTO;
import com.bridgelabz.microservices.note.repository.ElasticRepositoryForLabel;
import com.bridgelabz.microservices.note.repository.ElasticRepositoryForNote;
import com.bridgelabz.microservices.note.repository.LabelRepository;
import com.bridgelabz.microservices.note.repository.NoteRepository;
import com.bridgelabz.microservices.note.utility.NoteUtility;

@Service
public class LabelServiceImpl implements LabelService{
	
	@Autowired
	private NoteRepository noteRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private LabelRepository labelRepository;
	
	@Autowired
	private ElasticRepositoryForLabel labelElasticRepository;
	
	@Autowired
	private ElasticRepositoryForNote noteElasticRepository;
	
	@Autowired
	private Environment environment;

	/**
	 * 
	 * @param token
	 * @param createLabelDto
	 * @return Label
	 * @throws UnAuthorizedException
	 * @throws NullValueException
	 * @throws NoteNotFoundException
	 */
	@Override
	public LabelDTO createLabel(String userId,String labelName)
			throws UnAuthorizedException, NullValueException, NoteNotFoundException {

		NoteUtility.validateLabelCreation(labelName);

		List<Label> labels = labelElasticRepository.findByLabelNameAndUserId(labelName,userId);

		if (!labels.isEmpty()) {
			throw new LabelCreationException("label with this name already exists");
		}

		Label label = new Label();
		label.setLabelName(labelName);
		label.setUserId(userId);

		labelRepository.save(label);
		
		labelElasticRepository.save(label);
		LabelDTO labelDto=modelMapper.map(label,LabelDTO.class);

		return labelDto;
	}

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
	@Override
	public void addLabel(String userId, String labelId, String noteId)
			throws NoteNotFoundException, UnAuthorizedException, NoteTrashedException, LabelAdditionException {

		Optional<Note> checkNote = noteElasticRepository.findById(noteId);
		
		if (!checkNote.isPresent()) {
			throw new NoteNotFoundException(environment.getProperty("NoteNotFoundException"));
		}

		Note note=checkNote.get();

		if (note.isTrashed()) {
			throw new NoteTrashedException(environment.getProperty("NoteTrashedException"));
		}
		
		if (!note.getUserId().equals(userId)) {
			throw new UnAuthorizedException(environment.getProperty("UnAuthorizedException"));
		}

		List<Label> labels = labelElasticRepository.findByLabelIdAndUserId(labelId,userId);
		
		if (labels.isEmpty()) {
			throw new NoSuchLabelException(environment.getProperty("NoSuchLabelException"));
		}
	
		List<Label> tempList = new LinkedList<>();

		tempList = note.getLabels();

		if (tempList != null) {

			for (int i = 0; i < tempList.size(); i++) {

				if (tempList.get(i).getLabelId().equals(labelId)) {
					throw new LabelAdditionException(environment.getProperty("LabelAdditionException"));
				}
			}
			tempList.addAll(labels);

			note.setLabels(tempList);
		}

		else {
			note.setLabels(labels);
		}
		noteRepository.save(note);
		
		noteElasticRepository.save(note);

	}

	/**
	 * 
	 * @return List of Labels
	 * @throws NullValueException
	 */
	@Override
	public List<Label> viewLabels() throws NullValueException {
		
		List<Label> labelList = (List<Label>) labelElasticRepository.findAll();

		if (labelList == null) {
			throw new NullValueException(environment.getProperty("NullValueException"));
		}

		return labelList;
	}

	/**
	 * 
	 * @param userId
	 * @param labelId
	 * @return List of ViewNoteDTO
	 * @throws LabelNotFoundException
	 * @throws UnAuthorizedException
	 * @throws NoteNotFoundException
	 */
	@Override
	public List<ViewNoteDTO> viewLabel(String userId, String labelId)
			throws LabelNotFoundException, UnAuthorizedException, NoteNotFoundException {

		Optional<Label> optionalLabel = labelElasticRepository.findByLabelId(labelId);
		if (!optionalLabel.isPresent()) {
			throw new LabelNotFoundException("The label with the given id does not exist");
		}
		
		Label label=optionalLabel.get();

		if (!label.getUserId().equals(userId)) {
			throw new UnAuthorizedException("The user with the given id does not exist");
		}
       
	List<Note> noteList =noteElasticRepository.findAllByUserId(userId);

		if (noteList == null) {
			throw new NoteNotFoundException("no such note available");
		}

		return noteList.stream()
				.filter(
						noteStream->noteStream.getLabels().stream().anyMatch(labelStream->labelStream.getLabelId().equals(labelId))).map(filterNote->modelMapper.map(filterNote, ViewNoteDTO.class)).collect(Collectors.toList());
	
	}

	/**
	 * 
	 * @param userId
	 * @param labelName
	 * @throws NoteNotFoundException
	 * @throws LabelNotFoundException
	 * @throws UnAuthorizedException
	 */
	@Override
	public void removeLabel(String userId, String labelId)
			throws NoteNotFoundException, LabelNotFoundException, UnAuthorizedException {

		Optional<Label> optionalLabel = labelElasticRepository.findByLabelId(labelId);
		
		if (!optionalLabel.isPresent()) {
			throw new LabelNotFoundException("label is not present in list");
		}
        
		Label label=optionalLabel.get();
		
		if (!label.getUserId().equals(userId)) {
			throw new UnAuthorizedException("the user with given id is not found");
		}
		
		List<Note> noteList = noteElasticRepository.findAllByUserId(userId);
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			for (int j = 0; j < note.getLabels().size(); j++) {
				if (note.getLabels().get(j).getLabelId().equals(labelId)) {
					note.getLabels().remove(i);
					noteRepository.save(note);
					noteElasticRepository.save(note);
				}
			}
		}
		/*noteList.stream().filter(note->labels.stream().anyMatch(label->label.getLabelId().equals(labelId)).collect(Collectors.toList());*/

		labelRepository.deleteByLabelId(labelId);
		labelElasticRepository.deleteByLabelId(labelId);
	}

	/**
	 * 
	 * @param userId
	 * @param labelId
	 * @param labelName
	 * @throws LabelNotFoundException
	 * @throws UnAuthorizedException
	 */
	@Override
	public void editLabel(String userId, String labelId, String labelName)
			throws LabelNotFoundException, UnAuthorizedException {

		//Optional<Label> optionalLabel = labelRepository.findByLabelId(labelId);
		
		Optional<Label> optionalLabel =labelElasticRepository.findByLabelId(labelId);
		
		if (!optionalLabel.isPresent()) {
			throw new LabelNotFoundException("label is not present in list");
		}
		
		Label label=optionalLabel.get();

		if (!label.getUserId().equals(userId)) {
			throw new UnAuthorizedException("the user with given id is not found");
		}
		
		//List<Note> noteList = noteRepository.findAllByUserId(userId);
		List<Note> noteList = noteElasticRepository.findAllByUserId(userId);
		
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			for (int j = 0; j < note.getLabels().size(); j++) {
				if (note.getLabels().get(j).getLabelId().equals(labelId)) {
					note.getLabels().get(i).setLabelName(labelName);
					noteRepository.save(note);
					noteElasticRepository.save(note);
				}
			}
		}
		label.setLabelName(labelName);
		labelRepository.save(label);
		labelElasticRepository.save(label);
	}

	/**
	 * 
	 * @param userId
	 * @return LabelDTO
	 * @throws NullValueException 
	 */
	@Override
	public List<LabelDTO> viewUserLabels(String userId) throws NullValueException {

	//List<Label> labels=labelRepository.findByUserId(userId);
	List<Label> labels=labelElasticRepository.findByUserId(userId);
	 if(labels.isEmpty()) {
		throw new NullValueException("there is no any label for given user"); 
	 }
	 
	return labels.stream().map(labelStream->modelMapper.map(labelStream, LabelDTO.class)).collect(Collectors.toList());
	}
	
	/**
	 * 
	 * @param userId
	 * @param noteId
	 * @param labelId
	 * @throws LabelNotFoundException
	 * @throws NoteNotFoundException
	 * @throws UnAuthorizedException
	 */
	@Override
	public void removeLabelFromNote(String userId, String noteId, String labelId)
			throws LabelNotFoundException, NoteNotFoundException, UnAuthorizedException {

		//Optional<Label> optionalLabel = labelRepository.findByLabelId(labelId);
		
		Optional<Label> optionalLabel = labelElasticRepository.findByLabelId(labelId);
		if (!optionalLabel.isPresent()) {
			throw new LabelNotFoundException("the label with given id does not exist");
		}
       
		Label label=optionalLabel.get();
		
		//Optional<Note> optionalNote = noteRepository.findByNoteId(noteId);
		Optional<Note> optionalNote = noteElasticRepository.findByNoteId(noteId);
		if (!optionalNote.isPresent()) {
			throw new NoteNotFoundException("the note with given id does not exist");
		}
		
		Note note = optionalNote.get();

		if (!note.getUserId().equals(userId)) {
			throw new UnAuthorizedException("this particular note is not authorized for given user");
		}
		
		if(!label.getUserId().equals(userId)) {
			throw new UnAuthorizedException("this particular label is not authorized for given user");
		}

		for (int i = 0; i < note.getLabels().size(); i++) {
			if (note.getLabels().get(i).getLabelId().equals(labelId)) {
				note.getLabels().remove(i);
				noteRepository.save(note);
				noteElasticRepository.save(note);
			}
		}
	}
}
