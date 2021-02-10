package com.github.camque.mutants.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.github.camque.mutants.dto.StatResponse;

@SpringBootTest
public class StatRestTest {

	@Autowired
	private IStatRest service;

	@Test
	void contextLoads() {
		assertThat(this.service).isNotNull();
	}

	@Test
	void getStatsOne() {
		try {
			ResponseEntity<StatResponse> response = this.service.getStats();
			assertThat(response).isNotNull();
		} catch (Exception e) {
			fail("Exception");
		}
	}

	@Test
	void getStatsTwo() {
		try {
			ResponseEntity<StatResponse> response = this.service.getStats();
			assertThat(response).isNotNull();
			assertThat(response.getBody()).isInstanceOf(StatResponse.class);
		} catch (Exception e) {
			fail("Exception");
		}
	}

	@Test
	void getStatsThree() {
		try {
			ResponseEntity<StatResponse> response = this.service.getStats();
			assertThat(response).isNotNull();
			assertThat(response.getBody()).isInstanceOf(StatResponse.class);
			assertThat( response.getBody().getCountHumanDna() ).isPositive();
			assertThat( response.getBody().getCountMutantDna() ).isPositive();
		} catch (Exception e) {
			fail("Exception");
		}
	}

}
