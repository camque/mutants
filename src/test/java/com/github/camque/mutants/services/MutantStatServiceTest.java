package com.github.camque.mutants.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.camque.mutants.services.impl.MutantStatService;

@SpringBootTest
public class MutantStatServiceTest {

	@Autowired
	private IMutantStatService service;

	@Test
	void contextLoads() {
		assertThat(this.service).isNotNull();
	}

	@Test
	void getStats() {
		try {
			Map<String, Object> response = this.service.getStats();
			assertThat(response).isNotNull();
		} catch (Exception e) {
			fail("Exception");
		}
	}

	@Test
	void getStatsTwo() {
		try {
			Map<String, Object> response = this.service.getStats();
			assertThat(response).isNotNull();
			assertThat(response).isNotEmpty();
		} catch (Exception e) {
			fail("Exception");
		}
	}

	@Test
	void getStatsThree() {
		try {
			Map<String, Object> response = this.service.getStats();
			assertThat(response).isNotNull();
			assertThat(response).isNotEmpty();
			assertThat( (Long) response.get(MutantStatService.MUTANT_KEY) ).isPositive();
			assertThat( (Long) response.get(MutantStatService.HUMAN_KEY) ).isPositive();
		} catch (Exception e) {
			fail("Exception");
		}
	}

	@Test
	void addStat() {
		try {
			this.service.addStat(true);
		} catch (Exception e) {
			fail("Exception");
		}
	}

	@Test
	void saveStats() {
		try {
			this.service.saveStats();
		} catch (Exception e) {
			fail("Exception");
		}
	}

}
