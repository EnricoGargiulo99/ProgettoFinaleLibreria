package it.accenture.libreriaProgetto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.accenture.libreriaProgetto.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Integer>{
	
	public List<Utente> findByNomeAndCognome(String nome, String cognome);
	
	public Utente findByUsername(String username);

}
