package graphiques;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import metaProjet.Debug;
import animaJDR.Des;
import animaJDR.GestionnairePersonnage;
import animaJDR.ListeResistances;
import animaJDR.Personnage;
import animaJDR.Resistances;
import graphiques.Sauvegarde;


public class LaFenetre extends JFrame
{

	////////////////////////////////////////////////
	/////////////// ATTRIBUTS //////////////////////
	////////////////////////////////////////////////
	
	
	
	private static final long serialVersionUID = -1757789564131667227L;
	
	private JTextField texteNom ;
	private JTextField texteNiveau ;
	private JTextField texteObservation ;
	private JTextField texteInitiative ;
	private JTextField texteAttaque ;
	private JTextField texteDefense ;
	private JTextField texteRPhy ;
	private JTextField texteRMal ;
	private JTextField texteRPoi ;
	private JTextField texteRPsy ;
	private JTextField texteRMys ;
	
	private JCheckBox chckbxJetOuvert ;
	private JTextArea zoneConsole ;
	private JComboBox<String> menuDeroulant ;
	
	private ActionListener clickBoutonD100 ;
	private ActionListener clickBoutonAjout ;
	private ActionListener clickBoutonSuppr ;
	private ActionListener clickListe ;
	private ActionListener clickCombat ;
	private KeyListener entrerCmd ;
	
	private GestionnairePersonnage listePerso ;

	private JTextField zoneCommande;
	private JScrollPane defileur ;
	
	public final String init_liste_value = "Nouv. Perso." ;
	
	////////////////////////////////////////////////
	//////////////// METHODES //////////////////////
	////////////////////////////////////////////////
	
	
	/**
	 * Lance l'application
	 */
	public static void main(String[] args)
	{	
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{	
				try
				{
					LaFenetre frame = new LaFenetre();
					frame.setSize(650, 510);
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	private boolean JTextFieldEstVide(JTextField texteField)
	{
		boolean resultat ;
		if(texteField.getText().length() == 0)
			resultat = true ;
		else
			resultat = false ;
		
		return resultat ;
	}
	
	/*
	 * 
	 */
	private void ajouterTexteConsole(String texte)
	{
		this.zoneConsole.setText(this.zoneConsole.getText() + texte + "\r\n");
	}
	
	/*
	 * 
	 */
	private void ajouterPersonnage()
	{
		Personnage perso = null ;
		Debug.afficher(String.valueOf(JTextFieldEstVide(texteRMal)));
		JTextFieldEstVide(texteRMys) ;
		
		// si les resistances sont disponibles
		if (!JTextFieldEstVide(texteRMal) && !JTextFieldEstVide(texteRMys) && !JTextFieldEstVide(texteRPhy)
				&& !JTextFieldEstVide(texteRPoi) && !JTextFieldEstVide(texteRPsy))
		{
			// creation de l'objet resistances
			Resistances res = new Resistances( Integer.valueOf(texteRPhy.getText()), Integer.valueOf(texteRMal.getText()),
					Integer.valueOf(texteRPoi.getText()), Integer.valueOf(texteRPsy.getText()), Integer.valueOf(texteRMys.getText()) ) ;
			
			// creation du personnage avec resistances
			perso = new Personnage(texteNom.getText(), Integer.valueOf(texteNiveau.getText()),
					Integer.valueOf(texteInitiative.getText()), Integer.valueOf(texteAttaque.getText()),
					Integer.valueOf(texteDefense.getText()), Integer.valueOf(texteObservation.getText()), res) ;
		}
		// sinon la creation se fait de manière classique
		else
		{
			// creation du personnage sans resistance
			perso = new Personnage(texteNom.getText(), Integer.valueOf(texteNiveau.getText()),
					Integer.valueOf(texteInitiative.getText()), Integer.valueOf(texteAttaque.getText()),
					Integer.valueOf(texteDefense.getText()), Integer.valueOf(texteObservation.getText())) ;
		}
		
		listePerso.ajouterPersonnage(perso) ;
		menuDeroulant.addItem(perso.getNom());
		menuDeroulant.setSelectedItem(perso.getNom());
		Sauvegarde.sauvegarder(listePerso) ;
	}
	
	/*
	 * 
	 */
	private void supprimerPersonnage()
	{
		String nom = (String) menuDeroulant.getSelectedItem() ;
		if (nom != init_liste_value)
		{
			menuDeroulant.removeItem(nom) ;
			listePerso.supprimerPersonnage(nom) ;
			afficherPersonnage((String)menuDeroulant.getSelectedItem());
			Sauvegarde.sauvegarder(listePerso) ;
		}
	}
	
	/*
	 * 
	 */
	private void afficherPersonnage(String nom)
	{
		Personnage personnage = this.listePerso.getPersoParNom(nom) ;
		if (personnage != null)
		{
			setTextesPrincipaux(nom, String.valueOf(personnage.getNiveau()), String.valueOf(personnage.getInitiative()), 
					String.valueOf(personnage.getAttaque()), String.valueOf(personnage.getDefense()),
					String.valueOf(personnage.getObservation())) ;
			
			if (personnage.possedeResistance())
			{
				setTextesResistances(String.valueOf(personnage.getResistance(ListeResistances.ResPhy)), 
						String.valueOf(personnage.getResistance(ListeResistances.ResMal)),
						String.valueOf(personnage.getResistance(ListeResistances.ResPoi)),
						String.valueOf(personnage.getResistance(ListeResistances.ResPsy)),
						String.valueOf(personnage.getResistance(ListeResistances.ResMys)));
			}
			else
			{
				setTextesResistances("", "", "", "", "") ;
			}
		}
		else
		{
			setTextesPrincipaux("", "", "", "", "", "") ;
			setTextesResistances("", "", "", "", "") ;
		}
	}

	/*
	 * Donne l'etat de la checkbox pour les jets ouverts du D100
	 */
	public boolean getJetOuvert()
	{
		return this.chckbxJetOuvert.isSelected() ;
	}
	
	/*
	 * Retourne le nombre de personnages enregistres
	 */
	public int getNombrePerso()
	{
		return listePerso.getNombrePersonnages() ;
	}
	
	/*
	 * 
	 */
	public Personnage getPersonnageParNom(String nom)
	{
		Personnage personnage = null ;
		personnage = listePerso.getPersoParNom(nom) ;
		
		return personnage ;
	}
	
	/*
	 * Retourne la liste des noms enregistres
	 */
	public String[] getListeNomSauve()
	{
		return listePerso.getListeNoms() ;
	}
	
	/*
	 * Methode qui retourne le nom actuellement selectionne dans menuDeroulant
	 */
	public String getNomListeSelection()
	{
		return (String)menuDeroulant.getSelectedItem() ;
	}
	
	/*
	 * Methode qui ajoute un ensemble de String au menu déroulant
	 */
	private void ajouterListeNomMenuDeroulant(String[] noms)
	{
		for (int i=0; i<getNombrePerso(); i++)
		{
			menuDeroulant.addItem(noms[i]) ;
		}
	}
	
	/*
	 * 
	 */
	public int getInitiative()
	{
		return Integer.valueOf(texteInitiative.getText()) ;
	}
	
	/*
	 * 
	 */
	public void effacerZoneCommande()
	{
		zoneCommande.setText("") ;
	}
	
	/*
	 * Setter global pour les champs de texte
	 */
	public void setTextesPrincipaux(String nom, String niveau, String initiative, String attaque, String defense, String observation)
	{
		texteNom.setText(nom) ;
		texteNiveau.setText(niveau) ;
		texteInitiative.setText(initiative) ;
		texteAttaque.setText(attaque) ;
		texteDefense.setText(defense) ;
		texteObservation.setText(observation) ;
	}
	
	/*
	 * 
	 */
	public void setTextesResistances(String RPhy, String RMal, String RPoi, String RPsy, String RMys)
	{
		texteRPhy.setText(RPhy) ;
		texteRMal.setText(RMal) ;
		texteRPoi.setText(RPoi) ;
		texteRPsy.setText(RPsy) ;
		texteRMys.setText(RMys) ;
	}
	
	///// --------------------------------- ///// ------------------------------------- /////
	
	/**
	 * Constructeur de la classe LaFenetre.
	 * Creer la fenetre et tous ses composants.
	 */
	public LaFenetre()
	{
		setTitle("Anima v0.1");
		setResizable(false);
		getContentPane().setLayout(null);
		
		listePerso = null ;
		listePerso = Sauvegarde.charger() ;
		if (listePerso == null)
			listePerso = new GestionnairePersonnage() ;

		// ---------- Evenements ------- //

		clickBoutonD100 = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				boolean ouvert = LaFenetre.this.getJetOuvert() ;
				LaFenetre.this.ajouterTexteConsole("D100 : " + String.valueOf(Des.lancerDesClassique(ouvert)));
			}
		};

		clickBoutonAjout = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				LaFenetre.this.ajouterPersonnage() ;
			}
		};

		clickBoutonSuppr = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				LaFenetre.this.supprimerPersonnage() ;

			}
		};

		clickListe = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				afficherPersonnage((String) menuDeroulant.getSelectedItem());
			}
		};
		
		clickCombat = new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e)
			{
				FenetreCombat fenetreCombat = new FenetreCombat(LaFenetre.this) ;
				fenetreCombat.setVisible(true);
			}
		};

		entrerCmd = new KeyListener()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				codesCommandes code ;

				// si la touche "entrï¿½e" est appuyï¿½e
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					code = Commandes.lireCommande(LaFenetre.this.zoneCommande.getText()) ;
					LaFenetre.this.ajouterTexteConsole(Commandes.executerCommande(code, LaFenetre.this)) ;
					LaFenetre.this.effacerZoneCommande() ;
				}
			}

			@Override
			public void keyReleased(KeyEvent e){}
			@Override
			public void keyTyped(KeyEvent e){}
		};

		// ---------- Console ---------- //
		
		zoneConsole = new JTextArea() ;
		zoneConsole.setEditable(false);
		zoneConsole.setLineWrap(true) ;
		//zoneConsole.setBounds(10, 284, 624, 147) ;
		//getContentPane().add(zoneConsole) ;
		
		defileur = new JScrollPane() ;
		defileur.setBounds(10, 284, 624, 147) ;
		defileur.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		getContentPane().add(defileur) ;
		defileur.setViewportView(zoneConsole) ;
		
		zoneCommande = new JTextField();
		zoneCommande.setBounds(10, 442, 624, 29);
		zoneCommande.addKeyListener(entrerCmd) ;
		getContentPane().add(zoneCommande);
		
		// ---------- Autres ---------- //
		
		chckbxJetOuvert = new JCheckBox("jet ouvert");
		chckbxJetOuvert.setSelected(true);
		chckbxJetOuvert.setBounds(80, 241, 97, 23);
		getContentPane().add(chckbxJetOuvert);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(396, 11, 1, 223);
		getContentPane().add(separator);
		
		
		
		// ---------- Boutons ---------- //
		
		JButton boutonSauver = new JButton("Enregistrer") ;
		boutonSauver.setBounds(10, 11, 137, 23) ;
		boutonSauver.addActionListener(clickBoutonAjout) ;
		getContentPane().add(boutonSauver) ;
		
		JButton boutonSupprimer = new JButton("Supprimer") ;
		boutonSupprimer.setBounds(10, 51, 137, 23) ;
		boutonSupprimer.addActionListener(clickBoutonSuppr) ;
		getContentPane().add(boutonSupprimer) ;
		
		JButton boutonD10 = new JButton("D10");
		boutonD10.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0)
			{
				zoneConsole.setText(zoneConsole.getText() + "D10 : " + String.valueOf(Des.d10()) + "\n" );
			}
		});
		boutonD10.setBounds(10, 211, 60, 23);
		getContentPane().add(boutonD10);
		
		JButton boutonD100 = new JButton("D100");
		boutonD100.addActionListener(clickBoutonD100) ;
		boutonD100.setBounds(80, 211, 67, 23);
		getContentPane().add(boutonD100);
		
		JButton boutonCombat = new JButton("Combat") ;
		boutonCombat.setBounds(10, 153, 137, 47) ;
		boutonCombat.addActionListener(clickCombat) ;
		getContentPane().add(boutonCombat) ;
		
		// ---------- Etiquettes (JLabel) ---------- //
		
		JLabel etiquetteNom = new JLabel("Nom :");
		etiquetteNom.setBounds(191, 9, 75, 26);
		getContentPane().add(etiquetteNom);
		
		JLabel etiquetteNiveau = new JLabel("Niveau :");
		etiquetteNiveau.setBounds(191, 49, 75, 26);
		getContentPane().add(etiquetteNiveau);
		
		JLabel etiquetteInitiative = new JLabel("Initiative :");
		etiquetteInitiative.setBounds(191, 89, 75, 26);
		getContentPane().add(etiquetteInitiative);
		
		JLabel etiquetteAttaque = new JLabel("Attaque :");
		etiquetteAttaque.setBounds(191, 129, 75, 26);
		getContentPane().add(etiquetteAttaque);
		
		JLabel etiquetteDefense = new JLabel("Defense :");
		etiquetteDefense.setBounds(191, 169, 75, 26);
		getContentPane().add(etiquetteDefense);
		
		JLabel etiquetteObservation = new JLabel("Observation :");
		etiquetteObservation.setBounds(191, 209, 75, 26);
		getContentPane().add(etiquetteObservation);
		
		JLabel etiquetteRPhy = new JLabel("Res. Phy :");
		etiquetteRPhy.setBounds(420, 9, 75, 26);
		getContentPane().add(etiquetteRPhy);
		
		JLabel etiquetteRMal = new JLabel("Res. Mal :");
		etiquetteRMal.setBounds(420, 49, 75, 26);
		getContentPane().add(etiquetteRMal);
		
		JLabel etiquetteRPoi = new JLabel("Res. Poi :");
		etiquetteRPoi.setBounds(420, 89, 75, 26);
		getContentPane().add(etiquetteRPoi);
		
		JLabel etiquetteRPsy = new JLabel("Res. Psy :");
		etiquetteRPsy.setBounds(420, 129, 75, 26);
		getContentPane().add(etiquetteRPsy);
		
		JLabel etiquetteRMys = new JLabel("Res. Mys :");
		etiquetteRMys.setBounds(420, 169, 75, 26);
		getContentPane().add(etiquetteRMys);
		
		// ---------- saisies ---------- //
		
		texteNom = new JTextField();
		texteNom.setBounds(276, 12, 100, 20);
		getContentPane().add(texteNom);
		texteNom.setColumns(10);
		
		texteNiveau = new JTextField();
		texteNiveau.setBounds(276, 52, 40, 20);
		getContentPane().add(texteNiveau);
		
		texteInitiative = new JTextField();
		texteInitiative.setBounds(276, 92, 40, 20);
		getContentPane().add(texteInitiative);
		
		texteAttaque = new JTextField();
		texteAttaque.setBounds(276, 132, 40, 20);
		getContentPane().add(texteAttaque);
		
		texteDefense = new JTextField();
		texteDefense.setBounds(276, 172, 40, 20);
		getContentPane().add(texteDefense);
		
		texteObservation = new JTextField();
		texteObservation.setBounds(276, 212, 40, 20);
		getContentPane().add(texteObservation);
		
		texteRPhy = new JTextField();
		texteRPhy.setBounds(490, 12, 40, 20);
		getContentPane().add(texteRPhy);
		
		texteRMal = new JTextField();
		texteRMal.setBounds(490, 52, 40, 20);
		getContentPane().add(texteRMal);
		
		texteRPoi = new JTextField();
		texteRPoi.setBounds(490, 92, 40, 20);
		getContentPane().add(texteRPoi);
		
		texteRPsy = new JTextField();
		texteRPsy.setBounds(490, 132, 40, 20);
		getContentPane().add(texteRPsy);
		
		texteRMys = new JTextField();
		texteRMys.setBounds(490, 172, 40, 20);
		getContentPane().add(texteRMys);
		
		// ---------- liste ---------- //
		
		String[] menuDeroulantInit = {init_liste_value} ;
		menuDeroulant = new JComboBox<String>(menuDeroulantInit);
		menuDeroulant.setBounds(10, 92, 137, 20);
		menuDeroulant.addActionListener(clickListe) ;
		getContentPane().add(menuDeroulant);
		ajouterListeNomMenuDeroulant(getListeNomSauve()) ;
	}
}
