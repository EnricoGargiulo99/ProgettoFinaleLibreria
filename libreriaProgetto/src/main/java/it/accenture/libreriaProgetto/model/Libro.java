package it.accenture.libreriaProgetto.model;

import java.util.List;  

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "libri")
public class Libro {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(unique=true)
	private String titolo;
	private String autore;
	private String argomento;
	private String editore;
	private int copie;
	private int copieDisponibili;
	
	@JsonIgnore
	@OneToMany(mappedBy = "libro")
	private List<Gestione> utenti;

}
