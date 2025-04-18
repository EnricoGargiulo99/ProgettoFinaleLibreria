package it.accenture.libreriaProgetto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.accenture.libreriaProgetto.model.Utente;
import it.accenture.libreriaProgetto.model.UtenteInput;
import it.accenture.libreriaProgetto.service.UtenteService;

@RestController
public class UtenteController {
	
	@Autowired
	private UtenteService utenteService;

	@PostMapping("utenti")
	public String creaUtente(@RequestBody UtenteInput utenteInput) {
		return utenteService.creaUtente(utenteInput);
	}
	
	@GetMapping("utenti")
	public List<Utente> getUtenti() {
		return utenteService.getUtenti();
	}
	
	@GetMapping("utenti/{username}")
	public ResponseEntity<Utente> getUtenteByUsername(@PathVariable String username) {
		return utenteService.getUtenteByUsername(username);
	}
	
	@GetMapping("utenti/controlla/{username}")
	public boolean cercaUsername(@PathVariable String username) {
		if(utenteService.getUtenteByUsername(username).getBody()!=null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@PutMapping("utenti/{username}")
	public ResponseEntity<String> Utente(@PathVariable String username, @RequestBody UtenteInput utenteInput) {
		return utenteService.modificaUtente(username, utenteInput);
	}
	
	@DeleteMapping("utenti/{username}")
	public String cancellaUtente(@PathVariable String username) {
		return utenteService.cancellaUtente(username);
	}

}
