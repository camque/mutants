package com.github.camque.mutants.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.github.camque.mutants.exception.ValidationException;
import com.github.camque.mutants.services.IMutantService;
import com.github.camque.mutants.utils.StringUtils;

@Service
public class MutantService implements IMutantService {

	private static final Logger LOG = LogManager.getLogger(MutantService.class);

	private static final String SECUENCIA_SOPORTADA = "[ATCG]*";

	@Override
	public boolean isMutant(String[] dna) throws ValidationException {
		this.validateChains(dna);

		LOG.info("New request");
		return false;
	}

	/**
	 * Method to validate data quality
	 * @param dna
	 * @throws ValidationException
	 */
	private void validateChains(String[] dna) throws ValidationException {
		//Null array
		if ( dna == null ) {
			throw new ValidationException("Uninitialized vector");
		}

		//Empty array
		if ( dna.length == 0 ) {
			throw new ValidationException("Empty chains vector");
		}

		//Validate chars
		for (final String cadena : dna) {
			if ( !StringUtils.validateRegex(SECUENCIA_SOPORTADA, cadena) ) {
				throw new ValidationException("Unsupported character in DNA strand");
			}
		}

		//Validate size by row
		final int rowSize = dna.length;
		for (final String cadena : dna) {
			if ( cadena.length() != rowSize ) {
				throw new ValidationException("DNA chains are not the same size");
			}
		}
	}

}
