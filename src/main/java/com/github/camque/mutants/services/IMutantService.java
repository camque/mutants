package com.github.camque.mutants.services;

import java.util.List;

import com.github.camque.mutants.exception.ValidationException;

public interface IMutantService {

	boolean isMutant(List<String> dna) throws ValidationException;

}
