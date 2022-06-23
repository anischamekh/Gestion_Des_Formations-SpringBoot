package com.cni.projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cni.projet.entity.Evaluation;

public interface EvaluationRepository extends JpaRepository<Evaluation, Integer> {

}
