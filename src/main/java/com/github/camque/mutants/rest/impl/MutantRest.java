package com.github.camque.mutants.rest.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.github.camque.mutants.dto.MutantRequest;
import com.github.camque.mutants.dto.MutantResponse;
import com.github.camque.mutants.exception.ValidationException;
import com.github.camque.mutants.rest.IMutantRest;
import com.github.camque.mutants.services.IMutantService;

@RestController
public class MutantRest implements IMutantRest {

	private static final Logger LOG = LogManager.getLogger(MutantRest.class);

	@Autowired
	private IMutantService service;

	@Override
	public ResponseEntity<MutantResponse> isMutant( MutantRequest request) {
		LOG.debug("New request arrived");

		MutantResponse response;
		HttpStatus httpStatus;

		try {
			final boolean responseService = this.service.isMutant( request.getDna() );

			response = new MutantResponse("", responseService);
			httpStatus = responseService ? HttpStatus.OK : HttpStatus.FORBIDDEN;

		} catch (final ValidationException e) {
			LOG.error(e.getMessage());
			response = new MutantResponse(e.getMessage(), false);
			httpStatus = HttpStatus.BAD_REQUEST;
		}

		return ResponseEntity.status(httpStatus).body(response);
	}


}
