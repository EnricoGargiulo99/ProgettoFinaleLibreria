package it.accenture.libreriaProgetto.repository;

import java.util.List; 


import org.springframework.data.jpa.repository.JpaRepository;

import it.accenture.libreriaProgetto.model.Libro;


public interface LibroRepository extends JpaRepository<Libro, Integer>{

	public Libro findByTitolo(String titolo);
	public List<Libro> findByAutore(String autore);
	public List<Libro> findByArgomento(String argomento);
	public List<Libro> findByEditore(String editore);
}
