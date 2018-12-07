package org.trendinghub.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskTimer {
	
	private static TaskTimer instance;
	private  final ScheduledExecutorService scheduledService = Executors.newScheduledThreadPool(1);

	private TaskTimer() {
	}
	public static TaskTimer getInstance() {
		TaskTimer currentInstance;
		if (instance == null) {
			synchronized (TaskTimer.class) {
				if (instance == null) {
					instance = new TaskTimer();
				}
				currentInstance = instance;
			}
		} else {
			currentInstance = instance;
		}
		return currentInstance;
	}
	public void startTaskExecutionEvery(Task task, int hour, int min, int sec) {
		final Runnable taskWrapper = () -> {
			task.execute();
		};
		scheduledService.schedule(taskWrapper, min, TimeUnit.MINUTES);
	}
}
