package org.trendinghub.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trendinghub.structure.Article;

@Service
public class APIService {

	private static APIService instance;
	
	@Autowired
	private Source source;
	
	private CloseableHttpClient httpClient;
	
	private APIService() {
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		httpClient = HttpClients.custom().setConnectionManager(connectionManager).build();
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
		String[] urls = source.getSourceUrls();
		Thread[] threads = new Thread[urls.length];
		for (int i = 0; i < threads.length; i++) {
			HttpGet request = new HttpGet(urls[i]);
			threads[i] = new Thread(() -> {
				try (CloseableHttpResponse response = httpClient.execute(request)){
					HttpEntity entity = response.getEntity();
					String responseBody = EntityUtils.toString(entity, "UTF-8");
					articles.addAll(jsonToArticles(new JSONArray(responseBody)));
				} catch (Exception e) {
					// TODO: handle exception
				}
			});
			threads[i].start();
		}
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return articles;
	}
	private Collection<? extends Article> jsonToArticles(JSONArray jsonArray) {
		List<Article> articles = new ArrayList<>();
		jsonArray.forEach(item -> {
			if (item instanceof JSONObject) {
				JSONObject object = (JSONObject) item;
				articles.add(new Article(object.optString("uniqueid"), object.optString("title"), object.getJSONObject("source").optString("sourceUrl"), object.getJSONObject("source").optString("targetUrl")));
			}
		});
		return articles;
	}
}
