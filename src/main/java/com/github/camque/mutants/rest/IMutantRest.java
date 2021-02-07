package com.github.camque.mutants.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.camque.mutants.dto.MutantRequest;
import com.github.camque.mutants.dto.MutantResponse;

@RequestMapping("/mutant")
public interface IMutantRest {

	/**
	 * Validate mutant
	 * @param request
	 * @return MutantResponse
	 */
	@PostMapping
	ResponseEntity<MutantResponse> isMutant( @RequestBody MutantRequest request);

}
