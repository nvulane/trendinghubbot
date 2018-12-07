package org.trendinghub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class TrendinghubbotApplication {

	public static void main(String[] args) {
		ApiContextInitializer.init();
		SpringApplication.run(TrendinghubbotApplication.class, args);
	}
}
