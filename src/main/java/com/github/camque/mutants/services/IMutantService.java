package com.github.camque.mutants.services;

import com.github.camque.mutants.exception.ValidationException;

public interface IMutantService {

	boolean isMutant(String[] dna) throws ValidationException;

}
