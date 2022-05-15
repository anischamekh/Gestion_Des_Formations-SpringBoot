package com.cni.projet.controllers;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.cni.projet.entity.Theme;
import com.cni.projet.security.services.ThemeService;


@Transactional
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ThemeController {

	
	private final ThemeService themeService;
	
	@Autowired
	public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

	@GetMapping("/allthemes")
	public ResponseEntity<List<Theme>> getAllThemes() {
		List<Theme> themes = themeService.findAllThemes();
		return new ResponseEntity<>(themes, HttpStatus.OK);
	}
	
	@GetMapping("/findtheme/{id}")
    public ResponseEntity<Theme> getThemeById(@PathVariable("id") int id) {
		Theme theme = themeService.findThemeById(id);
        return new ResponseEntity<>(theme, HttpStatus.OK);
    }

    @PostMapping("/addtheme")
    public ResponseEntity<Theme> addTheme(@RequestBody Theme theme) {
    	Theme newTheme = themeService.addTheme(theme);
        return new ResponseEntity<>(newTheme, HttpStatus.CREATED);
    }
    
    @PutMapping("/updatetheme/{id}")
    public ResponseEntity<Theme> updateTheme(@PathVariable("id") int id,@RequestBody Theme theme) {
    	Optional<Theme> themeData = Optional.of(themeService.findThemeById(id));
    	if (themeData.isPresent()) {
			Theme _theme = themeData.get();
			_theme.setCodeTheme(theme.getCodeTheme());
			_theme.setLibelleTheme(theme.getLibelleTheme());
			return new ResponseEntity<>(themeService.updateTheme(_theme), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }

    
    
    @DeleteMapping("/deleteTheme/{codeTheme}")
    public ResponseEntity<?> deleteTheme(@PathVariable("codeTheme") String codeTheme) {
    	themeService.deleteTheme(codeTheme);
        return new ResponseEntity<>(HttpStatus.OK);
    }
	 
	 
	
}