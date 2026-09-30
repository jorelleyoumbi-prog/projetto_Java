package codice;

import java.time.LocalDate;

/** @author JORELLE MADELEINE MENGAPTCHE YOUMBI      20047743
 * La classe AnnuncioVendite extende quella di annuncio e 
 * rappresenta l'annuncio in vendita
 * un annuncio di vendita ha una data di scadenza
 */

public class AnnuncioVendite extends Annuncio{
	
	private final LocalDate dataScadenza;

	/**
	 * Costruttore che crea  un annuncio in vendita
	 * @param id identificatore univoco dell'annuncio
	 * @param emailUtente identifatore dell'utente che ha creato l'annuncio in vendita
	 * @param prezzo il prezzo dell'annuncio
	 * @param nomeArticolo il nome dell'articolo
	 * @param paroleChiave parole chiave legate all'articolo
	 * @param dataScadenza  data di scadenza dell'annuncio in vendita 
	 * @throws IllegalArgumentException se la data di scadenza è vuota per l'annncio in vendita
	 */
	public AnnuncioVendite(String id, String emailUtente, double prezzo, String nomeArticolo, String paroleChiave,LocalDate dataScadenza) {
		super(id, emailUtente, prezzo, nomeArticolo, paroleChiave);
		
		if(dataScadenza==null) {
			
			throw new IllegalArgumentException("La data di scadenza non può essere vuota per un articolo in vendita");
		}
		
		this.dataScadenza = dataScadenza;
	}
	
	/**
	 * restituisce la data di scadenza dell'annuncio in vendita
	 * @return  ritorna la data di scadenza
	 */

	@Override
	public LocalDate getScadenzaData() {	
		
		return dataScadenza;
	}
	
	
	/**
	 * restituisce la descrizione dell'annuncio più la sua data di scadenza
	 * @return ritorna l'annuncio e la sua data di scadenza
	 */
	
	@Override
	public String toString() {
		
		return super.toString()+", dataScadenza= " + dataScadenza;
	}

}
