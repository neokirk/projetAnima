package graphiques;

import animaJDR.ListeResistances;
import animaJDR.Personnage;
import animaJDR.ListeCompetences;
import graphiques.codesCommandes;
import metaProjet.Debug ;
import Magie.voie;

public class Commandes
{
	/*
	 * Methode qui parse un String et qui retroune un code associe a la commande lue
	 */
	static public codesCommandes lireCommande(String cmd)
	{
		
		codesCommandes retour = codesCommandes.rien ;
		
		// on analyse uniquement si la chaine est non-vide
		if (cmd.length() != 0)
		{
			// detection de debut de commande
			if (cmd.codePointAt(0) == '/')
			{
				Debug.afficher("commande detectee") ;
				// detection d'init
				if (cmd.contains("init"))
				{
					if (cmd.contains("-all"))
					{
						retour = codesCommandes.lancer_init_all ;
					}
					else
					{
						retour = codesCommandes.lancer_init ;
					}
				}
				// detection pour l'observation
				else if (cmd.contains("obs"))
				{
					if (cmd.contains("-all"))
					{
						retour = codesCommandes.lancer_obs_all ;
					}
					else
					{
						retour = codesCommandes.lancer_obs ;
					}
				}
				else if(cmd.contains("/res"))
				{
					if (cmd.contains("-all") && cmd.contains("-mys"))
						retour = codesCommandes.lancer_res_mys_all ;
					else if (cmd.contains("-mys"))
						retour = codesCommandes.lancer_res_mys ;
					else if (cmd.contains("-all") && cmd.contains("-psy"))
						retour = codesCommandes.lancer_res_psy_all ;
					else if (cmd.contains("-psy"))
						retour = codesCommandes.lancer_res_psy ;
				}
				// detection pour l'aide
				else if (cmd.contains("help"))
				{
					retour = codesCommandes.aide ;
				}
				else if (cmd.contains("mD"))
				{
					retour = codesCommandes.magieDebug ;
				}
			}
		}
		
		return retour ;
	}
	
	/*
	 * 
	 */
	static public String executerCommande(codesCommandes code, LaFenetre fenetre)
	{
		String afficher = "" ;
		Personnage personnage ;
		int nombrePerso = fenetre.getNombrePerso() ;
		String[] listeNom = fenetre.getListeNomSauve() ;
		
		switch(code)
		{
		case aide:
			afficher = "Liste des commandes :\n- /init\n- /obs\n" ;
			afficher += "- /res (opt : -phy -poi -mal -psy -mys -all)\n" ;
			afficher += "- /help" ;
			break;
			
		case lancer_init:
			if (fenetre.getNomListeSelection() != fenetre.init_liste_value)
			{
				personnage = fenetre.getPersonnageParNom(fenetre.getNomListeSelection()) ;
				afficher += "Init. de " + personnage.getNom() + " = " ;
				afficher += String.valueOf(personnage.tirerInitiative()) ;
			}
			break;

		case lancer_init_all:
			for (int i=0; i<nombrePerso; i++)
			{
				afficher += "Init. de " + listeNom[i] + " = " ;
				personnage = fenetre.getPersonnageParNom(listeNom[i]) ;
				afficher += String.valueOf(personnage.tirerInitiative()) + "\n" ;
			}
			break;
			
		case lancer_obs:
			if (fenetre.getNomListeSelection() != fenetre.init_liste_value)
			{
				personnage = fenetre.getPersonnageParNom(fenetre.getNomListeSelection()) ;
				afficher += "Obs. de " + personnage.getNom() + " = " ;
				afficher += String.valueOf(personnage.tirerCompetence(ListeCompetences.observation)) ;
			}
			break;
			
		case lancer_obs_all:
			for (int i=0; i<nombrePerso; i++)
			{
				afficher += "Obs. de " + listeNom[i] + " = " ;
				personnage = fenetre.getPersonnageParNom(listeNom[i]) ;
				afficher += String.valueOf(personnage.tirerCompetence(ListeCompetences.observation)) + "\n" ;
			}
			break;
			
		case lancer_res_mys:
			if (fenetre.getNomListeSelection() != fenetre.init_liste_value)
			{
				personnage = fenetre.getPersonnageParNom(fenetre.getNomListeSelection()) ;
				afficher += "Res. myst de " + personnage.getNom() + " = " ;
				afficher += String.valueOf(personnage.tirerResistance(ListeResistances.ResMys)) ;
			}
			break;
			
		case lancer_res_mys_all:
			for (int i=0; i<nombrePerso; i++)
			{
				afficher += "Res. myst de " + listeNom[i] + " = " ;
				personnage = fenetre.getPersonnageParNom(listeNom[i]) ;
				afficher += String.valueOf(personnage.tirerResistance(ListeResistances.ResMys)) + "\n" ;
			}
			break;
			
		case magieDebug:
			for (int i=0; i<nombrePerso; i++)
			{
				voie oo = new voie(10) ;
				afficher += oo.getNom() + "\n" ;
			}
			break;
			
		default:
			afficher = "Code de commande inconnu\n" ;
			break;
		}
		
		return afficher ;
	}
	
}
