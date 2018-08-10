package com.bridgelabz.microservices.note.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.stereotype.Service;

/*@Document(collection="labels")
@Service*/
@Document(indexName="labeldb",type="label")
public class Label {
	
	@Id
    String labelId;
    String labelName;
    String userId;
	
	public Label() {
		super();
	}

	public String getLabelId() {
		return labelId;
	}

	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}

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
		return "LabelDTO [labelId=" + labelId + ", labelName=" + labelName + ", userId=" + userId
				+ "]";
	}
	
	
}
