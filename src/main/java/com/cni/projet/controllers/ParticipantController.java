package com.cni.projet.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cni.projet.entity.Formation;
import com.cni.projet.entity.Participant;
import com.cni.projet.entity.User;
import com.cni.projet.repository.FormationRepository;
import com.cni.projet.repository.ParticipantRepository;
import com.cni.projet.security.services.FormationService;
import com.cni.projet.security.services.ParticipantService;

@Transactional
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ParticipantController {

	private final ParticipantService participantService;
	
	@Autowired
	private FormationRepository formationRepository;
	
	@Autowired
	private ParticipantRepository participantRepository;
	
	@Autowired
	private FormationService formationService;
	
	@Autowired
	public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }
	
	@GetMapping("/allParticipants")
	public ResponseEntity<List<Participant>> getAllParticipants() {
		List<Participant> participants = participantService.findAllParticipants();
		return new ResponseEntity<>(participants, HttpStatus.OK);
	}
	
	@GetMapping("/findParticipant/{id}")
    public ResponseEntity<Participant> getParticipantById(@PathVariable("id") int id) {
		Participant participant = participantService.findParticipantById(id);
        return new ResponseEntity<>(participant, HttpStatus.OK);
    }
	
    @PostMapping("/addParticipant")
	public Participant addParticipant(@RequestBody Participant participant) {
    	Formation formation = formationRepository.findById(participant.getFormation().getId()).get();
		if (formation == null) return null;
		else {
			participant.setFormation(formation);
			formation.getParticipants().add(participant);
			participantRepository.save(participant);
			return participant;
		}
	}
	
	
	/*
	@PostMapping("/addParticipant/{idF}")
	public Participant addParticipant(@PathVariable int idF,@RequestBody Participant participant) {
		//Formation formation = formationRepository.findById(idF).get();
		Formation formation = formationService.findFormationById(idF);
		if (formation == null) return null;
		else {
			participant.setFormation(formation);
			formation.getParticipants().add(participant);
			participantRepository.save(participant);
			return participant;
		}
	}
	*/
	
}
