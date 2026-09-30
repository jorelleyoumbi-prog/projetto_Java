package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import codice.Utente;

class testUtente {
	

	@Test
	public void testCostruttore() {
		 Utente utente = new Utente("jorelleyoumbi@gmail.com","Jorelle Madeleine");
	  assertEquals("jorelleyoumbi@gmail.com",utente.getEmail());
	  assertEquals("Jorelle Madeleine",utente.getNome());

	}

	@Test
	public void testUtenteValido() {
		
		//utenti con nomi e email validi sono creati correttamente
		Utente u1 = new Utente("jorelleyoumbi@gmail.com","Jorelle Youmbi"); 
		
		assertNotNull(u1);
		
		assertEquals("jorelleyoumbi@gmail.com",u1.getEmail());
		assertEquals("Jorelle Youmbi",u1.getNome()); 
		
		Utente u2= new Utente("soniayetega@gmail.com","Sonia Yetega");
		
		assertNotNull(u2);
		
		assertEquals("soniayetega@gmail.com",u2.getEmail());
		assertEquals("Sonia Yetega",u2.getNome());

	}
	
	//lancia eccezione quando l'email è vuota
	@Test
	public void testEmailVuoto() {
		
      IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
		      new Utente("", "Eli Toukem");
	  });
	  assertEquals("Email non valida", thrown.getMessage());

	}
	
	// lancia eccezione quando il formato dell'email non è corretto(senza @)
	@Test
	public void testEmailInvalido() {
		
      IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
		      new Utente("Elitoukem.com", "Eli Toukem");
	  });
	  assertEquals("Email non valida", thrown.getMessage());	

	}
	
	//lancia l'eccezione quando il campo nome è vuoto
	@Test
	public void testNomeVuoto() {
		
      IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
		      new Utente("elvire@gmail.com", " ");
	  });
	  assertEquals("Nome non valido", thrown.getMessage());

	}
	
	// fa il test su due utente a partire dell'email
	@Test
	public void testEquals() {
		
     Utente u1= new Utente("tiakolamelo@gmail.com","Tiakola Melo");
     
     Utente u2= new Utente("gimswarrano@gmail.com","Maitre Gims");
     
     Utente u3= new Utente("tiakolamelo@gmail.com","Niska Mariano");
     
     assertEquals(u1,u3);
     assertNotEquals(u2,u3);
     assertNotEquals(u1,u2);
	}
	
	 
	@Test
	public void testToString() {
		
		String nome= "Jorelle Youmbi";
		String email= "jorelleyoumbi@gmail.com";
	     
		Utente u= new Utente(email,nome); 
		
		String result=u.toString();
	     String expected="Utente{email= jorelleyoumbi@gmail.com,nome= Jorelle Youmbi}";
	     
	     assertEquals(expected,result);
	}
}
