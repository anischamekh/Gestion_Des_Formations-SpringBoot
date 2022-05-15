package com.cni.projet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cni.projet.entity.Formation;

public interface FormationRepository  extends JpaRepository<Formation, Integer> {
	
	void deleteFormationById(int id);
	 
	Optional<Formation> findFormationById(int id);


}
