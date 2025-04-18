package it.accenture.libreriaProgetto.service;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.accenture.libreriaProgetto.model.Gestione;
import it.accenture.libreriaProgetto.model.Libro;
import it.accenture.libreriaProgetto.model.LibroInput;
import it.accenture.libreriaProgetto.model.Utente;
import it.accenture.libreriaProgetto.repository.LibroRepository;

@Service
public class LibroService {
	
	@Autowired
	private LibroRepository libroRepository;
	
	public String creaLibro(LibroInput libroIn) {
		Libro libroMod = getLibroByTitolo(libroIn.getTitolo()).getBody();
		System.out.println(libroMod);
		if(libroMod==null) {
			Libro libro = new Libro();
			libro.setTitolo(libroIn.getTitolo());
			libro.setAutore(libroIn.getAutore());
			libro.setArgomento(libroIn.getArgomento());
			libro.setEditore(libroIn.getEditore());
			libro.setCopie(libroIn.getCopie());
			libro.setCopieDisponibili(libroIn.getCopie());
			libroRepository.save(libro).getId();
			return "Libro aggiunto";
		}
		else {
			return "Libro già presente";
		} 
	}
	
	public List<Libro> getLibri(){
		return libroRepository.findAll();
	}
	
	public ResponseEntity<Libro> getLibroById(int id) {
		Libro libro=libroRepository.findById(id).orElse(null);
		if(libro!=null) {
			return new ResponseEntity<Libro>(libro, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Libro>(libro, HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<Libro> getLibroByTitolo(String titolo) {
		Libro libri=libroRepository.findByTitolo(titolo);
		if(libri!=null) {
			return new ResponseEntity<Libro>(libri, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Libro>(libri, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<List<Libro>> getLibroByAutore(String autore) {
		List<Libro> libri=libroRepository.findByAutore(autore);
		if(libri.size()!=0) {
			return new ResponseEntity<List<Libro>>(libri, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Libro>>(libri, HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<List<Libro>> getLibroByArgomento(String argomento) {
		List<Libro> libri=libroRepository.findByArgomento(argomento);
		if(libri.size()!=0) {
			return new ResponseEntity<List<Libro>>(libri, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Libro>>(libri, HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<List<Libro>> getLibroByEditore(String editore) {
		List<Libro> libri=libroRepository.findByEditore(editore);
		if(libri.size()!=0) {
			return new ResponseEntity<List<Libro>>(libri, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Libro>>(libri, HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<String> modificaLibro(String titolo, LibroInput libroIn) {
		Libro libroMod = getLibroByTitolo(titolo).getBody();
		if(libroMod!=null) {
			libroMod.setTitolo(libroIn.getTitolo());
			libroMod.setAutore(libroIn.getAutore());
			libroMod.setArgomento(libroIn.getArgomento());
			libroMod.setEditore(libroIn.getEditore());
			int nCopiePrestate=libroMod.getCopie()-libroMod.getCopieDisponibili();
			libroMod.setCopie(libroIn.getCopie());
			libroMod.setCopieDisponibili(libroIn.getCopie()-nCopiePrestate);
			libroRepository.save(libroMod);
			return new ResponseEntity<String>("Libro modificato", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Libro non trovato", HttpStatus.NOT_FOUND);
	}
	
	public String cancellaLibro(int id) {
		Libro libro = getLibroById(id).getBody();
		if(libro!=null) {
			libroRepository.deleteById(id);
			return "Libro cancellato";
		}
		else {
			return "Libro non presente";
		}
	}

}
