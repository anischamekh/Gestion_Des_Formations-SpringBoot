package com.cni.projet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cni.projet.entity.User;

public interface FormateurRepository extends JpaRepository<User, Long> {

	void deleteFormateurById(Long id);
	 
	 Optional<User> findFormateurById(Long id);
}
