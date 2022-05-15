package com.cni.projet.security.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cni.projet.entity.Formation;
import com.cni.projet.exception.FormationNotFoundException;
import com.cni.projet.repository.FormationRepository;

@Service
public class FormationService {

private final FormationRepository formationRepository;
	

    @Autowired
    public FormationService(FormationRepository formationRepository) {
        this.formationRepository = formationRepository;
    }


     public List<Formation> findAllFormations() {
		return formationRepository.findAll();
	}


	
	public Formation findFormationById(int id) {
		return formationRepository.findFormationById(id)
				.orElseThrow(() -> new FormationNotFoundException("Formation with id " + id + " was not found"));
	}

	
	public Formation addFormation(Formation formation) {
        return formationRepository.save(formation);
	}
	


	public Formation updateFormation(Formation formation) {
		return formationRepository.save(formation);
	}

	public void deleteFormation(int id){
		formationRepository.deleteFormationById(id);
    }

}
