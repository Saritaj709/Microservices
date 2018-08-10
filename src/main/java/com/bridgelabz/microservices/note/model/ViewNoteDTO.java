package com.bridgelabz.microservices.note.model;

import java.util.Date;
import java.util.List;

public class ViewNoteDTO {
	
	String noteId;
	String title;
	String description;
	Date createdAt;
	Date lastModifiedAt;
	List<Label> labels;
	String color;
	Date reminder;
	boolean trashed;
	boolean archieve;
	boolean pin;
	List<UrlMetaData> metaData;

	public ViewNoteDTO() {
		super();
	}

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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getLastModifiedAt() {
		return lastModifiedAt;
	}

	public void setLastModifiedAt(Date lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}

	public List<Label> getLabels() {
		return labels;
	}

	public void setLabels(List<Label> labels) {
		this.labels = labels;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Date getReminder() {
		return reminder;
	}

	public void setReminder(Date reminder) {
		this.reminder = reminder;
	}

	public boolean isTrashed() {
		return trashed;
	}

	public void setTrashed(boolean isTrashed) {
		this.trashed = isTrashed;
	}

	public boolean isArchieve() {
		return archieve;
	}

	public void setArchieve(boolean archieve) {
		this.archieve = archieve;
	}

	public boolean isPin() {
		return pin;
	}

	public void setPin(boolean pin) {
		this.pin = pin;
	}

	
	public String getNoteId() {
		return noteId;
	}

	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}

	public List<UrlMetaData> getMetaData() {
		return metaData;
	}

	public void setMetaData(List<UrlMetaData> metaData) {
		this.metaData = metaData;
	}

	@Override
	public String toString() {
		return "ViewNoteDTO [noteId=" + noteId + ", title=" + title + ", description=" + description + ", createdAt="
				+ createdAt + ", lastModifiedAt=" + lastModifiedAt + ", labels=" + labels + ", color=" + color
				+ ", reminder=" + reminder + ", trashed=" + trashed + ", archieve=" + archieve + ", pin=" + pin
				+ ", urlMetaData=" + metaData + "]";
	}

}
