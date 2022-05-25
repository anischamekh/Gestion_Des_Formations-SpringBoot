package com.cni.projet.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cni.projet.entity.Participant;
import com.cni.projet.exception.ParticipantNotFoundException;
import com.cni.projet.repository.ParticipantRepository;

@Service
public class ParticipantService {
	
private final ParticipantRepository participantRepo;
	

    @Autowired
    public ParticipantService(ParticipantRepository participantRepo) {
        this.participantRepo = participantRepo;
    }


	public List<Participant> findAllParticipants() {
		return participantRepo.findAll();
	}
	
	public Participant findParticipantById(int id) {
		return participantRepo.findParticipantById(id)
				.orElseThrow(() -> new ParticipantNotFoundException("Participant with code " + id + " was not found"));
	}

	
	public Participant addParticipant(Participant participant) {
        return participantRepo.save(participant);
	}

}
