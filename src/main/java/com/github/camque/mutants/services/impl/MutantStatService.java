package com.github.camque.mutants.services.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.github.camque.mutants.model.MutantStat;
import com.github.camque.mutants.repository.IMutantStatDao;
import com.github.camque.mutants.services.IMutantStatService;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MutantStatService implements IMutantStatService {

	private static final Logger LOG = LogManager.getLogger(MutantStatService.class);

	public static final int ID_ROW = 1;
	public static final String MUTANT_KEY = "mutant";
	public static final String HUMAN_KEY = "human";
	public static final String RATIO_KEY = "ratio";

	private static AtomicLong mutantCount;
	private static AtomicLong humanCount;
	private static MutantStat mutantStat;

	@Autowired
	private IMutantStatDao dao;

	public MutantStatService() {
		super();
		mutantCount = new AtomicLong(0);
		humanCount = new AtomicLong(0);
	}

	/**
	 * Start bean get stats
	 */
	@PostConstruct
	private void init() {
		LOG.info("Init bean");
		Optional<MutantStat> row = this.dao.findById(ID_ROW);
		if ( row.isPresent() ) {
			mutantStat = row.get();
			mutantCount.set( mutantStat.getValueMutans() );
			humanCount.set( mutantStat.getValueHumans() );
		}
	}

	/**
	 * Before destroy bean save stats
	 */
	@PreDestroy
	private void end() {
		LOG.info("Destroying bean");
		this.saveStats();
	}

	@Override
	public Map<String, Object> getStats() {
		Map<String, Object> response = new HashMap<>();

		Long mc = mutantCount.get();
		Long hc = humanCount.get();

		response.put(MUTANT_KEY, mc);
		response.put(HUMAN_KEY, hc);
		response.put(RATIO_KEY, hc.longValue()!=0 ? (Double) (mc.doubleValue() / hc.doubleValue() ) : 0 );

		return response;
	}

	@Async
	@Override
	public void addStat(boolean isMutant) {
		if ( isMutant ) {
			mutantCount.incrementAndGet();
		}
		else {
			humanCount.incrementAndGet();
		}
	}

	@Override
	public void saveStats() {
		LOG.info("Saving stats");
		boolean mustSave = false;

		if ( mutantCount.get() != mutantStat.getValueMutans().longValue() ) {
			mutantStat.setValueMutans( mutantCount.get() );
			mustSave = true;
		}

		if ( humanCount.get() != mutantStat.getValueHumans().longValue() ) {
			mutantStat.setValueHumans( humanCount.get() );
			mustSave = true;
		}

		if ( mustSave ) {
			this.dao.save(mutantStat);
			LOG.info("Stats saved");
		}
		else {
			LOG.info("No need to save statistics.");
		}

	}

}
