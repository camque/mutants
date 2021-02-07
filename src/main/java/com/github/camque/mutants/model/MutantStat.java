package com.github.camque.mutants.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mutant_stat")
public class MutantStat {

	@Id
	private Integer id;

	@Column(name = "value_mutans")
	private Long valueMutans;

	@Column(name = "value_humans")
	private Long valueHumans;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getValueMutans() {
		return this.valueMutans;
	}

	public void setValueMutans(Long valueMutans) {
		this.valueMutans = valueMutans;
	}

	public Long getValueHumans() {
		return this.valueHumans;
	}

	public void setValueHumans(Long valueHumans) {
		this.valueHumans = valueHumans;
	}

}
