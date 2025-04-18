package it.accenture.libreriaProgetto.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.accenture.libreriaProgetto.model.Gestione;
import it.accenture.libreriaProgetto.model.Utente;



public interface GestioneRepository extends JpaRepository<Gestione, Integer>{
	
	public List<Gestione> findByUtente(Utente utente);
}
