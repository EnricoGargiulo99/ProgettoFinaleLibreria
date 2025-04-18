package it.accenture.libreriaProgetto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.accenture.libreriaProgetto.model.Gestione;
import it.accenture.libreriaProgetto.model.Libro;
import it.accenture.libreriaProgetto.model.Utente;
import it.accenture.libreriaProgetto.repository.GestioneRepository;
import it.accenture.libreriaProgetto.repository.LibroRepository;
import it.accenture.libreriaProgetto.repository.UtenteRepository;

@Service
public class GestioneService {
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private LibroService libroService;
	
	@Autowired
	private LibroRepository libroRepository;
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Autowired
	private GestioneRepository gestioneRepository;
	
	public List<Gestione> getGestioni(){
		return gestioneRepository.findAll();
	}
	
	public boolean prenotaLibro(String titolo, String username) {
		Libro libro = libroService.getLibroByTitolo(titolo).getBody();
		if(libro.getCopieDisponibili()>0) {
			libro.setCopieDisponibili(libro.getCopieDisponibili()-1);
			Utente utente=utenteService.getUtenteByUsername(username).getBody();
			Gestione gestione = new Gestione(utente, libro);
			
			gestioneRepository.save(gestione);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean restituisciLibro(String titolo, String username) {
		Libro libro = libroService.getLibroByTitolo(titolo).getBody();
		Utente utente=utenteService.getUtenteByUsername(username).getBody();
		
		List<Gestione> gestioni = gestioneRepository.findByUtente(utente);
		
		for(Gestione g:gestioni) {
			if(g.getLibro().getTitolo()==libro.getTitolo()) {
				libro.setCopieDisponibili(libro.getCopieDisponibili()+1);
				gestioneRepository.deleteById(g.getId());
				return true;
			}
		}
		return false;
	}
}
