package com.bridgelabz.microservices.note.model;

import java.io.Serializable;

public class CreateLabelDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String labelName;
	private String userId;

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "CreateLabelDTO [labelName=" + labelName + ", userId=" + userId + ", noteId=" + "]";
	}

}
