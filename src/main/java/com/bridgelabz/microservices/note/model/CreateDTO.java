package com.bridgelabz.microservices.note.model;

import java.sql.Date;
import java.util.List;

public class CreateDTO {

	private String title;
	private String description;

	private List<Label> labels;
	private String color;
	private Date reminder;
	private boolean archieve;
	private boolean pin;

	public CreateDTO() {
		
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

	@Override
	public String toString() {
		return "CreateDTO [title=" + title + ", description=" + description + ", labels=" + labels + ", color=" + color
				+ ", reminder=" + reminder + ", archieve=" + archieve + ", pin=" + pin + "]";
	}

}
