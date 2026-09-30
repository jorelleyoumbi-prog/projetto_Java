package test;


import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import codice.AnnuncioAcquisto;
import codice.AnnuncioVendite;

class testAnnuncio {

	@Test
	void testAnnuncioVendite() {
		//test di creazione di annuncio vendite valido
		
		LocalDate dataScadenza=LocalDate.of(2025, 12, 31);
		
		AnnuncioVendite annuncio= new AnnuncioVendite("1","jorelleyoumbi@gmail.com",300.0,"Laptop","tecnologia,elettronica",dataScadenza);
		
		assertEquals("1",annuncio.getId());
		assertEquals("jorelleyoumbi@gmail.com",annuncio.getEmailUtente());
		assertEquals(300.0,annuncio.getPrezzo());
		assertEquals("Laptop",annuncio.getNomeArticolo());
		assertEquals("tecnologia,elettronica",annuncio.getParoleChiave());
		assertEquals(dataScadenza,annuncio.getScadenzaData());
	}
	
	@Test
	void testAnnuncioAnnuncio() {
		//test di creazione di annuncio vendite valido
		
		
		
		AnnuncioAcquisto annuncio= new AnnuncioAcquisto("2","madeleineyoumbi@gmail.com",300.0,"Laptop","tecnologia,elettronica");
		
		assertEquals("2",annuncio.getId());
		assertEquals("madeleineyoumbi@gmail.com",annuncio.getEmailUtente());
		assertEquals(300.0,annuncio.getPrezzo());
		assertEquals("Laptop",annuncio.getNomeArticolo());
		assertEquals("tecnologia,elettronica",annuncio.getParoleChiave());
		
	}
	 

	@Test
	void testDataScadenzaAnnuncioVendite() {
		//test suul'eccezione lanciata sulla data di scadenza dell'annuncio
			
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, ()->{
			
			new AnnuncioVendite("2","mariachiara@gmail.com",50.0,"scarpe con tacchi","abigliamento,scarpe",null);
		});
		
		assertEquals("La data di scadenza non può essere vuota per un articolo in vendita",thrown.getMessage());
	}
	
	@Test
	void testToStringVendite() {
		//test sulla descrizione testuale
			
		LocalDate dataScadenza= LocalDate.of(2025, 8, 8);
		
		AnnuncioVendite annuncio= new AnnuncioVendite("3","rihannaeminem@gmail.com",70.0,"micronde","casa,elettrodomestici",dataScadenza);
		String result= annuncio.toString();
		
		String expected= "Annuncio { id= 3;emailUtente= rihannaeminem@gmail.com;prezzo= 70.0;nomeArticolo= micronde;paroleChiave= casa,elettrodomestici}, dataScadenza= 2025-08-08";
		
		assertEquals(expected,result);
	}
	

	@Test
	void testEquals() {
		//test su Equals 
			
		LocalDate dataScadenza = LocalDate.of(2025, 3, 17);
		
		AnnuncioVendite annuncio1= new AnnuncioVendite("4","munilong@gmail.com",70.0,"bici","auto,strada",dataScadenza);
		AnnuncioVendite annuncio2= new AnnuncioVendite("4","munilong@gmail.com",70.0,"bici","auto,strada",dataScadenza);
		
		assertEquals(annuncio1,annuncio2);
	    
	}
	
	@Test
	void testGetParoleChiave() {
		//test sulle parole chiave
			
		LocalDate dataScadenza = LocalDate.of(2025, 02, 14);
		
		AnnuncioVendite annuncio= new AnnuncioVendite("5","revenge@gmail.com",70.0,"fondotinta liquido","trucco,bellezza",dataScadenza);
		
		assertEquals("trucco,bellezza",annuncio.getParoleChiave());
		
	}
	
	
	@Test
	void testToStringAcquisto() {
		//test sulla descrizione testuale
			
		
		
		AnnuncioAcquisto annuncio= new AnnuncioAcquisto("6","makia@gmail.com",5.0,"guanti","abbigliamento,bellezza");
		String result= annuncio.toString();
		
		String expected= "Annuncio { id= 6;emailUtente= makia@gmail.com;prezzo= 5.0;nomeArticolo= guanti;paroleChiave= abbigliamento,bellezza}Acquisto";
		
		assertEquals(expected,result);
	}
	
	
	@Test
	void testAddParolaChiave() {
		//test sull'aggiunta di una parola chiave
			
		LocalDate dataScadenza= LocalDate.of(2025, 01, 31);
		
		AnnuncioAcquisto annuncio= new AnnuncioAcquisto("7","lauragermano@gmail.com",5.0,"cuffie","elettronica,tecnologia");

		
		 annuncio.addParolaChiave("musica");
		 
		 assertTrue(annuncio.getParolaChiave().contains("musica"));
		 
		 AnnuncioVendite annuncio2 = new AnnuncioVendite("8","coldplay@gmail.com",345.99,"Laptop","elettronica,tecnologia",dataScadenza);
		 
		 annuncio2.addParolaChiave("digitale");
		 assertTrue(annuncio2.getParolaChiave().contains("digitale"));
	}

}
