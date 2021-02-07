package com.github.camque.mutants.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.github.camque.mutants.dto.MutantRequest;
import com.github.camque.mutants.dto.MutantResponse;

@SpringBootTest
public class MutantRestTest {

	@Autowired
	private IMutantRest service;

	@Test
	void contextLoads() {
		assertThat(this.service).isNotNull();
	}

	@Test
	void getStatsOne() {
		MutantRequest request = new MutantRequest();
		request.setDna( Arrays.asList("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG") );

		try {
			ResponseEntity<MutantResponse> response = this.service.isMutant( request );
			assertThat(response).isNotNull();
		} catch (Exception e) {
			fail("Exception");
		}
	}

	@Test
	void getStatsTwo() {
		MutantRequest request = new MutantRequest();
		request.setDna( Arrays.asList("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG") );

		try {
			ResponseEntity<MutantResponse> response = this.service.isMutant( request );
			assertThat(response).isNotNull();
			assertThat(response.getBody()).isInstanceOf(MutantResponse.class);
		} catch (Exception e) {
			fail("Exception");
		}
	}

	@Test
	void getStatsThree() {
		MutantRequest request = new MutantRequest();
		request.setDna( Arrays.asList("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG") );

		try {
			ResponseEntity<MutantResponse> response = this.service.isMutant( request );
			assertThat(response).isNotNull();
			assertThat(response.getBody()).isInstanceOf(MutantResponse.class);
			assertThat( response.getBody().isResponse() ).isTrue();
		} catch (Exception e) {
			fail("Exception");
		}
	}

	@Test
	void getStatsFour() {
		MutantRequest request = new MutantRequest();
		request.setDna( Arrays.asList("ATAAGA", "TAGTTC", "ATGTGT", "AGAAGG", "CGCCTA", "ACACTG") );

		try {
			ResponseEntity<MutantResponse> response = this.service.isMutant( request );
			assertThat(response).isNotNull();
			assertThat(response.getBody()).isInstanceOf(MutantResponse.class);
			assertThat( response.getBody().isResponse() ).isFalse();
		} catch (Exception e) {
			fail("Exception");
		}
	}

	@Test
	void getStatsFive() {
		MutantRequest request = new MutantRequest();
		request.setDna( Arrays.asList("ATXAGA", "TAGTTC", "ATGTGT", "AGAAGG", "CGCCTA", "ACACTG") );

		try {
			ResponseEntity<MutantResponse> response = this.service.isMutant( request );
			assertThat(response).isNotNull();
			assertThat(response.getBody()).isInstanceOf(MutantResponse.class);
		} catch (Exception e) {
			fail("Exception");
		}
	}

}
