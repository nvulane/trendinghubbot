package org.trendinghub.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Source {
	
	@Value("${news.source}")
	private String[] newsSource;

	public String[] getSourceUrls() {
		String[] urls = new String[newsSource.length];
		for (int i = 0; i < urls.length; i++) {
			urls[i] = String.format("https://api.pnd.gs/v1/sources/%s/latest", newsSource[i]);
		}
		return urls;
	}
}
