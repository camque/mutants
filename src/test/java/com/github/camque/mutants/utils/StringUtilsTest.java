package com.github.camque.mutants.utils;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.github.camque.mutants.utils.StringUtils;

public class StringUtilsTest {

	@Test
	void patternAcepted() {
		try {
			boolean response = StringUtils.validateRegex("[ATCG]*", "ATGCGA");
			assertThat(response).isTrue();

		} catch (Exception e) {
			fail("Exception");
		}
	}

	@Test
	void patternDeny() {
		try {
			boolean response = StringUtils.validateRegex("[ATCG]*", "ATGXGA");
			assertThat(response).isFalse();

		} catch (Exception e) {
			fail("Exception");
		}
	}

}
