package com.github.camque.mutants.services;

import java.util.Map;

public interface IMutantStatService {

	/**
	 * Return de current stats
	 * @return Map
	 */
	Map<String, Object> getStats();

	/**
	 * Add a row stat
	 * @param isMutant
	 */
	void addStat(boolean isMutant);

	/**
	 * Save acumulate stats
	 */
	void saveStats();

}
