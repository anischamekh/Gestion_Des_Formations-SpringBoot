package com.cni.projet.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "participant")
public class Participant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 8)
	private int cin;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@NotBlank
	@Email
	private String email;
	@Column(length = 8)
	private int tel;
	@NotBlank
	private String ville;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="idFormation")
	private Formation formation;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCin() {
		return cin;
	}
	public void setCin(int cin) {
		this.cin = cin;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTel() {
		return tel;
	}
	public void setTel(int tel) {
		this.tel = tel;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	public Formation getFormation() {
		return formation;
	}
	public void setFormation(Formation formation) {
		this.formation = formation;
	}
	public Participant(@NotBlank int id, @NotBlank int cin, @NotBlank String firstName,
			@NotBlank String lastName, @NotBlank @Email String email, @NotBlank int tel,
			@NotBlank String ville,@JsonProperty("formation")Formation formation) {
		super();
		this.id = id;
		this.cin = cin;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.tel = tel;
		this.ville = ville;
		this.formation = formation;
	}
	public Participant() {
		super();
	}
	@Override
	public String toString() {
		return "Participant [id=" + id + ", cin=" + cin + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", tel=" + tel + ", ville=" + ville + ", formation=" + formation + "]";
	}

}

