package it.accenture.libreriaProgetto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.accenture.libreriaProgetto.model.Gestione;
import it.accenture.libreriaProgetto.model.GestioneInput;
import it.accenture.libreriaProgetto.service.GestioneService;

@RestController
public class GestioneController {
	
	@Autowired
	private GestioneService gestioneService;
	
	@GetMapping("gestione")
	public List<Gestione> getGestioni() {
		return gestioneService.getGestioni();
	}
	
	
	@PostMapping("prenotazione")
	public boolean prenota(@RequestBody GestioneInput gestioneInput) {
		return gestioneService.prenotaLibro(gestioneInput.getTitolo(), gestioneInput.getUsername());
	}
	
	@DeleteMapping("restituzione")
	public boolean restituisci(@RequestBody GestioneInput gestioneInput) {
		return gestioneService.restituisciLibro(gestioneInput.getTitolo(), gestioneInput.getUsername());
	}

}
