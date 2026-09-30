package codice;
/**@author Jorelle Madeleine Youmbi Mengaptche   20047743
 *  Classe contenende le diverse eccezioni che si possono trovare durante la gestione della bacheca di annunci
 */
public class Eccezioni {
     /**
      * Eccezione che viene lanciata quando si tenta di aggiungere un annuncio già presente bella bacheca di annunci
      */
	 public static class AnnuncioDuplicatoException extends  Exception{
		
		 /**costruttore dell'eccezione AnnuncioDuplicatoException
		  * 
		  * @param messaggio  messaggio che descrive l'eccezione
		  */
		 public AnnuncioDuplicatoException(String messaggio) {
			  super(messaggio);
			 
		 }
		 
		 
	 }
}
