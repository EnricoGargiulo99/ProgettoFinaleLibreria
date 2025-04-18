package it.accenture.libreriaProgetto.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "utenti")
public class Utente {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String nome;
	private String cognome;
	private String indirizzo;
	private String CF;
	
	@Column(unique=true)
	private String username;
	private String password;
	private String ruolo="utente";
	
	@JsonIgnore
	@OneToMany(mappedBy = "utente")
	private List<Gestione> libri;
	

}
