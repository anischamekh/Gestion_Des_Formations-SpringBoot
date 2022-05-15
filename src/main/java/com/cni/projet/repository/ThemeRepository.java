package com.cni.projet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cni.projet.entity.Theme;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Integer> {
	
	 void deleteThemeByCodeTheme(String codeTheme);
	 
	 Optional<Theme> findThemeById(int id);

}
