package org.trendinghub.bot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.trendinghub.repository.ArticleRepository;
import org.trendinghub.service.APIService;
import org.trendinghub.structure.Article;
import org.trendinghub.util.Task;
import org.trendinghub.util.TaskTimer;

@Component
public class Bot extends TelegramLongPollingBot {
	
	@Value("${telegram.bot.username}")
	private String username;
	@Value("${telegram.bot.token}")
	private String token;
	@Value("${telegram.bot.channel}")
	private String channel;
	
	@Autowired
	private APIService apiService;
	@Autowired
	private ArticleRepository articleRepository;
	
	public Bot() {
		startTrendNotifications();
	}

	@Override
	public void onUpdateReceived(Update update) {
		sendNotifications();
	}

	@Override
	public String getBotUsername() {
		return username;
	}

	@Override
	public String getBotToken() {
		return token;
	}
	private void startTrendNotifications() {
		TaskTimer.getInstance().startTaskExecutionEvery(new Task() {
			
			@Override
			public void execute() {
				sendNotifications();
			}
		}, 30);
	}

	private void sendNotifications() {
		List<Article> articles = apiService.getArticles();
		for (Article article : articles) {
			if (articleRepository.findByUniqueId(article.getUniqueId()) == null) {
				SendMessage message = new SendMessage();
				message.setChatId(channel);
				message.setText(article.getTagertUrl());
				try {
					execute(message);
					articleRepository.save(article);
				} catch (TelegramApiException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}
	}
}
