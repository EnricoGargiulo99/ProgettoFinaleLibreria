package it.accenture.libreriaProgetto.model; 

import lombok.Data;

@Data
public class LibroInput {
	
	private String titolo;
	private String autore;
	private String argomento;
	private String editore;
	private int copie;

}
