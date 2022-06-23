package com.cni.projet.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "formation",
		uniqueConstraints = {
		@UniqueConstraint(columnNames = "titre")})
public class Formation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String titre;
	private String description;
	@JsonFormat(pattern="yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	private LocalDate dateDebut;
	@JsonFormat(pattern="yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	private LocalDate dateFin;
	private int duree;
	private String lien;
	private Status etat;
	private String image;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="idUser")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name ="idTheme")
	private Theme theme;
	
	@JsonIgnore
	@OneToMany(mappedBy = "formation",
				cascade = CascadeType.ALL,
				fetch = FetchType.LAZY)
	private Set<Participant> participants = new HashSet<Participant>();
	/*
	@JsonIgnore
	@OneToMany(mappedBy = "formation",
				cascade = CascadeType.ALL,
				fetch = FetchType.LAZY)
	private Set<FileDB> filesDb = new HashSet<FileDB>();
	*/
	@JsonIgnore
	@OneToMany(mappedBy = "formation",
				cascade = CascadeType.ALL,
				fetch = FetchType.LAZY)
	private Set<Evaluation> evaluation = new HashSet<Evaluation>();
	
	public Formation() {
		super();
	}
	
	@JsonCreator	
	public Formation(@JsonProperty("id")int id, @JsonProperty("titre")String titre, @JsonProperty("description")String description, @JsonProperty("dateDebut")LocalDate dateDebut, @JsonProperty("dateFin")LocalDate dateFin, @JsonProperty("duree")int duree, @JsonProperty("lien")String lien,
			@JsonProperty("etat")Status etat, @JsonProperty("image") String image,  @JsonProperty("user")User user, @JsonProperty("theme")Theme theme) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.duree = duree;
		this.lien = lien;
		this.etat = etat;
		this.image = image;
		this.user = user;
		this.theme = theme;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public String getLien() {
		return lien;
	}

	public void setLien(String lien) {
		this.lien = lien;
	}

	public Status getEtat() {
		return etat;
	}

	public void setEtat(Status etat) {
		this.etat = etat;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Theme getTheme() {
		return theme;
	}
	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	
	public Set<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<Participant> participants) {
		this.participants = participants;
	}

/*
	public Set<FileDB> getFilesDb() {
		return filesDb;
	}

	public void setFilesDb(Set<FileDB> filesDb) {
		this.filesDb = filesDb;
	}
	*/
	public Set<Evaluation> getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Set<Evaluation> evaluation) {
		this.evaluation = evaluation;
	}

	@Override
	public String toString() {
		return "Formation [id=" + id + ", titre=" + titre + ", description=" + description + ", dateDebut=" + dateDebut
				+ ", dateFin=" + dateFin + ", duree=" + duree + ", lien=" + lien + ", etat=" + etat + ", image=" + image
				+ "]";
	}

	
	
}
