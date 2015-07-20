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

import animaJDR.Des;
import animaJDR.Valeurs;

public class FenetreCombat extends JFrame
{
	private static final long serialVersionUID = -7062780541679788235L;
	private JPanel contentPane;
	
	private ActionListener listenerRond ;
	private ActionListener listenerBoutons ;
	
	private JComboBox attaquant ;
	private JComboBox defenseur ;
	private JTextField texteDegat;
	private JTextField texteAtt;
	private JTextField texteDef;
	private JButton lancerAtt ;
	private JButton lancerDef ;
	private JButton fightBouton ;
	private JTextField texteIP;

	/**
	 * Constructeur de la fenetre
	 */
	public FenetreCombat(LaFenetre mainFenetre)
	{
		String[] listeNoms = mainFenetre.getListeNomSauve() ;
		setTitle("Anima v0.1 - fenetre de combat");
		setResizable(false);
		
		// Fenetre
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		// Menus deroulants
		defenseur = new JComboBox(listeNoms) ;
		defenseur.setBounds(287, 22, 137, 20);
		getContentPane().add(defenseur);
		
		attaquant = new JComboBox(listeNoms) ;
		attaquant.setBounds(10, 22, 137, 20);
		getContentPane().add(attaquant);
		
		// Champs de texte
		texteDegat = new JTextField();
		texteDegat.setBounds(66, 53, 58, 20);
		contentPane.add(texteDegat);
		texteDegat.setColumns(4);
		
		texteAtt = new JTextField() ;
		texteAtt.setBounds(49, 311, 58, 20) ;
		contentPane.add(texteAtt) ;
		
		texteDef = new JTextField() ;
		texteDef.setBounds(321, 311, 58, 20) ;
		contentPane.add(texteDef) ;
		
		texteIP = new JTextField();
		texteIP.setBounds(321, 53, 58, 20);
		contentPane.add(texteIP);
		texteIP.setColumns(10);
		
		// Etiquettes
		JLabel etiquetteDegat = new JLabel("D\u00E9gats :");
		etiquetteDegat.setBounds(10, 56, 60, 14);
		contentPane.add(etiquetteDegat);
		
		JLabel etiquetteAttaque = new JLabel("Des :");
		etiquetteAttaque.setBounds(10, 311, 46, 14);
		contentPane.add(etiquetteAttaque);
		
		JLabel etiquetteDefense = new JLabel("Des :");
		etiquetteDefense.setBounds(287, 311, 46, 14);
		contentPane.add(etiquetteDefense);
		
		JLabel etiquetteIp = new JLabel("IP :");
		etiquetteIp.setBounds(287, 56, 46, 14);
		contentPane.add(etiquetteIp);
		
		JLabel etiquetteTouche = new JLabel("Touche :");
		etiquetteTouche.setBounds(158, 240, 60, 14);
		contentPane.add(etiquetteTouche);
		
		JLabel affichageTouche = new JLabel("-");
		affichageTouche.setBounds(220, 240, 46, 14);
		contentPane.add(affichageTouche);
		
		JLabel etiquetteDegBilan = new JLabel("D\u00E9gats :");
		etiquetteDegBilan.setBounds(158, 260, 60, 14);
		contentPane.add(etiquetteDegBilan);
		
		JLabel affichageDegat = new JLabel("-");
		affichageDegat.setBounds(220, 260, 46, 14);
		contentPane.add(affichageDegat);
		
		JLabel etiquetteDefBilan = new JLabel("D\u00E9fense :");
		etiquetteDefBilan.setBounds(158, 280, 60, 14);
		contentPane.add(etiquetteDefBilan);
		
		JLabel affichageDefense = new JLabel("-");
		affichageDefense.setBounds(220, 280, 46, 14);
		contentPane.add(affichageDefense);
		
		JLabel etiquetteAttBilan = new JLabel("Attaque :");
		etiquetteAttBilan.setBounds(158, 300, 60, 14);
		contentPane.add(etiquetteAttBilan);
		
		JLabel affichageAttaque = new JLabel("-");
		affichageAttaque.setBounds(220, 300, 46, 14);
		contentPane.add(affichageAttaque);
		
		// Checkboxes
		JCheckBox checkEffet = new JCheckBox("effet (sans d\u00E9gat)");
		checkEffet.setBounds(10, 70, 155, 23);
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
		checkProjectile.setBounds(10, 150, 105, 23) ;
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
		rondBoucl.setBounds(287, 130, 160, 23);
		contentPane.add(rondBoucl);
		
		JRadioButton rondTir = new JRadioButton("tir\u00E9");
		rondTir.setSelected(true);
		rondTir.setEnabled(false);
		rondTir.setBounds(30, 170, 109, 23);
		contentPane.add(rondTir);
		
		JRadioButton rondLance = new JRadioButton("lanc\u00E9");
		rondLance.setEnabled(false);
		rondLance.setBounds(30, 190, 109, 23);
		contentPane.add(rondLance);
		
		// boutons
		lancerAtt = new JButton("Lancer") ;
		lancerAtt.setBounds(10, 340, 137, 20) ;
		contentPane.add(lancerAtt) ;
		
		lancerDef = new JButton("Lancer") ;
		lancerDef.setBounds(287, 340, 137, 20) ;
		contentPane.add(lancerDef) ;
		
		fightBouton = new JButton("Fight!") ;
		fightBouton.setBounds(158, 340, 120, 20);
		contentPane.add(fightBouton);
		
		// Definition des listeners
		listenerRond = new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// gestion de l'unicite du type de defense
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
				
				// gestion du projectile
				if (e.getSource() == checkProjectile)
				{
					if (checkProjectile.isSelected())
					{
						rondLance.setEnabled(true) ;
						rondTir.setEnabled(true) ;
					}
					else
					{
						rondLance.setEnabled(false) ;
						rondTir.setEnabled(false) ;
					}
				}
				
				if (e.getSource() == rondLance)
				{
					rondLance.setSelected(true) ;
					rondTir.setSelected(false) ;
				}
				else if (e.getSource() == rondTir)
				{
					rondLance.setSelected(false) ;
					rondTir.setSelected(true) ;
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
					int defense = mainFenetre.getPersonnageParNom((String)defenseur.getSelectedItem()).getDefense() ;
					boolean maitriseDef = (defense >= 200) ;
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
					
					if (texteIP.getText().length() != 0)
						IP = Integer.parseInt(texteIP.getText()) ;
					
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
					
					// cas d'attaque au projectile
					if (checkProjectile.isSelected())
					{
						// cas d'un tir
						if (rondTir.isSelected())
						{
							// defense avec une parade
							if (rondParade.isSelected())
							{
								if (!maitriseDef && !checkBouclier.isSelected())
									defense += Valeurs.paradeTir ;
								else if (!maitriseDef && checkBouclier.isSelected())
									defense += Valeurs.paradeTirBouclier ;
								else if (maitriseDef && !checkBouclier.isSelected())
									defense += Valeurs.paradeTirMaitre ;
							}
							// defense avec une esquive
							else if (rondEsquive.isSelected())
							{
								if (!maitriseDef)
									defense += Valeurs.esquiveTir ;
							}	
						}
						// cas d'un lance
						else
						{
							// defense avec une parade sans bouclier et sans maitrise
							if (rondParade.isSelected() && !checkBouclier.isSelected() && !maitriseDef)
							{
								defense += Valeurs.paradeLance ;
							}
						}
					}
					
					// --- calculs des effets du combat ---
					touche = attaque > defense ;
					degatsAppliques = ( (attaque-defense-20-IP*10)*degat )/100 ;
					
					// --- affichages ---
					if (touche)
						affichageTouche.setText("oui") ;
					else
						affichageTouche.setText("non") ;
					
					if (!checkEffet.isSelected())
						affichageDegat.setText(String.valueOf(degatsAppliques)) ;
					else
						affichageDegat.setText(String.valueOf(0)) ;
					
					affichageAttaque.setText(String.valueOf(attaque)) ;
					affichageDefense.setText(String.valueOf(defense)) ;
				}
				else if (e.getSource() == lancerAtt)
				{
					texteAtt.setText( String.valueOf(Des.lancerDesClassique(true).getResultat()) ) ;
				}
				else if (e.getSource() == lancerDef)
				{
					texteDef.setText( String.valueOf(Des.lancerDesClassique(true).getResultat()) ) ;
				}
			}
		};
		
		// ajouts des listeners en fin pour permettre la creation de tous les objets
		rondEsquive.addActionListener(listenerRond);
		rondParade.addActionListener(listenerRond);
		rondBoucl.addActionListener(listenerRond);
		checkProjectile.addActionListener(listenerRond);
		rondLance.addActionListener(listenerRond) ;
		rondTir.addActionListener(listenerRond) ;
		fightBouton.addActionListener(listenerBoutons);
		lancerAtt.addActionListener(listenerBoutons);
		lancerDef.addActionListener(listenerBoutons);

	}
}
