package com.github.camque.mutants.rest.impl;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.github.camque.mutants.dto.StatResponse;
import com.github.camque.mutants.rest.IStatRest;
import com.github.camque.mutants.services.IMutantStatService;
import com.github.camque.mutants.services.impl.MutantStatService;

@RestController
public class StatRest implements IStatRest {

	private static final Logger LOG = LogManager.getLogger(StatRest.class);

	@Autowired
	private IMutantStatService service;

	@Override
	public ResponseEntity<StatResponse> getStats() {
		LOG.debug("New request arrived");

		Map<String, Object> stats = this.service.getStats();
		StatResponse response = new StatResponse(
				(Long) stats.get(MutantStatService.MUTANT_KEY),
				(Long) stats.get(MutantStatService.HUMAN_KEY),
				(Double) stats.get(MutantStatService.RATIO_KEY));

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
