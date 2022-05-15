package com.cni.projet.entity;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "theme",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "codeTheme")})
public class Theme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String codeTheme;
	private String libelleTheme;

	@JsonIgnore
	@OneToMany(mappedBy = "theme",
			cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
	private Set<Formation> formations = new HashSet<Formation>();
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodeTheme() {
		return codeTheme;
	}
	public void setCodeTheme(String codeTheme) {
		this.codeTheme = codeTheme;
	}
	public String getLibelleTheme() {
		return libelleTheme;
	}
	public void setLibelleTheme(String libelleTheme) {
		this.libelleTheme = libelleTheme;
	}
	
	public Set<Formation> getFormations() {
		return formations;
	}
	public void setFormations(Set<Formation> formations) {
		this.formations = formations;
	}
	
	public Theme(int id,String codeTheme, String libelleTheme) {
		super();
		this.id = id;
		this.codeTheme = codeTheme;
		this.libelleTheme = libelleTheme;
	}
	
	public Theme() {
	}
	
	
}
