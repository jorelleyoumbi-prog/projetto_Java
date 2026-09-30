package codice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import codice.Eccezioni.AnnuncioDuplicatoException;

/**@author Jorelle MADELEINE MENGAPTCKE YOUMBI    20047743
 * La classe Bacheca  gestisce una collezione di annunci 
 * contine una lista di annunci ed una lista di parole chiave inserite dall'utente
 * 
 */

public class Bacheca implements Iterable<Annuncio> {
  
	private final List <Annuncio>annunci;
	
	/**
	 *  Costruttore Bacheca che inizzializza la lista  di annunci
	 * @param annunci lista di annunci 
	 * @throws IllegalArgumentException se la lista è degli annunci è vuota
	 */
	public Bacheca(List<Annuncio> annunci) {
		
		if(annunci==null) {
			throw new IllegalArgumentException("La lista degli annunci non può essere nulla");
		}
		this.annunci=annunci;
	}
	
	
	/** aggiungiAnnuncio aggiunge un nuovo annuncio alla bacheca in base al suo tipo di annuncio(vendite o acquista),evitando i duplicati
	 * @param tipoAnnuncio preciso il tipo di acquisto: vendite oacquista
	 * @param id identificatore univoco dell'annuncio
	 * @param emailUtente identificatore univoco dell'utente che agguinge l'annuncio alla bacheca
	 * @param prezzo è il prezzo dell'annuncio
	 * @param nomeArticolo è il nome della'articolo
	 * @param paroleChiave parole chiave legate all'annuncio , inserite dall'utente e  che permettono di effetuare una ricerca per parole chiave degli annunci
	 * @param dataScadenza data di scadenza di  un annuncio di vendita
	 * @param annunciCorrelati  annunci  di acquisto che hanno in commune le parole chiave inserite dall'utente
	 * @throws IllegalArgumentException se uno dei parametri non è valido
	 */
	
	
	
	public List<Annuncio> aggiungiAnnuncio(String tipoAnnuncio, String id, String emailUtente, double prezzo, String nomeArticolo, String paroleChiave, LocalDate dataScadenza) throws AnnuncioDuplicatoException {
	   
		if (tipoAnnuncio == null || tipoAnnuncio.isBlank()) {
	        throw new IllegalArgumentException("Il tipo di annuncio deve essere specificato (vendite o acquisto).");
	    }

	    // Controllo sull'ID duplicato
	    for (Annuncio a : annunci) {
	        if (a.getId().equals(id)) {
	            throw new AnnuncioDuplicatoException("Annuncio con ID '" + id + "' già presente.");
	        }
	    }

	    Annuncio annuncio;
	    List<Annuncio> annunciCorrelati = null;

	    switch (tipoAnnuncio.toLowerCase()) {
	        case "vendita":
	            if (dataScadenza == null) {
	                throw new IllegalArgumentException("Un annuncio di tipo vendita deve avere una data scadenza.");
	            }
	            annuncio = new AnnuncioVendite(id, emailUtente, prezzo, nomeArticolo, paroleChiave, dataScadenza);
	            break;

	        case "acquisto":
	            annuncio = new AnnuncioAcquisto(id, emailUtente, prezzo, nomeArticolo, paroleChiave);

	            // Cerca annunci correlati per parole chiave
	            annunciCorrelati = cercaArticoliPerParoleChiave(paroleChiave.split(","));
	            break;

	        default:
	            throw new IllegalArgumentException("Tipo di annuncio non valido. Deve essere 'vendita' o 'acquisto' ");
	    }

	    // Aggiunge l'annuncio alla lista
	    annunci.add(annuncio);

	    return annunciCorrelati; // Restituisce gli annunci correlati se tipo è acquisto
	}

	
	 
    /**
     * Metodo che permette di rimuovere un annuncio dalla bacheca dei annunci a partire dell'email dell'utente e dell'id dell'annuncio
     * @param id identificatore dell'annuncio
     * @param emailUtente identificatore univoco dell'utente che ha inserito l'annuncio
     * @return  ritorna true se l'annuncio è stato rimosso e false altrimenti
     */
    
    public boolean rimuoviAnnuncio(String id,String emailUtente) {

    	
    	return  annunci.removeIf(annuncio->
    	annuncio.getId().equals(id) && annuncio.getEmailUtente().equals(emailUtente));
    }
	  
	  /**
	     * Cerca annunci che contengono almeno una delle parole chiave specificate.
	     *
	     * @param paroleChiave Un array di parole chiave per la ricerca.
	     * @return Una lista di annunci che contengono parole chiave in comune.
	     */
	    public List<Annuncio> cercaArticoliPerParoleChiave(String... paroleChiave) {
	        List<String> paroleChiaveList = List.of(paroleChiave);
	        return annunci.stream()
	                .filter(annuncio -> {
	                    List<String> annuncioParoleChiave = List.of(annuncio.getParoleChiave().split(","));
	                    return annuncioParoleChiave.stream().anyMatch(paroleChiaveList::contains);
	                })
	                .collect(Collectors.toList());
	    }
	    
	      
	    
	    /**
	     * Rimuove dalla bacheca tutti gli annunci scaduti
	     */
	    public void pulisciBacheca() {
	    	
	        annunci.removeIf(annuncio->
	    	annuncio instanceof AnnuncioVendite vendite && vendite.getScadenzaData().isBefore(LocalDate.now()));
	    }
	    
	  
	    /**
	     * Iteratore che permette di scorrere la bacheca di annunci
	     * @return un iterator sugli annunci
	     */
	 
		@Override
		public Iterator<Annuncio> iterator() {
			return annunci.iterator();
		}
		
		
		/**
		 *  permette di restituire la liste degli annunci
		 * @return ritorna la lista degli annunci
		 */
		public List<Annuncio> getAnnunci(){
			 return new ArrayList<>( annunci);
		}
	
		
		/**
		 * permette di memorizzare sul file la bacheca di annunci
		 * Ogni annuncio è salvato sul file con le sue caratteristiche separate con ";"
		 * @param nomeFile nome del file su cui vengono salvati gli annunci
		 * @throws IOException lancia l'eccezione IOException se c'è un errore durante la scrituura sul file
		 * 
		 */
		public void salvaSuFile(String nomeFile) throws IOException{
			 try(PrintWriter writer = new PrintWriter(new FileWriter(nomeFile))){
				 
				 for(Annuncio annuncio:annunci) {
					 if(annuncio instanceof AnnuncioVendite vendite) {
						 writer.println(vendite.getId()+";"+vendite.getEmailUtente()+";"+annuncio.getPrezzo()+";"+vendite.getNomeArticolo()+";"+vendite.getParoleChiave()+";"+vendite.getScadenzaData());
					 }
					 else {
						 writer.println(annuncio.getId()+";"+annuncio.getEmailUtente()+";"+annuncio.getPrezzo()+";"+annuncio.getNomeArticolo()+";"+annuncio.getParoleChiave()+";");
					 }
					
				 }
			 }
		}
		
		/**
		 * permmet di leggere dal file gli annunci della bacheca
		 * @param nomeFile nome del file da cui vengono letti gli annunci
		 * @throws IOException solleva l'eccezione durante la lettura del file
		 */
		public void caricaDaFile(String nomeFile) throws IOException {
		    try (BufferedReader reader = new BufferedReader(new FileReader(nomeFile))) {
		        String linea;

		        while ((linea = reader.readLine()) != null) {
		            // Rimuove eventuali spazi bianchi indesiderati
		            linea = linea.trim();

		            
		            String[] dettagliAnnuncio = linea.split(";");
		            
		            
		            if (dettagliAnnuncio.length < 5) {
		                throw new IllegalArgumentException("Annuncio non valido: dati insufficienti.");
		            }

		            String id = dettagliAnnuncio[0].trim();
		            String emailUtente = dettagliAnnuncio[1].trim();
		            double prezzo;

		            try {
		                prezzo = Double.parseDouble(dettagliAnnuncio[2].trim());
		            } catch (NumberFormatException e) {
		                throw new IllegalArgumentException("Prezzo non valido nel file: " + dettagliAnnuncio[2]);
		            }

		            String nomeArticolo = dettagliAnnuncio[3].trim();
		            String paroleChiave = dettagliAnnuncio[4].trim();

		            // Se ci sono 6 elementi, significa che è un annuncio di vendita con data di scadenza
		            if (dettagliAnnuncio.length == 6) {
		                try {
		                    LocalDate dataScadenza = LocalDate.parse(dettagliAnnuncio[5].trim());
		                    Annuncio annuncio = new AnnuncioVendite(id, emailUtente, prezzo, nomeArticolo, paroleChiave, dataScadenza);
		                    annunci.add(annuncio);
		                } catch (DateTimeParseException e) {
		                    throw new IllegalArgumentException("Il formato della data di scadenza non è valido per l'annuncio: " + dettagliAnnuncio[5]);
		                }
		            } else {
		                // Annuncio di acquisto senza data di scadenza
		                Annuncio annuncio = new AnnuncioAcquisto(id, emailUtente, prezzo, nomeArticolo, paroleChiave);
		                annunci.add(annuncio);
		            }
		        }
		    }
		}

}