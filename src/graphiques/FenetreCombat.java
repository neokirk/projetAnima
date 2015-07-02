package graphiques;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

import metaProjet.Debug;
import animaJDR.Des;
import animaJDR.Valeurs;

public class FenetreCombat extends JFrame
{
	private static final long serialVersionUID = -7062780541679788235L;
	private JPanel contentPane;
	
	private ActionListener listenerRond ;
	private ActionListener listenerBoutons ;
	
	private JComboBox<String> attaquant ;
	private JComboBox<String> defenseur ;
	private JTextField texteDegat;
	private JTextField texteAtt;
	private JTextField texteDef;
	private JButton lancerAtt ;
	private JButton lancerDef ;
	private JButton fightBouton ;

	/**
	 * Constructeur de la fenetre
	 */
	public FenetreCombat(LaFenetre mainFenetre)
	{
		String[] listeNoms = mainFenetre.getListeNomSauve() ;
		setTitle("Anima v0.1 - fenetre de combat");
		setResizable(false);
		
		// Fenetre
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		// Menus déroulants
		defenseur = new JComboBox<String>(listeNoms) ;
		defenseur.setBounds(287, 22, 137, 20);
		getContentPane().add(defenseur);
		
		attaquant = new JComboBox<String>(listeNoms) ;
		attaquant.setBounds(10, 22, 137, 20);
		getContentPane().add(attaquant);
		
		// Champs de texte
		texteDegat = new JTextField();
		texteDegat.setBounds(66, 53, 58, 20);
		contentPane.add(texteDegat);
		texteDegat.setColumns(4);
		
		texteAtt = new JTextField() ;
		texteAtt.setBounds(66, 200, 58, 20) ;
		contentPane.add(texteAtt) ;
		
		texteDef = new JTextField() ;
		texteDef.setBounds(330, 200, 58, 20) ;
		contentPane.add(texteDef) ;
		
		// Etiquettes
		JLabel etiquetteDegat = new JLabel("Dégats :");
		etiquetteDegat.setBounds(10, 56, 46, 14);
		contentPane.add(etiquetteDegat);
		
		JLabel etiquetteAttaque = new JLabel("Des :");
		etiquetteAttaque.setBounds(10, 200, 46, 14);
		contentPane.add(etiquetteAttaque);
		
		JLabel etiquetteDefense = new JLabel("Des :");
		etiquetteDefense.setBounds(287, 200, 46, 14);
		contentPane.add(etiquetteDefense);
		
		// Checkboxes
		JCheckBox checkEffet = new JCheckBox("effet (sans dégat)");
		checkEffet.setBounds(10, 70, 125, 23);
		contentPane.add(checkEffet);
		
		JCheckBox checkSurprise = new JCheckBox("surprise");
		checkSurprise.setBounds(10, 90, 97, 23);
		contentPane.add(checkSurprise);
		
		JCheckBox checkDeFlanc = new JCheckBox("de flanc");
		checkDeFlanc.setBounds(10, 110, 97, 23);
		contentPane.add(checkDeFlanc);
		
		JCheckBox checkDeDos = new JCheckBox("de dos");
		checkDeDos.setBounds(10, 130, 97, 23);
		contentPane.add(checkDeDos);
		
		JCheckBox checkProjectile = new JCheckBox("projectile");
		checkProjectile.setBounds(10, 150, 97, 23);
		contentPane.add(checkProjectile);
		
		JCheckBox checkBouclier = new JCheckBox("bouclier");
		checkBouclier.setBounds(287, 70, 97, 23);
		contentPane.add(checkBouclier);
		
		JRadioButton rondParade = new JRadioButton("parade");
		rondParade.setBounds(287, 90, 109, 23);
		contentPane.add(rondParade);
		rondParade.setSelected(true) ;
		
		JRadioButton rondEsquive = new JRadioButton("esquive");
		rondEsquive.setBounds(287, 110, 109, 23);
		contentPane.add(rondEsquive);
		
		JRadioButton rondBoucl = new JRadioButton("bouclier surnaturel");
		rondBoucl.setBounds(287, 130, 150, 23);
		contentPane.add(rondBoucl);
		
		// boutons
		lancerAtt = new JButton("Lancer") ;
		lancerAtt.setBounds(10, 230, 137, 20) ;
		contentPane.add(lancerAtt) ;
		
		lancerDef = new JButton("Lancer") ;
		lancerDef.setBounds(287, 230, 137, 20) ;
		contentPane.add(lancerDef) ;
		
		fightBouton = new JButton("Fight!") ;
		fightBouton.setBounds((10 + 287)/2 + 10, 230, 120, 20);
		contentPane.add(fightBouton);
		
		// Definition des listeners
		listenerRond = new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource() == rondBoucl)
				{
					rondEsquive.setSelected(false) ;
					rondParade.setSelected(false) ;
					rondBoucl.setSelected(true) ;
				}
				else if (e.getSource() == rondEsquive)
				{
					rondBoucl.setSelected(false) ;
					rondParade.setSelected(false) ;
					rondEsquive.setSelected(true) ;
				}
				else if (e.getSource() == rondParade)
				{
					rondEsquive.setSelected(false) ;
					rondBoucl.setSelected(false) ;
					rondParade.setSelected(true) ;
				}
			}
		};
		
		
		listenerBoutons = new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource() == fightBouton)
				{
					int attaque = mainFenetre.getPersonnageParNom((String)attaquant.getSelectedItem()).getAttaque() ;
					int defense = mainFenetre.getPersonnageParNom((String)defenseur.getSelectedItem()).getDefense() ; ;
					int degat = 0 ;
					int IP = 0 ;
					int degatsAppliques = 0 ;
					boolean touche = false ;
					
					if (texteAtt.getText().length() != 0)
						attaque += Integer.parseInt(texteAtt.getText()) ;
					
					if (texteDef.getText().length() != 0)
						defense += Integer.parseInt(texteDef.getText()) ;
					
					if (texteDegat.getText().length() != 0)
						degat = Integer.parseInt(texteDegat.getText()) ;
					
					if (checkSurprise.isSelected())
						defense += Valeurs.malusSurprise ;
					
					// traite les cas ou le defenseur applique les regles standardes de combat
					if (!rondBoucl.isSelected())
					{
						if (checkDeFlanc.isSelected())
							defense += Valeurs.malusDefFlanc ;
						if (checkDeDos.isSelected())
							defense += Valeurs.malusDefDos ;
					}
					
					// calculs des effets du combat
					touche = attaque > defense ;
					degatsAppliques = ( (attaque-defense-20-IP*10)*degat )/100 ;
					Debug.afficher(degatsAppliques);
				}
				else if (e.getSource() == lancerAtt)
				{
					texteAtt.setText( String.valueOf(Des.lancerDesClassique(true)) ) ;
				}
				else if (e.getSource() == lancerDef)
				{
					texteDef.setText( String.valueOf(Des.lancerDesClassique(true)) ) ;
				}
			}
		};
		
		rondEsquive.addActionListener(listenerRond);
		rondParade.addActionListener(listenerRond);
		rondBoucl.addActionListener(listenerRond);
		fightBouton.addActionListener(listenerBoutons);
		lancerAtt.addActionListener(listenerBoutons);
		lancerDef.addActionListener(listenerBoutons);
	}
}
