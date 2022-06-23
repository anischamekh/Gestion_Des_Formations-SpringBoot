package com.cni.projet.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cni.projet.entity.Evaluation;
import com.cni.projet.entity.Formation;
import com.cni.projet.repository.EvaluationRepository;
import com.cni.projet.repository.FormationRepository;
import com.cni.projet.security.services.EvaluationService;

@Transactional
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EvaluationController {
	
	private final EvaluationService evaluationService;
	
	@Autowired
	private FormationRepository formationRepository;
	
	@Autowired
	private EvaluationRepository evaluationRepository;
	
	@Autowired
	public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

	@GetMapping("/allEvaluations")
	public ResponseEntity<List<Evaluation>> getAllEvaluations() {
		List<Evaluation> evaluations = evaluationService.findAllEvaluations();
		return new ResponseEntity<>(evaluations, HttpStatus.OK);
	}
	
	
	
	@PostMapping("/addEvaluation")
	public Evaluation addEvaluation(@RequestBody Evaluation evaluation) {
		Formation formation = formationRepository.findById(evaluation.getFormation().getId()).get();
		if (formation == null) return null;
		else {			
				evaluation.setFormation(formation);
				
				formation.getEvaluation().add(evaluation);
				
				evaluationRepository.save(evaluation);
				return evaluation;
				
			}
			
		}
			
}