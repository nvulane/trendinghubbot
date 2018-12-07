package org.trendinghub.bot;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.trendinghub.service.APIService;
import org.trendinghub.structure.Article;
import org.trendinghub.util.Task;
import org.trendinghub.util.TaskTimer;

public class Bot extends TelegramLongPollingBot {
	
	@Value(value="telegram.username")
	private String username;
	@Value(value="telegram.token")
	private String token;
	
	public Bot() {
		startTrendNotifications();
	}

	@Override
	public void onUpdateReceived(Update update) {
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
		}, 0,0,0);
	}

	private void sendNotifications() {
		List<Article> articles = APIService.getInstance().getArticles();
	}
}
