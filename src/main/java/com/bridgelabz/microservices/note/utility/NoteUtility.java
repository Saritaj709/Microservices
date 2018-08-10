package com.bridgelabz.microservices.note.utility;

import com.bridgelabz.microservices.note.exception.NoteCreationException;
import com.bridgelabz.microservices.note.exception.NullValueException;
import com.bridgelabz.microservices.note.model.CreateDTO;

public class NoteUtility {


	public static void validateNoteCreation(CreateDTO createDto) throws NoteCreationException, NullValueException {

		if (createDto.getDescription() == null && createDto.getTitle() == null) {
			throw new NoteCreationException("Both description and title cannot be null");
		}

		if (createDto.getLabels() == null) {
			throw new NoteCreationException("note label should not be null");
		}

		for (int i = 0; i < createDto.getLabels().size(); i++) {
			if (createDto.getLabels().get(i).getLabelName().equals(null)
					|| createDto.getLabels().get(i).getLabelName().length() == 0
					|| createDto.getLabels().get(i).getLabelName().trim().length() == 0) {
				throw new NullValueException("label name cannot be null,pls enter name for label");
			}
		}
	}

	public static void validateLabelCreation(String labelName) throws NullValueException {

		if (labelName == null || labelName.length() == 0 || labelName.trim().length() == 0) {
			throw new NullValueException("Label name cannot be null");
		}
	}
}
