package com.cni.projet.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "evaluation")
public class Evaluation {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String fullName;
	private String difficulte;
	private String rythme;
	private String attentes;
	private String objectifsAtteints;
	private String inscription;
	private String duree;
	private String dates;
	private String niveau;
	private String langageTenu;
	private int noteFormateur;
	private int noteFormation;
	private String onligneOrPres;
	private String dispensions;
	private String idees;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name ="idFormation")
	private Formation formation;

	public Evaluation(int id, String fullName, String difficulte, String rythme, String attentes,
			String objectifsAtteints, String inscription, String duree, String dates, String niveau, String langageTenu,
			int noteFormateur, int noteFormation, String onligneOrPres, String dispensions, String idees,
			Formation formation) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.difficulte = difficulte;
		this.rythme = rythme;
		this.attentes = attentes;
		this.objectifsAtteints = objectifsAtteints;
		this.inscription = inscription;
		this.duree = duree;
		this.dates = dates;
		this.niveau = niveau;
		this.langageTenu = langageTenu;
		this.noteFormateur = noteFormateur;
		this.noteFormation = noteFormation;
		this.onligneOrPres = onligneOrPres;
		this.dispensions = dispensions;
		this.idees = idees;
		this.formation = formation;
	}

	public Evaluation() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDifficulte() {
		return difficulte;
	}

	public void setDifficulte(String difficulte) {
		this.difficulte = difficulte;
	}

	public String getRythme() {
		return rythme;
	}

	public void setRythme(String rythme) {
		this.rythme = rythme;
	}

	public String getAttentes() {
		return attentes;
	}

	public void setAttentes(String attentes) {
		this.attentes = attentes;
	}

	public String getObjectifsAtteints() {
		return objectifsAtteints;
	}

	public void setObjectifsAtteints(String objectifsAtteints) {
		this.objectifsAtteints = objectifsAtteints;
	}

	public String getInscription() {
		return inscription;
	}

	public void setInscription(String inscription) {
		this.inscription = inscription;
	}

	public String getDuree() {
		return duree;
	}

	public void setDuree(String duree) {
		this.duree = duree;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public String getLangageTenu() {
		return langageTenu;
	}

	public void setLangageTenu(String langageTenu) {
		this.langageTenu = langageTenu;
	}

	public int getNoteFormateur() {
		return noteFormateur;
	}

	public void setNoteFormateur(int noteFormateur) {
		this.noteFormateur = noteFormateur;
	}

	public int getNoteFormation() {
		return noteFormation;
	}

	public void setNoteFormation(int noteFormation) {
		this.noteFormation = noteFormation;
	}

	public String getOnligneOrPres() {
		return onligneOrPres;
	}

	public void setOnligneOrPres(String onligneOrPres) {
		this.onligneOrPres = onligneOrPres;
	}

	public String getDispensions() {
		return dispensions;
	}

	public void setDispensions(String dispensions) {
		this.dispensions = dispensions;
	}

	public String getIdees() {
		return idees;
	}

	public void setIdees(String idees) {
		this.idees = idees;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}
	
	
}
