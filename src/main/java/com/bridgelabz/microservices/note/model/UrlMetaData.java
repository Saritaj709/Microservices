package com.bridgelabz.microservices.note.model;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName="urldb",type="url")
public class UrlMetaData{
	
	private String url;
	private String description;
	private String imageUrl;
	private String Keywords;
	
	public UrlMetaData() {
		
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String string) {
		this.imageUrl = string;
	}

	public String getKeywords() {
		return Keywords;
	}

	public void setKeywords(String keywords) {
		Keywords = keywords;
	}

	@Override
	public String toString() {
		return "UrlMetaData [url=" + url + ", description=" + description + ", imageUrl=" + imageUrl + ", Keywords="
				+ Keywords + "]";
	}
}
