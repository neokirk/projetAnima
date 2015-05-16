package graphiques;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

public class FenetreCombat extends JFrame
{
	private static final long serialVersionUID = -7062780541679788235L;
	private JPanel contentPane;
	
	private JComboBox<String> attaquant ;
	private JComboBox<String> defenseur ;
	private JTextField textField;

	/**
	 * Constructeur de la fenètre
	 */
	public FenetreCombat(LaFenetre mainFenetre)
	{
		String[] listeNoms = mainFenetre.getListeNomSauve() ;
		
		// Fenetre
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		// Menus déroulants
		defenseur = new JComboBox<String>(listeNoms) ;
		defenseur.setBounds(10, 22, 137, 20);
		getContentPane().add(defenseur);
		
		attaquant = new JComboBox<String>(listeNoms) ;
		attaquant.setBounds(287, 22, 137, 20);
		getContentPane().add(attaquant);
		
		// Champs de texte
		textField = new JTextField();
		textField.setBounds(66, 53, 58, 20);
		contentPane.add(textField);
		textField.setColumns(4);
		
		// Etiquettes
		JLabel etiquetteDegat = new JLabel("Dégats :");
		etiquetteDegat.setBounds(10, 56, 46, 14);
		contentPane.add(etiquetteDegat);
		
		// Checkboxes
		JCheckBox checkEffet = new JCheckBox("effet (sans dégat)");
		checkEffet.setBounds(10, 70, 125, 23);
		contentPane.add(checkEffet);
		
		JCheckBox checkSurprise = new JCheckBox("surprise");
		checkSurprise.setBounds(10, 90, 97, 23);
		contentPane.add(checkSurprise);
		
		JCheckBox chckbxDeFlanc = new JCheckBox("de flanc");
		chckbxDeFlanc.setBounds(10, 110, 97, 23);
		contentPane.add(chckbxDeFlanc);
		
		JCheckBox chckbxDeDos = new JCheckBox("de dos");
		chckbxDeDos.setBounds(10, 130, 97, 23);
		contentPane.add(chckbxDeDos);
		
		JCheckBox chckbxProjectile = new JCheckBox("projectile");
		chckbxProjectile.setBounds(10, 150, 97, 23);
		contentPane.add(chckbxProjectile);
		
		JCheckBox checkBouclier = new JCheckBox("bouclier");
		checkBouclier.setBounds(287, 70, 97, 23);
		contentPane.add(checkBouclier);
		
		JRadioButton rondParade = new JRadioButton("parade");
		rondParade.setBounds(287, 90, 109, 23);
		contentPane.add(rondParade);
		
		JRadioButton rondEsquive = new JRadioButton("esquive");
		rondEsquive.setBounds(287, 110, 109, 23);
		contentPane.add(rondEsquive);
	}
}
