package com.cni.projet.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
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
	private Date date;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="idUser")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name ="idTheme")
	private Theme theme;
	
	public Formation() {
		super();
	}
	
	@JsonCreator 
	public Formation(@JsonProperty("id")int id,@JsonProperty("titre")String titre,@JsonProperty("description")String description,@JsonProperty("date")Date date,@JsonProperty("user")User user,@JsonProperty("theme")Theme theme) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.date = date;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	@Override
	public String toString() {
		return "Formation [id=" + id + ", Titre=" + titre + ", description=" + description + ", date=" + date + "]";
	}
	
	
	
}
