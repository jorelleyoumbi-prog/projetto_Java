
package codice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jorelle Madeleine MENGAPTCHE YOUMBI  20047743
 * La classe astratta Annuncio permette di gestire  gli annunci nella bacheca
 * Ogni annuncio possiede un Id che lo idebntifica,l'email dell'utente ,
 * prezzo ,il nome dell'articolo,delle parole chiave
 * Possiede anche una lista di parola su cui l'utente può apportatre delle modifiche
 */
public abstract class Annuncio {
   
	 private final String id;
	 private  final String emailUtente ;
	 private  final double prezzo;
	 private  final String nomeArticolo;
	 private  final  String paroleChiave;
	 private List <String> ParolaChiave;
	 
	 
	 /**
	  * Costruttore Annuncio che  inizia i diversi campi della classe Annuncio
	  * @param id identifactore univoco dell'annuncio
	  * @param emailUtente identificatore dell'utente nell'annuncio
	  * @param prezzo prezzo dell'articolo da vendere o cercare
	  * @param nomeArticolo  nome dell'articolo da vendere o da cercare
	  * @param paroleChiave  parole chiave associate all'annuncio
	  * @throws IllegalArgumentException  se uno dei campi non è valido
	  */
	 
	 
	 public Annuncio(String id,String emailUtente,double prezzo,String nomeArticolo,String paroleChiave) {
		 
		  if(id==null || id.isBlank() ) {
			  throw new IllegalArgumentException("id non può essere nullo o vuoto");
		  }
		  
		  if(emailUtente==null|| emailUtente.isBlank()) {
			  
			  throw new IllegalArgumentException("emailUtente non può essere nulla o vuota");
		  }
		  
		  if(prezzo<0) {
			  
			  throw new IllegalArgumentException("prezzo non può essere negativo");
		  }
		  
		  if(nomeArticolo==null|| nomeArticolo.isBlank()) {
			  throw new IllegalArgumentException("nomeArticolo non può essere nullo o vuoto");
		  }
		  if(paroleChiave==null || paroleChiave.isBlank()) {
			  throw new IllegalArgumentException("paroleChiave non può essere nullo o vuoto");
		  }
		  
		  this.id=id;
		  this.emailUtente=emailUtente;
		  this.prezzo=prezzo;
		  this.nomeArticolo=nomeArticolo;
		  this.paroleChiave=paroleChiave;
		  this.ParolaChiave=new ArrayList<>();
	 }
	 
	 
	 /**
	  *  Restituisce l'identificatore univoco dell'annuncio
	  *  @return ritorna l'id dell'annuncio
	  */
	 
	 public String getId(){
		 
		 return id;
	 }
	 
	 /**
	  *  Restituisce l'email dell'utente che ha creato l'annuncio
	  *  @return ritorna l'email dell'utente
	  */
	 
	 public String getEmailUtente() {
		 return emailUtente;
	 }
	 
	 /**
	  * restituisce il prezzo dell'annuncio
	  * @return ritorna il prezzo dell'annuncio
	  */
	 
	 public double getPrezzo() {
		 
		 return prezzo;
	 }
	 
	 /**
	  * restituisce il nome dell'articolo
	  * @return ritorna il nome dell'articolo
	  */
	 
	 public String getNomeArticolo() {
		 
		 return nomeArticolo;
	 }
	 
	 /**
	  * restituisce le parole chiave legate all'annuncio
	  * @return ritorna le parole chiave dell'annuncio 
	  */
	 
	 public String getParoleChiave() {
		
		 return paroleChiave;
	 }
	 
	 /**
	  * restituisce la lista delle parole chiave degli annunci
	  * @return la lista deiìlle parole chiave
	  */
	 public List<String> getParolaChiave() {
		  return ParolaChiave;
	 }
	 
	 /**
	  * metodo astratta che fornisce la data di scadenza dell'annuncio
	  * è implemetato nelle sottoclasse  AnnuncioVendite e AnnuncioAcquisto
	  */
	
	  public abstract LocalDate getScadenzaData();
	  
	  /**
	   * restituisce testatualemente le caratteristiche dell'annuncio
	   * @return ritorna l'annuncio con le sue caratteristiche
	   */
	  
	  @Override
	  public String toString() {
		  
		  return "Annuncio { "+"id= " +id + ";emailUtente= "+ emailUtente + ";prezzo= " +prezzo + ";nomeArticolo= "+nomeArticolo +";paroleChiave= "+ paroleChiave
				  +"}";
	  }
	   
	  /**
	   * verifica che due annunci sono uguali in base alle lore caratterisctiche
        *@param obj è l'oggetto da confrontare
        * @return se gli annunci sono uguali, ritorna true altrimenti false
	   */
	 @Override  
	  public boolean equals(Object obj) {
		  
		  if(this==obj) return true;
		  
		  if(obj==null || this.getClass()!=obj.getClass() ) return false;
		  
		  Annuncio annuncio= (Annuncio)obj;
		  return Double.compare(annuncio.prezzo, prezzo)==0 &&
				  emailUtente.equals(annuncio.emailUtente)&&
				  id.equals(annuncio.id) &&
				  nomeArticolo.equals(annuncio.nomeArticolo)&&
				  paroleChiave.equals(annuncio.paroleChiave);
	  }
	 
	 /**
	  *  metodo che permette all'utente di inserire una nuova parola chiave 
	  * @param nuovaParolaChiave parola chiave da inserire
	  */
	 public void addParolaChiave(String nuovaParolaChiave) {
		  
		 if(nuovaParolaChiave!=null && !nuovaParolaChiave.isBlank()) {
			 ParolaChiave.add(nuovaParolaChiave.trim());    //trim est utilisé pour enlever les espaces blancs inserés par l'utilisateur
			 
		 }
	 }
	 
	 
			  
	  
}
