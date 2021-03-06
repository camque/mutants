package com.github.camque.mutants.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"dna"
})
public class MutantRequest implements Serializable {

	private static final long serialVersionUID = 823295825840350915L;

	@JsonProperty("dna")
	private List<String> dna;

	public List<String> getDna() {
		return this.dna;
	}

	public void setDna(List<String> dna) {
		this.dna = dna;
	}

}
