package com.cni.projet.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cni.projet.entity.Formation;
import com.cni.projet.entity.Status;
import com.cni.projet.entity.Theme;
import com.cni.projet.entity.User;
import com.cni.projet.repository.FormateurRepository;
import com.cni.projet.repository.FormationRepository;
import com.cni.projet.repository.ThemeRepository;
import com.cni.projet.security.services.FormationService;

@Transactional
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FormationController {


	  @Autowired
	  private FormateurRepository formateurRepository;
	  @Autowired
	  private ThemeRepository themeRepository;
	  @Autowired
	  private FormationRepository formationRepository;
	  @Autowired
	  private final FormationService formationService;
	  		
		@Autowired
		public FormationController(FormationService formationService) {
	        this.formationService = formationService;
	    }
	  	  
		@PostMapping("/addFormation")
		public Formation addFormation(@RequestBody Formation formation) {
			Theme theme = themeRepository.findById(formation.getTheme().getId()).get();
			User formateur = formateurRepository.findById(formation.getUser().getId()).get();
			if (theme == null || formateur == null) return null;
			else {			
				LocalDate localDate = LocalDate.now();
				LocalDate dd = formation.getDateDebut();
				LocalDate df = formation.getDateFin();
				if (df.isBefore(dd)) return null; 
				else {
					
					if(localDate.isBefore(dd)) {
						formation.setEtat(Status.PLANIFIER);
					}else if ((localDate.isAfter(dd) && localDate.isBefore(df)) || (localDate.isEqual(dd) && localDate.isBefore(df))) {
						formation.setEtat(Status.EN_COURS);
					}else if (localDate.isAfter(df)) {
						formation.setEtat(Status.REALISER);
					}
					formation.setTheme(theme);
					formation.setUser(formateur);
					formateur.getFormations().add(formation);
					theme.getFormations().add(formation);
					formationRepository.save(formation);
					formateurRepository.save(formateur);
					themeRepository.save(theme);
					return formation;
					
				}
				
			}
				
		}
	  
		
		@GetMapping("/allFormations")
		public ResponseEntity<List<Formation>> getAllFormation() {
			List<Formation> formations = formationService.findAllFormations();
			return new ResponseEntity<>(formations, HttpStatus.OK);
		}
		
		@GetMapping("/findFormation/{id}")
	    public ResponseEntity<Formation> getFormationById(@PathVariable("id") int id) {
			Formation formation = formationService.findFormationById(id);
	        return new ResponseEntity<>(formation, HttpStatus.OK);
	    }
		
	    @PutMapping("/updateFormation/{id}")
	    public ResponseEntity<Formation> updateFormation(@PathVariable("id") int id,@RequestBody Formation formation) {
	    	Optional<Formation> formationData = Optional.of(formationService.findFormationById(id));
	    	if (formationData.isPresent()) {
	    		Formation _formation = formationData.get();
				_formation.setTitre(formation.getTitre());
				_formation.setDescription(formation.getDescription());
				_formation.setDateDebut(formation.getDateDebut());
				_formation.setDateFin(formation.getDateFin());
				_formation.setDuree(formation.getDuree());
				_formation.setLien(formation.getLien());
				_formation.setEtat(formation.getEtat());
				_formation.setUser(formation.getUser());
				_formation.setTheme(formation.getTheme());
				LocalDate localDate = LocalDate.now();
				LocalDate dd = formation.getDateDebut();
				LocalDate df = formation.getDateFin();
				if (df.isBefore(dd)) return null; 
				else {
					
					if(localDate.isBefore(dd)) {
						formation.setEtat(Status.PLANIFIER);
					}else if ((localDate.isAfter(dd) && localDate.isBefore(df)) || (localDate.isEqual(dd) && localDate.isBefore(df))) {
						formation.setEtat(Status.EN_COURS);
					}else if (localDate.isAfter(df)) {
						formation.setEtat(Status.REALISER);
					}
				}
				return new ResponseEntity<>(formationService.updateFormation(_formation), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

	    }

	    
	    @DeleteMapping("/deleteFormation/{id}")
	    public ResponseEntity<?> deleteFormation(@PathVariable("id") int id) {
	    	formationService.deleteFormation(id);
	        return new ResponseEntity<>(HttpStatus.OK);
	    }
}