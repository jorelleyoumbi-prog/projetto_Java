package Interfaccia;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;


import codice.Annuncio;
import codice.Bacheca;

/**@author Jorelle Madeleine MENGAPTCHE YOUMBI   20047743
 * 
 * Classe che sviluppa l'interfaccia grafica della bacheca
 * 
 */
public class InterfacciaUtenteGrafica extends JFrame {
     
	private Bacheca bacheca; 
	private JTextArea displayArea;
	
	public InterfacciaUtenteGrafica() {
		
		 setTitle("Gestione Bacheca Annunci");
		 setSize(600,400);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setLayout(new BorderLayout());
		 
		 bacheca = new Bacheca(new ArrayList<>());
		 
		 //panello del menu 
		 JPanel menuPanel=new JPanel();
		 menuPanel.setLayout(new GridLayout(5,1,10,10));
		 menuPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		 
		 JButton  aggiungiAnnuncioBtn=new JButton("Aggiungi Annuncio");
		 JButton mostraAnnuncionBtn= new JButton("Mostra Annunci");
		 JButton cercaAnnuncioBtn= new JButton("cerca annunci");
		 JButton rimuoviAnnuncioBtn= new JButton("Rimuovi Annuncio");
		 JButton pulisciBachecaBtn = new JButton("Pulisci Bacheca");
		 JButton salvaSuFileBtn= new JButton("Salva su file");
		 JButton caricaDaFile= new JButton("Carica da file");
		 JButton esciBtn= new JButton("Esci");
		 
		 menuPanel.add(aggiungiAnnuncioBtn);
		 menuPanel.add(mostraAnnuncionBtn);
		 menuPanel.add(cercaAnnuncioBtn);
		 menuPanel.add(rimuoviAnnuncioBtn);
		 menuPanel.add(pulisciBachecaBtn);
		 menuPanel.add(salvaSuFileBtn);
		 menuPanel.add(caricaDaFile);
		 menuPanel.add(esciBtn);
		 
		 //Area di visualizzazione degli  annunci
		 
		 displayArea= new JTextArea();
		 displayArea.setEditable(false);
		 
		 JScrollPane scrollPane=new JScrollPane(displayArea);
		 
		 //Agiunta al layout
		 
		add(menuPanel,BorderLayout.WEST);
		add(scrollPane,BorderLayout.CENTER);
		
		//Eventi pulsanti
		
		aggiungiAnnuncioBtn.addActionListener(e->mostraFinestraAggiungiAnnuncio());
		mostraAnnuncionBtn.addActionListener(e->mostraAnnunci());
		cercaAnnuncioBtn.addActionListener(e->cercaAnnunci());
		rimuoviAnnuncioBtn.addActionListener(e->rimuoviAnnuncio());
		pulisciBachecaBtn.addActionListener(e->pulisciBacheca());
		salvaSuFileBtn.addActionListener(e->salvaSuFile());
		caricaDaFile.addActionListener(e->caricaDaFile());
		esciBtn.addActionListener(e-> System.exit(0));
		
		
		setVisible(true);
	}
	
	private void mostraFinestraAggiungiAnnuncio() {
		
		JDialog dialog =new JDialog(this,"Aggiungi Annuncio",true);
		dialog.setSize(400, 300);
		dialog.setLayout(new GridLayout(8,2,5,5));
		
		JTextField tipoField = new JTextField();
		JTextField idField= new JTextField();
		JTextField emailField= new JTextField();
		JTextField prezzoField= new JTextField();
		JTextField nomeArticoloField= new JTextField();
		JTextField paroleChiaveField=new JTextField();
		JTextField dataScadenzaField=new JTextField();
		
		dialog.add(new JLabel("Tipo(acquisto/vendita):"));
		dialog.add(tipoField);
		dialog.add(new JLabel("ID:"));
		dialog.add(idField);
		dialog.add(new JLabel("Email:"));
		dialog.add(emailField);
		dialog.add(new JLabel("Prezzo:"));
		dialog.add(prezzoField);
		dialog.add(new JLabel("NomeArticolo:"));
		dialog.add(nomeArticoloField);
		dialog.add(new JLabel("Parole Chiave:"));
		dialog.add(paroleChiaveField);
		dialog.add(new JLabel("Data Scadenza(YYYY-MM-DD,solo vendita):"));
		dialog.add(dataScadenzaField);
		
		JButton aggiungiBtn = new JButton("Aggiungi");
		JButton annullaBtn = new JButton("Annulla");
		
		aggiungiBtn.addActionListener((ActionEvent e) ->{
			
			try {
				
				 String tipo=tipoField.getText().trim();
				 String id=idField.getText().trim();
				 String email= emailField.getText().trim();
				 double prezzo= Double.parseDouble(prezzoField.getText().trim());
				 String nomeArticolo= nomeArticoloField.getText().trim();
				 String paroleChiave= paroleChiaveField.getText().trim();
				 
				 String dataScadenzaStr=dataScadenzaField.getText().trim();
				 LocalDate dataScadenza = dataScadenzaStr.isEmpty() ? null : LocalDate.parse(dataScadenzaStr);
				 
				 if(tipo.equalsIgnoreCase("vendita")) {
					 bacheca.aggiungiAnnuncio(tipo, id, email, prezzo, nomeArticolo, paroleChiave, dataScadenza);
				 }
				 else if(tipo.equalsIgnoreCase("acquisto")) {
					 bacheca.aggiungiAnnuncio(tipo, id, email, prezzo, nomeArticolo, paroleChiave, dataScadenza);
				 }
				 else {
					 throw new IllegalArgumentException("Tipo di annuncio non valido");
				 }
				 
				 displayArea.append("Annuncio aggiunto con successo\n");
				 dialog.dispose();
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(dialog,"Errore: "+ex.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
			}
		}); 
		
		dialog.add(aggiungiBtn);
		dialog.add(annullaBtn);
		annullaBtn.addActionListener(e->dialog.dispose());
		dialog.setVisible(true);
	}
	
	private void mostraAnnunci() {
		
		displayArea.setText("");
		if(bacheca.getAnnunci().isEmpty()) {
			displayArea.setText("La bacheca è vuota");
		}
		else{
			
			for(Annuncio annuncio:bacheca.getAnnunci()) {
				displayArea.append(annuncio.toString()+"\n");
			}
		} ;
	}
	
	private void cercaAnnunci() {
		String input=JOptionPane.showInputDialog(this, "Inserisci parole chiave(separate da virgola)");
		if(input==null|| input.trim().isEmpty()) {
			return;
		}
		
		String[] paroleChiave=input.split(",");
		java.util.List<Annuncio> risultati= bacheca.cercaArticoliPerParoleChiave(paroleChiave);
		
		displayArea.setText("");
		if(risultati.isEmpty()) {
			displayArea.setText("Nessun annuncio trovato con queste parole chiave");
		}
		else {
			displayArea.append("Risulati della ricerca:\n");
			for(Annuncio annuncio: risultati) {
				displayArea.append(annuncio.toString()+"\n");
			}
		}
	}
	
	private void pulisciBacheca() {
		
		int annunciPrima=bacheca.getAnnunci().size();
		bacheca.pulisciBacheca();
		
		int annunciDopo=bacheca.getAnnunci().size();
		
		displayArea.append(String.format("Pulizia completata. Annunci rimossi: %d\n", annunciPrima - annunciDopo));
    
	}
	
	private void rimuoviAnnuncio() {
		
		String id= JOptionPane.showInputDialog(this, "Inserisci l'ID dell'annuncio da rimuovere");
		String email= JOptionPane.showInputDialog(this, "Inserisci l'email dell'utente dell'annuncio");
		
		if(id==null || id.trim().isEmpty()) {
			return;
		}
		
		if(email==null || email.trim().isEmpty()) {
			return;
		}
		
		boolean rimosso= bacheca.rimuoviAnnuncio(id, email);
		
		if(rimosso) {
			JOptionPane.showInputDialog(this, "Annuncio rimosso con successo");
		}
		else {
			JOptionPane.showInputDialog(this, "Nessun annuncio trovato con questo ID e questo utente");
		}
	}
	
	private void salvaSuFile() {
		try {
			bacheca.salvaSuFile("annunci.txt");
			JOptionPane.showMessageDialog(this, "Annunci caricati con successo");
		}catch(IOException e) {
			JOptionPane.showMessageDialog(this, "Errore durante il caricamento sul file");
		}
	}
	
	private void caricaDaFile() {
		
		try {
			
			bacheca.caricaDaFile("annunci.txt");
			JOptionPane.showMessageDialog(this, "Annunci caricati da file con successo");
		}catch(IOException e) {
			JOptionPane.showMessageDialog(this, "Errore durante il caricamento da file");
		}
	}
	
	private void esci() {
		
		int scelta=JOptionPane.showConfirmDialog(this, "Sei sicuro/a di voler uscire?","Conferma Uscita",JOptionPane.YES_NO_OPTION);
		
		if(scelta==  JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
	public static void  main(String[]args) {
		 
		SwingUtilities.invokeLater(InterfacciaUtenteGrafica::new);
		 
	 }
	 
	 
	 
}
