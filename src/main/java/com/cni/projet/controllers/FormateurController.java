package com.cni.projet.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cni.projet.entity.ERole;
import com.cni.projet.entity.Role;
import com.cni.projet.entity.User;
import com.cni.projet.payload.request.SignupRequest;
import com.cni.projet.payload.response.MessageResponse;
import com.cni.projet.repository.RoleRepository;
import com.cni.projet.repository.UserRepository;
import com.cni.projet.security.services.FormateurService;

@Transactional
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FormateurController {

	private final FormateurService formateurService;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	public FormateurController(FormateurService formateurService) {
        this.formateurService = formateurService;
    }

	@GetMapping("/allformateurs")
	public ResponseEntity<List<User>> getAllFormateurs() {
		List<User> users = formateurService.findAllFormateurs();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@GetMapping("/findformateur/{id}")
    public ResponseEntity<User> getFormateursById(@PathVariable("id") Long id) {
		User formateur = formateurService.findFormateurById(id);
        return new ResponseEntity<>(formateur, HttpStatus.OK);
    }

    @PostMapping("/addformateur")
    public ResponseEntity<?> addFormateur(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 signUpRequest.getPassword(),
							 signUpRequest.getFirstName(),
							 signUpRequest.getLastName());

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role formateurRole = roleRepository.findByName(ERole.ROLE_FORMATEUR)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(formateurRole);
		} 
		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
    
    
    
    @PutMapping("/updateformateur/{id}")
    public ResponseEntity<User> updateFormateur(@PathVariable("id") Long id,@RequestBody User formateur) {
    	Optional<User> formateurData = Optional.of(formateurService.findFormateurById(id));
    	if (formateurData.isPresent()) {
			User _formateur = formateurData.get();
			_formateur.setUsername(formateur.getUsername());
			_formateur.setEmail(formateur.getEmail());
			_formateur.setPassword(formateur.getPassword());
			_formateur.setFirstName(formateur.getFirstName());
			_formateur.setLastName(formateur.getLastName());
			return new ResponseEntity<>(formateurService.updateFormateur(_formateur), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }

    @DeleteMapping("/deleteformateur/{id}")
    public ResponseEntity<?> deleteFormateur(@PathVariable("id") Long id) {
    	formateurService.deleteFormateur(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
    @PatchMapping("/resetPassword/{id}")
    public User resetPassword(@PathVariable Long id,@RequestBody String ooldpw,@RequestBody String newpw) {
    	Optional<User> formateurData = Optional.of(formateurService.findFormateurById(id));
    	if (formateurData.isPresent()) {
			User formateur = formateurData.get();
			if (formateur.getPassword()!=null) {
				formateur.setPassword(formateur.getPassword());
			}
			
    	}
		//return formateurService.resetPassword();
		return null;

	}
   
}
    
