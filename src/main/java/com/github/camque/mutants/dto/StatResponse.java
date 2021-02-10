package com.github.camque.mutants.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"count_mutant_dna",
"count_human_dna",
"ratio"
})
public class StatResponse implements Serializable {

	private static final long serialVersionUID = 6391070397528489934L;

	@JsonProperty("count_mutant_dna")
	private Long countMutantDna;

	@JsonProperty("count_human_dna")
	private Long countHumanDna;

	@JsonProperty("ratio")
	private Double ratio;

	public StatResponse(Long countMutantDna, Long countHumanDna, Double ratio) {
		super();
		this.countMutantDna = countMutantDna;
		this.countHumanDna = countHumanDna;
		this.ratio = ratio;
	}

	public Long getCountMutantDna() {
		return this.countMutantDna;
	}

	public void setCountMutantDna(Long countMutantDna) {
		this.countMutantDna = countMutantDna;
	}

	public Long getCountHumanDna() {
		return this.countHumanDna;
	}

	public void setCountHumanDna(Long countHumanDna) {
		this.countHumanDna = countHumanDna;
	}

	public Double getRatio() {
		return this.ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

}
