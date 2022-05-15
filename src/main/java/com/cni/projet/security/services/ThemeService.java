package com.cni.projet.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cni.projet.exception.ThemeNotFoundException;
import com.cni.projet.entity.Theme;
import com.cni.projet.repository.ThemeRepository;


@Service
public class ThemeService {
	
	private final ThemeRepository themeRepo;
	

    @Autowired
    public ThemeService(ThemeRepository themeRepo) {
        this.themeRepo = themeRepo;
    }


	public List<Theme> findAllThemes() {
		return themeRepo.findAll();
	}


	
	public Theme findThemeById(int id) {
		return themeRepo.findThemeById(id)
				.orElseThrow(() -> new ThemeNotFoundException("Theme with code " + id + " was not found"));
	}

	
	public Theme addTheme(Theme theme) {
        return themeRepo.save(theme);
	}
	


	public Theme updateTheme(Theme theme) {
		return themeRepo.save(theme);
	}

	public void deleteTheme(String codeTheme){
		themeRepo.deleteThemeByCodeTheme(codeTheme);
    }

}
