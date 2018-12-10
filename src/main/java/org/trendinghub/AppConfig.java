package org.trendinghub;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.trendinghub.service.APIService;

@Configuration
public class AppConfig {

	@Bean
	public APIService apiService() {
		return APIService.getInstance();
	}
}
