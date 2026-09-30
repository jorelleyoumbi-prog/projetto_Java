package codice;

import java.time.LocalDate;

/**@author JORELLE MADELEINE MENGAPTCHE YOUMBI 20047743
 * classe che rappresenta un annuncio di acquisto
 * Un annuncio di acquisto non ha data di scadenza
 */

public class AnnuncioAcquisto  extends Annuncio{
	
	/**
	 * 
	 * @param id  identificatore univoco dell'annuncio
	 * @param emailUtente identifatore dell'utente che ha creato l'annuncio in vendita
	 * @param prezzo il prezzo dell'annuncio
	 * @param nomeArticolo il nome dell'articolo
	 * @param paroleChiave parole chiave legate all'articolo
	 * @throws IllegalArguemntException se uno dei parametri non è valido
	 */

	public AnnuncioAcquisto(String id, String emailUtente, double prezzo, String nomeArticolo, String paroleChiave) {
		super(id, emailUtente, prezzo, nomeArticolo, paroleChiave);
		// TODO Auto-generated constructor stub
	}

	/**
	 *  restitisce null perché l'annuncio di acquisto non ha data di scadenza
	 *  @return ritorna null
	 */
	@Override
	public LocalDate getScadenzaData() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/** restituisce la descrizione testuale dell'annuncio di acquisto
	 * @return ritorna la stringa dell'annuncio di acquisto
	 */
	@Override
	public String toString() {
		return super.toString()+"Acquisto";
	}

}
