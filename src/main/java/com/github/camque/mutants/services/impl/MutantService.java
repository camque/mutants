package com.github.camque.mutants.services.impl;

import java.text.MessageFormat;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.camque.mutants.exception.ValidationException;
import com.github.camque.mutants.services.IMutantService;
import com.github.camque.mutants.services.IMutantStatService;
import com.github.camque.mutants.utils.StringUtils;

@Service
public class MutantService implements IMutantService {

	private static final Logger LOG = LogManager.getLogger(MutantService.class);

	private static final String SECUENCIA_SOPORTADA = "[ATCG]*";
	private static final int SIZE_MUTANT_CHAIN = 4;
	private static final int COUNT_MUTANT_CHAIN = 2;

	@Autowired
	private IMutantStatService mutantStatService;

	@Override
	public boolean isMutant(List<String> dna) throws ValidationException {
		boolean response = false;
		this.validateChains(dna);

		int count = 0;
		for (int i = 0; i < dna.size(); i++) {
			for (int j = 0; j < dna.size(); j++) {

				//Check row
				if ( j <= (dna.size() - SIZE_MUTANT_CHAIN) ) {
					if ( this.checkRow(dna, i, j) ) {
						count++;
					}
				}

				if ( count >= COUNT_MUTANT_CHAIN ) {
					response = true;
					break;
				}

				//Check col
				if ( i <= (dna.size() - SIZE_MUTANT_CHAIN) ) {
					if ( this.checkCol(dna, i, j) ) {
						count++;
					}
				}

				if ( count >= COUNT_MUTANT_CHAIN ) {
					response = true;
					break;
				}

				//Check Diagonal
				if ( j <= (dna.size() - SIZE_MUTANT_CHAIN) && i <= (dna.size() - SIZE_MUTANT_CHAIN) ) {
					if ( this.checkDiagonal(dna, i, j) ) {
						count++;
					}
				}

				if ( count >= COUNT_MUTANT_CHAIN ) {
					response = true;
					break;
				}

				//Check Transversal
				if ( j >= (SIZE_MUTANT_CHAIN - 1) && i <= (dna.size() - SIZE_MUTANT_CHAIN) ) {
					if ( this.checkTransversal(dna, i, j) ) {
						count++;
					}
				}

				if ( count >= COUNT_MUTANT_CHAIN ) {
					response = true;
					break;
				}

			}

			if ( response ) {
				break;
			}

		}

		this.mutantStatService.addStat(response);

		LOG.debug(MessageFormat.format("Mutant validate: {0}", response));
		return response;
	}

	/**
	 * Check the rows of the matrix
	 * @param dna
	 * @param i
	 * @param j
	 * @return boolean
	 */
	private boolean checkRow(List<String> dna, int i, int j) {
		boolean response = true;
		final char current = dna.get(i).charAt(j);
		char next;
		for (int k = 1; k < SIZE_MUTANT_CHAIN; k++ ) {
			next = dna.get(i).charAt(j+k);
			if ( current != next ) {
				response = false;
				break;
			}
		}

		return response;
	}

	/**
	 * Check the columns of the matrix
	 * @param dna
	 * @param i
	 * @param j
	 * @return boolean
	 */
	private boolean checkCol(List<String> dna, int i, int j) {
		boolean response = true;
		final char current = dna.get(i).charAt(j);
		char next;
		for (int k = 1; k < SIZE_MUTANT_CHAIN; k++ ) {
			next = dna.get(i+k).charAt(j);
			if ( current != next ) {
				response = false;
				break;
			}
		}

		return response;
	}

	/**
	 * Check the diagonals of the matrix
	 * @param dna
	 * @param i
	 * @param j
	 * @return boolean
	 */
	private boolean checkDiagonal(List<String> dna, int i, int j) {
		boolean response = true;
		final char current = dna.get(i).charAt(j);
		char next;
		for (int k = 1; k < SIZE_MUTANT_CHAIN; k++ ) {
			next = dna.get(i+k).charAt(j+k);
			if ( current != next ) {
				response = false;
				break;
			}
		}

		return response;
	}

	/**
	 * Check the cross-sections of the matrix
	 * @param dna
	 * @param i
	 * @param j
	 * @return boolean
	 */
	private boolean checkTransversal(List<String> dna, int i, int j) {
		boolean response = true;
		final char current = dna.get(i).charAt(j);
		char next;
		for (int k = 1; k < SIZE_MUTANT_CHAIN; k++ ) {
			next = dna.get(i+k).charAt(j-k);
			if ( current != next ) {
				response = false;
				break;
			}
		}

		return response;
	}

	/**
	 * Method to validate data quality
	 * @param dna
	 * @throws ValidationException
	 */
	private void validateChains(List<String> dna) throws ValidationException {
		//Null array
		if ( dna == null ) {
			throw new ValidationException("Uninitialized vector");
		}

		//Empty array
		if ( dna.size() == 0 ) {
			throw new ValidationException("Empty chains vector");
		}

		//Validate chars
		for (final String cadena : dna) {
			if ( !StringUtils.validateRegex(SECUENCIA_SOPORTADA, cadena) ) {
				throw new ValidationException("Unsupported character in DNA strand");
			}
		}

		//Validate size by row
		final int rowSize = dna.size();
		for (final String cadena : dna) {
			if ( cadena.length() != rowSize ) {
				throw new ValidationException("DNA chains are not the same size");
			}
		}
	}

}
