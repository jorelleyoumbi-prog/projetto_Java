package Interfaccia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import codice.Annuncio;
import codice.Bacheca;
import codice.Eccezioni.AnnuncioDuplicatoException;
import jbook.util.Input;
/**
 * @author Jorelle Madeleine MENGAPTCHE YOUMBI
 *  Classe che gestisce l'interfaccia dell'utente in modo testuale
 */
public class InterfacciaUtente {

	 private static Bacheca bacheca;
	 
	 public static void main(String args[]) {
		 
		 bacheca=new Bacheca(new ArrayList<>());
		 
		  boolean esci=false;
		  
		  System.out.println("--Benvenuto nella bacheca di annunci--");
		  
		  while(!esci) {
			  mostraMenu();
			  
			  System.out.print("Scegli un'opzione: ");
			  String scelta= Input.readString();
			   
			   switch(scelta){
			      
			       case "1":
			    	   agguingiAnnuncio();
			       break; 
			      
			       case "2":
			    	   cercaAnnunci();
			       break;
			       
			       case"3":
			    	   rimuoviAnnuncio();
			       break;
			       
			       case"4":
			    	   mostraAnnunci();
			       break;
			       
			       case"5":
			    	   pulisciBacheca();
			       break;
			       
			       case"6":
			    	   salvaSuFile();
			    	break;
			    	
			       case"7":
			    	   carica_Da_File() ;
			       break;
			    	
			       case"8":
			    	   
			    	   esci=true;
			    	   System.out.println("Uscita dalla bacheca");
			       break;
			       
			       default:
			    	   System.out.println("Opzione non valida. Riprova!!");
			   }
			  
		  }
	 }
	 
	 
	 private static void mostraMenu() {
		 
		 System.out.println("\n---Menu--");
		 
		 System.out.println("1.Agguingi un annuncio");
		 System.out.println("2.Cerca  annunci per parole chiave");
		 System.out.println("3.Rimuovi un  annuncio");
		 System.out.println("4.Mostra tutti gli annunci");
		 System.out.println("5.Pulisci la bacheca");
		 System.out.println("6. Salva su file");
		 System.out.println("7. Carica da file");
		 System.out.println("8. Esci");
		 
	 }
	 
	 private static void  agguingiAnnuncio() {
		 
		 System.out.println("\n-- Agguingi un annuncio--");
		 
		 System.out.print(" Inserisci il tipo  di annuncio(acquisto o vendita): ");
		 String tipoAnnuncio= Input.readString();
		 
		 System.out.print(" ID: ");
		 String id= Input.readString();
		 
		 System.out.print(" Email: ");
		 String emailUtente= Input.readString();
		 
		 System.out.print(" prezzo: ");
		 double prezzo= Input.readDouble();
		 
		 System.out.print(" Nome dell'articolo: ");
		 String nomeArticolo= Input.readString();
		 
		 System.out.print(" Inserisci le parole chiave dell'annuncio: ");
		 String paroleChiave= Input.readString();
		 
		 try {
			 
			    if(tipoAnnuncio.equalsIgnoreCase("vendita")) {
				 
				     System.out.print("\n Data di scadenza(YYYY-MM-DD): ");
				     String dataScadenzaInput= Input.readString();
				     LocalDate dataScadenza= LocalDate.parse(dataScadenzaInput);
				 
				  try {
					bacheca.aggiungiAnnuncio(tipoAnnuncio, id, emailUtente, prezzo, nomeArticolo, paroleChiave, dataScadenza);
				   } catch (AnnuncioDuplicatoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				   }
			    }
			 
			    else if(tipoAnnuncio.equalsIgnoreCase("acquisto")) {
				    try {
					   bacheca.aggiungiAnnuncio(tipoAnnuncio, id, emailUtente, prezzo, nomeArticolo, paroleChiave, null);
				     } catch (AnnuncioDuplicatoException e) {
					    // TODO Auto-generated catch block
					    e.printStackTrace();
				     }
			     }
			     else {
				    System.out.println("Tipo di annuncio non valido");
			     }
		         
			    System.out.println("Annuncio aggiunto con successo");
			 
		   }catch(Exception e) {
			 
			 System.out.println("Errore durante l'agguinta dell'annuncio" + e.getMessage());
		  }
    }
	 
	  
	 private static void  cercaAnnunci() {
		 
		 System.out.println("\n-- Cerca Annunci per parole chiave--");
		 
		 System.out.print("Inserici una parola chiave:");
		 String ParolaChiave= Input.readString();
		 
		 List <Annuncio> risultati = bacheca.cercaArticoliPerParoleChiave(ParolaChiave);
		 
		 if(risultati.isEmpty()) {
			  System.out.println("Nessun annuncio trovata con la parola chiave inserita");
		 }
		 else {
			 
			  System.out.println("Annuncio trovato: ");
			  for(Annuncio annuncio: risultati) {
				  
				  System.out.println(annuncio);
			  }
		 }
	 }
	 
	 private static void rimuoviAnnuncio() {
		 
		 try {
			 
			  System.out.print("Id dell'annuncio da rimuovere: ");
			  String id = Input.readString(); 
			  
			  System.out.print("Email dell'utente associato all'annuncio: ");
			  String emailUtente= Input.readString();
			  
			  bacheca.rimuoviAnnuncio(id, emailUtente);
			  
			  System.out.println("Annuncio rimosso con successo");
			  
			 
		 }catch(Exception e) {
			 System.out.println("Errore durante la rimossione dell'annuncio" +e.getMessage());
		 }
		 
	 }
	 
	 private static void mostraAnnunci() {
		 
		 System.out.println("\n--Lista Annunci nella bacheca--");
		 List<Annuncio> annunci = bacheca.getAnnunci();
		 
		 if(annunci.isEmpty()) {
			 System.out.println("La bacheca degli annunci è vuota");
		 }
		 else {
			 
			 for(Annuncio annuncio:annunci) {
				 
				 System.out.println(annuncio);
			 }
		 }
		 
	 }
	 
	 private static void pulisciBacheca() {
		 
		 System.out.println("\n-- Pulisci la bacheca di annunci--");
		 
		 int annunciPrima=bacheca.getAnnunci().size();
		 bacheca.pulisciBacheca();
		 
		 int annunciDopo=bacheca.getAnnunci().size();
		 
		 System.out.printf("Annunci rimossi: %d\n" ,annunciPrima-annunciDopo);
		 System.out.println("\n Bacheca pulita dagli annunci scaduti");
	 }
	 
	 private static void salvaSuFile() {
		 
		 System.out.println("\n--Salva la bacheca su file--");
		 
		 System.out.print("Inserisci il nome del file su cui salvare la bacheca: ");
		 
		 String nomeFile= Input.readString();
		 
		 try {
			    bacheca.salvaSuFile(nomeFile);
			    System.out.println("Bacheca salvata sul file");
		 }catch(Exception e) {
			 System.out.println("Errore durante il salvataggio"+e.getMessage());
		 }
	 }
	 
	 private static void carica_Da_File() {
		 System.out.println("\n--Carica la bacheca da file--");
		 System.out.print("Inserisci il file da cui caricare la bacheca: ");
		 
		 String nomeFile= Input.readString();
		 
		 try {
			    bacheca.caricaDaFile(nomeFile);
			    System.out.println("Bacheca carica da file");
		 }catch(Exception e) {
			 System.out.println("\n Errore durante il caricamento della bacheca"+e.getMessage());
		 }
	 }
}
