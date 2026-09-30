package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import codice.Annuncio;
import codice.AnnuncioAcquisto;
import codice.AnnuncioVendite;
import codice.Bacheca;
import codice.Eccezioni.AnnuncioDuplicatoException;
/**@author Jorelle Madeleine MENGAPTCHE YOUMBI
 *  Classe di test della bacheca
 */

class testBacheca {
	
	@Test
	void testCostruttore() {
		
		Bacheca bacheca = new Bacheca(new ArrayList<>());
		assertNotNull(bacheca.getAnnunci(),"La lista di annunci non dovrebbe essere nulla");
		assertTrue(bacheca.getAnnunci().isEmpty(),"La lista deve essere inizalemente vuota");
	}
   
	@Test
	void testAgguingiAnnuncio() throws AnnuncioDuplicatoException {
		
		List<Annuncio> listaAnnunci= new ArrayList<>();
		Bacheca bacheca = new Bacheca(listaAnnunci);
		
		LocalDate dataScadenza= LocalDate.of(2025, 02, 24);
		
		AnnuncioVendite annuncio = new AnnuncioVendite("1","jorellemaddeleine@gmail.com",35.0,"maglione","vestito,abigliamento",dataScadenza);
		bacheca.aggiungiAnnuncio("vendita","1","jorellemaddeleine@gmail.com",35.0,"maglione","vestito,abigliamento",dataScadenza);
		
		assertTrue(bacheca.getAnnunci().contains(annuncio));
		
		AnnuncioAcquisto annuncio2= new AnnuncioAcquisto("2","charnellemaria@gmail.com",20.0,"pentola","cucina");
		bacheca.aggiungiAnnuncio("acquisto","2","charnellemaria@gmail.com",20.0,"pentola","cucina",dataScadenza );
		
		assertTrue(bacheca.getAnnunci().contains(annuncio2));
	}
	
	@Test
	void testAggiungiAnnuncioDuplicato() {
	    List<Annuncio> listaAnnunci = new ArrayList<>();
	    Bacheca bacheca = new Bacheca(listaAnnunci);

	    LocalDate dataScadenza = LocalDate.of(2025, 3, 30);

	    // Aggiungi un annuncio
	    assertDoesNotThrow(() -> bacheca.aggiungiAnnuncio("vendita", "1", "jorelleyoumbi@gmail.com", 100.0, "talons", "scarpe,donna", dataScadenza));

	    // Prova ad aggiungere lo stesso annuncio
	    Exception exception = assertThrows(AnnuncioDuplicatoException.class, () -> {
	        bacheca.aggiungiAnnuncio("vendita", "1", "jorelleyoumbi@gmail.com", 100.0, "talons", "scarpe,donna", dataScadenza);
	    });

	    assertEquals("Annuncio con ID '1' già presente.", exception.getMessage());
	}

	@Test
	void testTipoAnnuncioNonValido() {
		
		List<Annuncio> listaAnnunci= new ArrayList<>();
		Bacheca bacheca = new Bacheca(listaAnnunci);
		
		LocalDate dataScadenza= LocalDate.of(2025, 02, 24);
		
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,()->{
			
			bacheca.aggiungiAnnuncio("ritiro","1","jorellemaddeleine@gmail.com",35.0,"maglione","vestito,abigliamento",dataScadenza );
				
		});
		
		assertEquals("Tipo di annuncio non valido. Deve essere 'vendita' o 'acquisto' ",thrown.getMessage());
		
		
	}
	
	@Test
	void testRimuoviAnnuncio() throws AnnuncioDuplicatoException{
		
		List<Annuncio> listaAnnunci= new ArrayList<>();
		Bacheca bacheca = new Bacheca(listaAnnunci);
		
		LocalDate dataScadenza= LocalDate.of(2025, 01, 10);
		
		
		
		bacheca.aggiungiAnnuncio("vendita", "2","rihanna@gmail.com",35.0,"fondotinta liquida","makeup,truco",  dataScadenza);
		bacheca.aggiungiAnnuncio("acquisto", "3","brunomars@gmail.com",10.0,"fiori","casa,decorazione",  dataScadenza);
		bacheca.rimuoviAnnuncio("2", "rihanna@gmail.com");
		bacheca.rimuoviAnnuncio("3", "brunomars@gmail.com");
		
		assertEquals(0,bacheca.getAnnunci().size());
		
	}
	
	@Test
	void testCercaArticoliPerParoleChiave() throws AnnuncioDuplicatoException{
		
		List<Annuncio> listaAnnunci= new ArrayList<>();
		Bacheca bacheca = new Bacheca(listaAnnunci);
		
		LocalDate dataScadenza1= LocalDate.of(2025, 02, 28);
		LocalDate dataScadenza2= LocalDate.of(2025, 04, 21);
		
		
		bacheca.aggiungiAnnuncio("vendita", "1","passychlorelle@gmail.com",35.0,"divano","casa,stanza",  dataScadenza1);
		bacheca.aggiungiAnnuncio("vendita", "2","bobmarley@gmail.com",65.0,"armandio","casa,cucina",dataScadenza2 );
		bacheca.aggiungiAnnuncio("acquisto","3","caroline@gmail.com",50.0,"letto","casa,camera",dataScadenza1);
		
		List<Annuncio> risult = bacheca.cercaArticoliPerParoleChiave("casa");
		
		assertEquals(3,risult.size(),"La lista dovrebbe contenere 3 annunci");
	    
		List<Annuncio> nessunRisult = bacheca.cercaArticoliPerParoleChiave("inverno");
		assertEquals(0,nessunRisult.size(),"La lista dei risultati dovrebbe essere vuota");
	}
	
	
	@Test
	void testPulisciBacheca() throws AnnuncioDuplicatoException{
		
		List<Annuncio> listaAnnunci= new ArrayList<>();
		Bacheca bacheca = new Bacheca(listaAnnunci);
		
		LocalDate dataScadenza1= LocalDate.of(2025, 8, 10);
		LocalDate dataScadenza2= LocalDate.of(2025, 1, 21);
		LocalDate dataScadenza3= LocalDate.of(2024, 12, 31);
		
		
		
		bacheca.aggiungiAnnuncio("vendita", "1","rihanna@gmail.com",35.0,"fondotinta liquida","makeup,truco",  dataScadenza1);
		bacheca.aggiungiAnnuncio("vendita", "2","brunomars@gmail.com",10.0,"fiori","casa,decorazione",  dataScadenza2);
		bacheca.aggiungiAnnuncio("vendita", "3","Delaideluca@gmail.com",100.0,"macchina","auto,meccanica",  dataScadenza3);
		bacheca.pulisciBacheca();
		
		
		assertEquals(1,bacheca.getAnnunci().size());
		
	}
	
	@Test
	void testSalvaSuFile() throws IOException,AnnuncioDuplicatoException{
		
		List<Annuncio> listaAnnunci = new ArrayList<>();
		Bacheca bacheca = new Bacheca(listaAnnunci);
		
		LocalDate dataScadenza= LocalDate.of(2025, 4, 21);
		
		bacheca.aggiungiAnnuncio("vendita", "1", "jorelleyoumbi@gmail.com", 15.0, "caricatore USB", "elettronica,tecnologia", dataScadenza);
		bacheca.aggiungiAnnuncio("acquisto", "2", "wilfridmeng@gmail.com", 345.0, "bici", "transporto,strada", dataScadenza);
		bacheca.salvaSuFile("annunci.txt");
		
		
	}
	
	
	


	

}
