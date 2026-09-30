package codice;

//import java.util.Objects;

/** @author Jorelle Madeleine MENGAPTCHE YOUMBI 20047743
 * La classe Utente rappresenta uogni utente del sistema. 
 * ogni utente è identificato dal suo nome e da un'unica email
*/
public class Utente {
   
	private  final String email;
	private  String nome;
	
	 
	 /**  
	  * Costrutoore della classe Utente che inizializza i campi nome e email
	  * 
	  * @param email l'email univoca dell'utente
	  * @param nome  nome dell'utente
	  * 
	  * @throws IllegalArgumentException lancia ecccezioni se il nome o l'email sono vuoti o null oppure se l'email è in un formato non coretto
	  * 
	  */
       public Utente(String email, String nome) {
		 
    	 if(email==null || email.isBlank()|| !email.contains("@")) {
    		 
    		 throw new IllegalArgumentException("Email non valida");
    	 }
    	 
    	 if(nome==null || nome.isBlank()) {
    		 
    		 throw new IllegalArgumentException("Nome non valido");
    	 }
		 this.email=email;
		 this.nome=nome;	
		 	 
	   }
       
       /**
        * recupera l'email dell'utente
        * @return ritorna l'email dell'utente
        */
       
       public String getEmail() {
    	   
    	   return email;
       }
	 
       /**
        * recupera il nome  dell'utente
        * @return ritorna il nome dell'utente
        */
       
       public String getNome() {
    	   
    	   return nome;
       }
       
       /**
        * modifica il nome  dell'utente(nuovo nome)
        * @throws IllegalArgumentException se il nome è vuoto o null
        * 
        */
       
       
       public void setNome(String nome) {
    	   
    	   if(nome==null || nome.isBlank()) {   //la methode isBlank è usato al posto di isEmpty e controlle si la stringa est vide ou contient des espaces blancs
    		   
    		   throw new IllegalArgumentException("Il nome non può essere vuoto o null");
    	   }
    	   this.nome=nome;
       }
       
       /**
        * verifica che due utenti sono uguali in base all'email
        *@param obj è l'oggetto da confrontare
        * @return se gli oggetti sono uguali, ritorna true altrimenti false
        */
       
       
       @Override
       public boolean equals(Object obj) {
    	   
    	   if(this== obj) return true; // je vérifie si les riferimenti sont égaux
    	   
    	   if (obj==null || obj.getClass()!=this.getClass()) return false; //je vérifie s'ils appartienent alla meme classe
    	   
    	   Utente utente= (Utente) obj;
    	   
    	    return email.equals(utente.email);  
       }
       
      
       /**
        * restituisce l'utente in modo testuale
        *
        * @return ritorna la stringa utente con email e nome
        */
       
       @Override
       public String toString() {
    	   
    	   return "Utente{" + "email= " +email +","+ "nome= " +nome + "}"; 
       }
       
       
}
