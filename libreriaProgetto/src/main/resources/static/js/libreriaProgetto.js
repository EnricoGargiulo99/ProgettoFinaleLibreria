
function creaUtente(e) {
        //creazione utente
		e.preventDefault();
        fetch("http://localhost:8080/utenti", {
            method: 'POST',
            body: JSON.stringify({

                nome: document.getElementById("nome").value,
                cognome: document.getElementById("cognome").value,
                indirizzo: document.getElementById("indirizzo").value,
                cf: document.getElementById("cf").value,
                username: document.getElementById("username").value,
                password: document.getElementById("password").value

            }),
            headers: { 'Content-type': 'application/json;charset=UTF-8' }
        }).then(response => response.text()).
            then(json => console.log(json)).
            catch(error => console.log(error));
}


async function cancellaUtente(event) {
    //cancella utente
	let usernameSearch = sessionStorage.getItem('utente');
	try{
		let f= await fetch("http://localhost:8080/utenti/"+usernameSearch,{
	        method:'DELETE',
	    headers:{'Content-type':'application/json;charset=UTF-8'}
	})
		let ft=await f.text();
		} catch(error){
			console.log(error);
		};
}

async function modificaUtente(event) {
	event.preventDefault();
    let usernameSearch = sessionStorage.getItem('utente');

    //mudifica utente
   	try{
	let f= await fetch("http://localhost:8080/utenti/"+usernameSearch,{
        method:'PUT',
        body: JSON.stringify({

			nome: document.getElementById("updNome").value,
			cognome: document.getElementById("updCognome").value,
			indirizzo: document.getElementById("updIndirizzo").value,
			cf: document.getElementById("updCf").value,
			username: usernameSearch,
			password: document.getElementById("updPassword").value
            
    }),
    headers:{'Content-type':'application/json;charset=UTF-8'}
})
	let ft=await f.text();
		modificaSession(ft);
	} catch(error){
		console.log(error);
	}

}

function modificaSession(json){
	sessionStorage.setItem("modifica", json);
}

function cercaUtente(username) {

    return fetch("http://localhost:8080/utenti/" + username);
	
	
}

function cercaUsername(username) {

    return fetch("http://localhost:8080/utenti/controlla/" + username);
	
	
}

/////////////////////////////////////LIBRI/////////////////////////////////////
//AGGIUNGI LIBRO
async function aggiungiLibro(event) {
    
    event.preventDefault();
	
	try{
		let f =await fetch("http://localhost:8080/libri", {
		        method: 'POST',
		        body: JSON.stringify({

		            titolo: document.getElementById("titolo").value,
		            autore: document.getElementById("autore").value,
		            argomento: document.getElementById("argomento").value,
		            editore: document.getElementById("editore").value,
		            copie: document.getElementById("copie").value,

		        }),
		        headers: { 'Content-type': 'application/json;charset=UTF-8' }
		    });
		let ft=await f.text();
		statusSession(ft);
	} catch(error){
		console.log(error);
	}
}
	

//MODIFICA LIBRO
async function modificaLibro(event) {
    event.preventDefault();
     let titolo= document.getElementById("titoloUp").value;
	 	
	 try{
	 	 		let f =await fetch("http://localhost:8080/libri/"+titolo,{
		        method:'PUT',
		        body: JSON.stringify({
		    
		            titolo: document.getElementById("titoloUp").value,
		            autore: document.getElementById("autoreUp").value,
		            argomento: document.getElementById("argomentoUp").value,
		            editore: document.getElementById("editoreUp").value,
		            copie: document.getElementById("copieUp").value,
		            
		    }),
		    headers:{'Content-type':'application/json;charset=UTF-8'}
		    });
	 	 		let ft=await f.text();
	 	 		statusSession(ft);
	 	 	} catch(error){
	 	 		console.log(error);
	 	 	}
    
}

//CANCELLA
async function cancellaLibro(event) {
	
    let titolo= document.getElementById("titoloRimuovi").value;
    
	
    try{
		let f =await fetch("http://localhost:8080/libri/"+ titolo, {method: 'DELETE'});
		let ft=await f.text();
		statusSession(ft)
	} catch(error){
		console.log(error);
	}
}
	
	
	function statusSession(json){
		sessionStorage.setItem("status", json);
	}

//CERCA
function cercaLibroTitolo(titolo) {

        return fetch("http://localhost:8080/libri/titolo/" + titolo);
        
}



function  cercaPerTitolo(event){

		event.preventDefault();
		
		

		let titolo = document.getElementById('cercaLibroPulsante').value.trim();
		
		if (!titolo) {
		  showAlert("⚠️ Titolo non valido.", "warning");
		  return;
		}
	
				fetch("http://localhost:8080/libri/titolo/" + titolo).
				then(response => {
  		    // Verifica se la risposta è ok
  		    if (!response.ok) {
  		    	showAlert('Libro non trovato.', 'danger');
  		    }

  		    // Prova a leggere la risposta come JSON
  		    return response.json();
  		  })
  	    .then(data => {
  	      // Verifica se l'utente esiste e se la password è corretta
  	      		console.log(data);
			if(data){
				  document.getElementById("modaleTitolo").textContent = data.titolo;
	        	  document.getElementById("modaleAutore").textContent = data.autore;
	        	  document.getElementById("modaleArgomento").textContent = data.argomento;
	        	  document.getElementById("modaleEditore").textContent = data.editore;
	        	  document.getElementById("modaleCopie").textContent = data.copie;
	        	  document.getElementById("modaleCopieDisponibili").textContent = data.copieDisponibili;

	        	  const myModal = new bootstrap.Modal(document.getElementById('dettagliLibroModal'));
	        	  myModal.show();
			}
  	      
  	    })
  	    .catch(error => {
  	      console.log("Errore nella fetch:", error);
  	    });
		
	}


