package it.accenture.libreriaProgetto.controller;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.accenture.libreriaProgetto.model.Libro;
import it.accenture.libreriaProgetto.model.LibroInput;
import it.accenture.libreriaProgetto.service.LibroService;


@RestController
public class LibriController {
	@Autowired
	private LibroService libroService;

	@PostMapping("libri")
	public String creaLibro(@RequestBody LibroInput libroInput) {
		return libroService.creaLibro(libroInput);
	}
	
	@GetMapping("libri")
	public List<Libro> getLibri() {
		return libroService.getLibri();
	}
	
	@GetMapping("libri/{id}")
	public ResponseEntity<Libro> getLibroById(@PathVariable int id) {
		return libroService.getLibroById(id);
	}
	
	@GetMapping("libri/titolo/{titolo}")
	public ResponseEntity<Libro> getLibroByTitolo(@PathVariable String titolo) {
		return libroService.getLibroByTitolo(titolo);
	}
	
	@GetMapping("libri/autore/{autore}")
	public ResponseEntity<List<Libro>> getLibroByAutore(@PathVariable String autore) {
		return libroService.getLibroByAutore(autore);
	}
	
	@GetMapping("libri/argomento/{argomento}")
	public ResponseEntity<List<Libro>> getLibroByArgomento(@PathVariable String argomento) {
		return libroService.getLibroByArgomento(argomento);
	}
	
	@GetMapping("libri/editore/{editore}")
	public ResponseEntity<List<Libro>> getLibroByEditore(@PathVariable String editore) {
		return libroService.getLibroByEditore(editore);
	}
	
	@PutMapping("libri/{titolo}")
	public ResponseEntity<String> modificaLibro(@PathVariable String titolo, @RequestBody LibroInput libroInput) {
		return libroService.modificaLibro(titolo, libroInput);
	}
	
	@DeleteMapping("libri/{titolo}")
	public String cancellaLibro(@PathVariable String titolo) {
		Libro libro = libroService.getLibroByTitolo(titolo).getBody();
		if (libro!=null) {
			return libroService.cancellaLibro(libro.getId());
		}
		return "Libro non presente";
	}

}
