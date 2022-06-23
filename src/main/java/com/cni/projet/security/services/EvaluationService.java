package com.cni.projet.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cni.projet.entity.Evaluation;
import com.cni.projet.repository.EvaluationRepository;

@Service
public class EvaluationService {

private final EvaluationRepository evaluationRepo;
	

    @Autowired
    public EvaluationService(EvaluationRepository evaluationRepo) {
        this.evaluationRepo = evaluationRepo;
    }


	public List<Evaluation> findAllEvaluations() {
		return evaluationRepo.findAll();
	}

	
	public Evaluation addEvaluation(Evaluation evaluation) {
        return evaluationRepo.save(evaluation);
	}
}
