package com.github.camque.mutants.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.camque.mutants.exception.ValidationException;

@SpringBootTest
public class MutantServiceTest {

	@Autowired
	private IMutantService service;

	@Test
	void contextLoads() {
		assertThat(this.service).isNotNull();
	}

	@Test
	void simpleArray() {
		List<String> dna = Arrays.asList("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG");

		try {
			boolean response = this.service.isMutant(dna);
			assertThat(response).isTrue();

		} catch (ValidationException e) {
			fail("Exception");
		}
	}

	@Test
	void sameRow() {
		List<String> dna = Arrays.asList("AAAAGAAAACGA", "CCGTTCCAGTGC", "TTATGTTTATGT", "AGAAGGAGAAGG", "CCCCTACCCCTA", "TCACTGTCACTG", "ATGCGAATGCGA", "CAGTGCCAGTGC", "TTATGTTTATGT", "AGAAGGAGAAGG", "CCCCTACCCCTA", "TCACTGTCACTG");

		try {
			boolean response = this.service.isMutant(dna);
			assertThat(response).isTrue();

		} catch (ValidationException e) {
			fail("Exception");
		}
	}

	@Test
	void sameCol() {
		List<String> dna = Arrays.asList("ACAAGACAACGA", "ACGTTCCAGTCC", "ATATGTTTATGT", "AGAAGGAGAAGG", "CTCCTACGCCTA", "ACACTGTCACTG", "ATGCGAATGCGA", "AAGTGCCAGTGC", "ATATGTTTATGT", "GGAAGGAGAAGG", "CCCCTACCCCTA", "TCACTGTCACTG");

		try {
			boolean response = this.service.isMutant(dna);
			assertThat(response).isTrue();

		} catch (ValidationException e) {
			fail("Exception");
		}
	}

	@Test
	void colandDiagonal() {
		List<String> dna = Arrays.asList("AAAAGACAACGA", "TAGTTCCAGTCC", "ATATGTTTATGT", "AGAAGGAGAAGG", "CGCCTACGCCTA", "ACACTGTCACTG", "ATGCGAATGCGA", "AAGTGCCAGTGC", "ATATGTTTATGT", "GGAAGGAGAAGG", "CCCCTACCCCTA", "TCACTGTCACTG");

		try {
			boolean response = this.service.isMutant(dna);
			assertThat(response).isTrue();

		} catch (ValidationException e) {
			fail("Exception");
		}
	}

	@Test
	void simpleHuman() {
		List<String> dna = Arrays.asList("ATAAGA", "TAGTTC", "ATGTGT", "AGAAGG", "CGCCTA", "ACACTG");

		try {
			boolean response = this.service.isMutant(dna);
			assertThat(response).isFalse();

		} catch (ValidationException e) {
			fail("Exception");
		}
	}

	@Test
	void simpleHumanTwo() {
		List<String> dna = Arrays.asList("ATAAGA", "TAGTTC", "ATGTGT", "AGAAGG", "CGCCTA", "AAACTG");

		try {
			boolean response = this.service.isMutant(dna);
			assertThat(response).isFalse();

		} catch (ValidationException e) {
			fail("Exception");
		}
	}

}
