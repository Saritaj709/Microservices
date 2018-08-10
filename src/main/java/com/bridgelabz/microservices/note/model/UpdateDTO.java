package com.bridgelabz.microservices.note.model;

import java.util.List;

public class UpdateDTO {

	String title;
	String description;
	String noteId;
    List<Label> labels;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Label> getLabels() {
		return labels;
	}

	public void setLabels(List<Label> labels) {
		this.labels = labels;
	}

	public String getNoteId() {
		return noteId;
	}

	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}

	@Override
	public String toString() {
		return "UpdateDTO [title=" + title + ", description=" + description + ", noteId="
				+ noteId + ", labels=" + labels + "]";
	}

}
