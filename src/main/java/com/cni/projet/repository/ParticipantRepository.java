package com.cni.projet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cni.projet.entity.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Integer>  {
	 
	 Optional<Participant> findParticipantById(int id);

}
