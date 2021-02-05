package com.github.camque.mutants.utils;

import java.util.regex.Pattern;

public class StringUtils {

	public static boolean validateRegex(String pattern, String value) {
		return Pattern.matches(pattern, value);
	}

}
