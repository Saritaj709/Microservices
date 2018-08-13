package com.bridgelabz.microservices.note.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/*@Document(collection = "notes")
@Service*/
@Document(indexName="notesdb",type="note")
public class Note {

	@Id
 	private String noteId;
	private String title;
	private List<String> description;
	private Date createdAt;
	private String userId;
	private Date lastModifiedAt;
	private String color="#fff";
	private Date reminder=null;
	private boolean trashed;
	private boolean archieve;
	private boolean pin;
	private List<Label> Labels;
	private List<UrlMetaData> metaData;  

	public Note() {
		super();
	}

	public String getNoteId() {
		return noteId;
	}

	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getDescription() {
		return description;
	}

	public void setDescription(List<String> description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getLastModifiedAt() {
		return lastModifiedAt;
	}

	public void setLastModifiedAt(Date lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String Color) {
		this.color = Color;
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

	public void setTrashed(boolean trashed) {
		this.trashed = trashed;
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

	public void setPin(boolean pinned) {
		this.pin = pinned;
	}

	public List<Label> getLabels() {
		return Labels;
	}

	public void setLabels(List<Label> labels) {
		Labels = labels;
	}

	public List<UrlMetaData> getMetaData() {
		return metaData;
	}

	public void setMetaData(List<UrlMetaData> metaData) {
		this.metaData = metaData;
	}

	@Override
	public String toString() {
		return "Note [noteId=" + noteId + ", title=" + title + ", description=" + description + ", createdAt="
				+ createdAt + ", userId=" + userId + ", lastModifiedAt=" + lastModifiedAt + ", color=" + color
				+ ", reminder=" + reminder + ", trashed=" + trashed + ", archieve=" + archieve + ", pin=" + pin
				+ ", Labels=" + Labels + ", metaData=" + metaData + "]";
	}

}
