package graphiques;

import animaJDR.Personnage;
import animaJDR.listeCompetences;
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
				Debug.afficher("commande d�tect�e") ;
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
				else if (cmd.contains("help"))
				{
					retour = codesCommandes.aide ;
				}
				else if (cmd.contains("magieDebug"))
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
		
		if (code == codesCommandes.aide)
		{
			afficher = "Liste des commandes :\n- /init\n- /obs\n- /help" ;
		}
		else if (code == codesCommandes.lancer_init)
		{
			if (fenetre.getNomListeSelection() != fenetre.init_liste_value)
			{
				personnage = fenetre.getPersonnageParNom(fenetre.getNomListeSelection()) ;
				afficher += "Init. de " + personnage.getNom() + " = " ;
				afficher += String.valueOf(personnage.tirerInitiative()) ;
			}
		}
		else if (code == codesCommandes.lancer_init_all)
		{
			for (int i=0; i<nombrePerso; i++)
			{
				afficher += "Init. de " + listeNom[i] + " = " ;
				personnage = fenetre.getPersonnageParNom(listeNom[i]) ;
				afficher += String.valueOf(personnage.tirerInitiative()) + "\n" ;
			}
		}
		else if (code == codesCommandes.lancer_obs)
		{
			if (fenetre.getNomListeSelection() != fenetre.init_liste_value)
			{
				personnage = fenetre.getPersonnageParNom(fenetre.getNomListeSelection()) ;
				afficher += "Obs. de " + personnage.getNom() + " = " ;
				afficher += String.valueOf(personnage.tirerCompetense(listeCompetences.observation)) ;
			}
		}
		else if (code == codesCommandes.lancer_obs_all)
		{
			for (int i=0; i<nombrePerso; i++)
			{
				afficher += "Obs. de " + listeNom[i] + " = " ;
				personnage = fenetre.getPersonnageParNom(listeNom[i]) ;
				afficher += String.valueOf(personnage.tirerCompetense(listeCompetences.observation)) + "\n" ;
			}
		}
		else if (code == codesCommandes.magieDebug)
		{
			for (int i=0; i<nombrePerso; i++)
			{
				voie oo = new voie(10);
				
				afficher += oo.getNom() + "\n" ;
			}
		}

		return afficher ;
	}
	
}
