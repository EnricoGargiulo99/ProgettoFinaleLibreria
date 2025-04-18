package it.accenture.libreriaProgetto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Gestione { 
	
	public Gestione() {
		
	}

	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne()
	@JoinColumn(name = "utente_fk")
	private Utente utente;
	
	@ManyToOne()
	@JoinColumn(name = "libro_fk")
	private Libro libro;

	public Gestione(Utente utente, Libro libro) {
		this.utente = utente;
		this.libro = libro;
	}
	
	

}
