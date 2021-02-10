package com.github.camque.mutants.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.camque.mutants.model.MutantStat;

@SpringBootTest
public class MutantStatDaoTest {

	@Autowired
	private IMutantStatDao service;

	@Test
	void contextLoads() {
		assertThat(this.service).isNotNull();
	}

	@Test
	void simpleArray() {
		try {
			Optional<MutantStat> row = this.service.findById(1);

			if ( row.isPresent() ) {
				MutantStat mutantStat = row.get();
				assertThat( mutantStat.getValueMutans() ).isPositive();
				assertThat( mutantStat.getValueHumans() ).isPositive();
			}
			else {
				fail("Not register in database");
			}

		} catch (Exception e) {
			fail("Exception");
		}
	}

}
