package com.github.camque.mutants.services.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.github.camque.mutants.model.MutantStat;
import com.github.camque.mutants.repository.IMutantStatDao;
import com.github.camque.mutants.services.IMutantStatService;

@Service
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

	@PostConstruct
	private void init() {
		Optional<MutantStat> row = this.dao.findById(ID_ROW);
		if ( row.isPresent() ) {
			mutantStat = row.get();
			mutantCount.set( mutantStat.getValueMutans() );
			humanCount.set( mutantStat.getValueHumans() );
		}
		LOG.info("xyz");
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


}
