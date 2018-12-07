package org.trendinghub.service;

import java.util.ArrayList;
import java.util.List;

import org.trendinghub.structure.Article;

public class APIService {

	private static APIService instance;
	
	private APIService() {
	}
	public static APIService getInstance() {
		APIService currentInstance;
		if (instance == null) {
			synchronized (APIService.class) {
				if (instance == null) {
					instance = new APIService();
				}
				currentInstance = instance;
			}
		} else {
			currentInstance = instance;
		}
		return currentInstance;
	}
	public List<Article> getArticles() {
		List<Article> articles = new ArrayList<>();
		return null;
	}

}
