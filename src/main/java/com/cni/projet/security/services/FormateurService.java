package com.cni.projet.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cni.projet.exception.FormateurNotFoundException;
import com.cni.projet.entity.User;
import com.cni.projet.repository.FormateurRepository;

@Service
public class FormateurService {

	private final FormateurRepository formRepo;
	

    @Autowired
    public FormateurService(FormateurRepository formRepo) {
        this.formRepo = formRepo;
    }


	public List<User> findAllFormateurs() {
		return formRepo.findAll();
	}


	
	public User findFormateurById(Long id) {
		return formRepo.findFormateurById(id)
				.orElseThrow(() -> new FormateurNotFoundException("Formateur with code " + id + " was not found"));
	}

	
	public User addFormateur(User formateur) {
        return formRepo.save(formateur);
	}
	


	public User updateFormateur(User formateur) {
		return formRepo.save(formateur);
	}


	public void deleteFormateur(Long id) {
		formRepo.deleteFormateurById(id);
	}


	public Object resetPassword(User _formateur) {
		return formRepo.save(_formateur);
	}

}
