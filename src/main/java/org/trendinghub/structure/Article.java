package org.trendinghub.structure;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="articles")
public class Article {
	
	@Id
	private String uniqueId;
	private String title;
	private String sourceUrl;
	private String tagertUrl;
	
	public Article(String uniqueId, String title, String sourceUrl, String tagertUrl) {
		this.uniqueId = uniqueId;
		this.title = title;
		this.sourceUrl = sourceUrl;
		this.tagertUrl = tagertUrl;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSourceUrl() {
		return sourceUrl;
	}
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	public String getTagertUrl() {
		return tagertUrl;
	}
	public void setTagertUrl(String tagertUrl) {
		this.tagertUrl = tagertUrl;
	}
}
