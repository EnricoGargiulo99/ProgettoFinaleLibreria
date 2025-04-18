package it.accenture.libreriaProgetto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.accenture.libreriaProgetto.model.Utente;
import it.accenture.libreriaProgetto.model.UtenteInput;
import it.accenture.libreriaProgetto.repository.UtenteRepository;

@Service
public class UtenteService {
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	public String creaUtente(UtenteInput utenteIn) {
		Utente utente = new Utente();
		utente.setNome(utenteIn.getNome());
		utente.setCognome(utenteIn.getCognome());
		utente.setIndirizzo(utenteIn.getIndirizzo());
		utente.setCF(utenteIn.getCF());
		utente.setUsername(utenteIn.getUsername());
		utente.setPassword(utenteIn.getPassword());
		
		return utenteRepository.save(utente).getUsername();
	}
	
	public List<Utente> getUtenti(){
		return utenteRepository.findAll();
	}
	
	public ResponseEntity<Utente> getUtenteByUsername(String username) {
		Utente utente=utenteRepository.findByUsername(username);
		if(utente!=null) {
			return new ResponseEntity<Utente>(utente, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Utente>(utente, HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<String> modificaUtente(String username, UtenteInput autoreIn) {
		Utente utenteMod = getUtenteByUsername(username).getBody();
		if(utenteMod!=null) {
			utenteMod.setNome(autoreIn.getNome());
			utenteMod.setCognome(autoreIn.getCognome());
			utenteMod.setIndirizzo(autoreIn.getIndirizzo());
			utenteMod.setCF(autoreIn.getCF());
			utenteMod.setUsername(autoreIn.getUsername());
			utenteMod.setPassword(autoreIn.getPassword());
			utenteRepository.save(utenteMod);
			return new ResponseEntity<String>("Utente modificato.", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Utente non trovato.", HttpStatus.NOT_FOUND);
	}
	
	public String cancellaUtente(String username) {
		Utente utente = getUtenteByUsername(username).getBody();
		if(utente!=null) {
			utenteRepository.deleteById(utente.getId());
			return "Utente cancellato";
		}
		else {
			return "Utente non presente";
		}
	}

}
