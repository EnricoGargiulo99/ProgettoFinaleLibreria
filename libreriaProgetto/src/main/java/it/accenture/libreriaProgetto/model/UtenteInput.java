package it.accenture.libreriaProgetto.model;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UtenteInput {
	private String nome;
	private String cognome;
	private String indirizzo;
	private String CF;
	
	@Column(unique=true)
	private String username;
	private String password;

}
