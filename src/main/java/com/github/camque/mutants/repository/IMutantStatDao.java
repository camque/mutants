package com.github.camque.mutants.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.camque.mutants.model.MutantStat;

public interface IMutantStatDao extends JpaRepository<MutantStat, Integer> {

}
