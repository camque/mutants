package com.github.camque.mutants.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.camque.mutants.dto.StatResponse;

@RequestMapping("/stats")
public interface IStatRest {

	@GetMapping
	ResponseEntity<StatResponse> getStats();

}
