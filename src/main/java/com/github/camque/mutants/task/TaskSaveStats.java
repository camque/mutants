package com.github.camque.mutants.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.github.camque.mutants.services.IMutantStatService;

@Component
public class TaskSaveStats {

	private static final Logger LOG = LogManager.getLogger(TaskSaveStats.class);

	@Autowired
	private IMutantStatService mutantStatService;

	@Scheduled(fixedRate = 120000, initialDelay = 60000)
	public void saveStats() {
		LOG.info("Executing job");
		this.mutantStatService.saveStats();
	}

}
