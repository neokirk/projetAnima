package graphiques;

import animaJDR.ListeResistances;
import animaJDR.Personnage;
import animaJDR.ListeCompetences;
import graphiques.codesCommandes;
import metaProjet.Debug ;
import Magie.voie;

public class Commandes
{
	public static final String introNiveau = "Présence de" ;
	public static final String introInit = "Initiative de" ;
	public static final String introAtt = "Attaque de" ;
	public static final String introDef = "Defense de" ;
	public static final String introObs = "Observation de" ;
	public static final String introRPhy = "Resistance Physique de" ;
	public static final String introRMal = "Resistance Maladie de" ;
	public static final String introRPoi = "Resistance Poison de" ;
	public static final String introRPsy = "Resistance Psy de" ;
	public static final String introRMys = "Resistance Mystique de" ;

	
	public static String genererAffichageConsole(String intro, String nom, int valeur, String complement)
	{
		String retour ;
		retour = intro + " " + nom + " : " + String.valueOf(valeur) + " " + complement ;
		
		return retour ;
	}

	public static String genererAffichageConsole(String intro, String nom, int valeur)
	{
		String retour ;
		retour = intro + " " + nom  + " : " + String.valueOf(valeur) + "\n" ;
		
		return retour ;
	}
	
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
				else if (cmd.contains("md"))
				{
					retour = codesCommandes.magieDebug ;
				}
			}
		}
		
		return retour ;
	}
	
	/*
	 * Affiche dans la console d'une fenêtre le retour d'une commande de lal iste "codesCommandes" 
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
				afficher += genererAffichageConsole(introInit, personnage.getNom(), personnage.tirerInitiative());
			}
			break;

		case lancer_init_all:
			for (int i=0; i<nombrePerso; i++)
			{
				personnage = fenetre.getPersonnageParNom(listeNom[i]) ;
				afficher += genererAffichageConsole(introInit, personnage.getNom(), personnage.tirerInitiative());
			}
			break;
			
		case lancer_obs:
			if (fenetre.getNomListeSelection() != fenetre.init_liste_value)
			{
				personnage = fenetre.getPersonnageParNom(fenetre.getNomListeSelection()) ;
				afficher += genererAffichageConsole(introObs, personnage.getNom(), personnage.tirerCompetence(ListeCompetences.observation));
			}
			break;
			
		case lancer_obs_all:
			for (int i=0; i<nombrePerso; i++)
			{
				personnage = fenetre.getPersonnageParNom(listeNom[i]) ;
				afficher += genererAffichageConsole(introObs, personnage.getNom(), personnage.tirerCompetence(ListeCompetences.observation));
			}
			break;
			
		case lancer_res_mys:
			if (fenetre.getNomListeSelection() != fenetre.init_liste_value)
			{
				personnage = fenetre.getPersonnageParNom(fenetre.getNomListeSelection()) ;
				afficher += genererAffichageConsole(introRMys, personnage.getNom(), personnage.tirerResistance(ListeResistances.ResMys));
			}
			break;
			
		case lancer_res_mys_all:
			for (int i=0; i<nombrePerso; i++)
			{
				personnage = fenetre.getPersonnageParNom(listeNom[i]) ;
				afficher += genererAffichageConsole(introRMys, personnage.getNom(), personnage.tirerResistance(ListeResistances.ResMys));
			}
			break;
			
		case lancer_res_psy:
			if (fenetre.getNomListeSelection() != fenetre.init_liste_value)
			{
				personnage = fenetre.getPersonnageParNom(fenetre.getNomListeSelection()) ;
				afficher += genererAffichageConsole(introRPsy, personnage.getNom(), personnage.tirerResistance(ListeResistances.ResPsy));
			}
			break;
			
		case lancer_res_psy_all:
			for (int i=0; i<nombrePerso; i++)
			{
				personnage = fenetre.getPersonnageParNom(listeNom[i]) ;
				afficher += genererAffichageConsole(introRPsy, personnage.getNom(), personnage.tirerResistance(ListeResistances.ResPsy));
			}
			break;
			
		case lancer_res_phy:
			if (fenetre.getNomListeSelection() != fenetre.init_liste_value)
			{
				personnage = fenetre.getPersonnageParNom(fenetre.getNomListeSelection()) ;
				afficher += genererAffichageConsole(introRPhy, personnage.getNom(), personnage.tirerResistance(ListeResistances.ResPhy));
			}
			break;
			
		case lancer_res_phy_all:
			for (int i=0; i<nombrePerso; i++)
			{
				personnage = fenetre.getPersonnageParNom(listeNom[i]) ;
				afficher += genererAffichageConsole(introRPhy, personnage.getNom(), personnage.tirerResistance(ListeResistances.ResPhy));
			}
			break;
			
		case lancer_res_poi:
			if (fenetre.getNomListeSelection() != fenetre.init_liste_value)
			{
				personnage = fenetre.getPersonnageParNom(fenetre.getNomListeSelection()) ;
				afficher += genererAffichageConsole(introRPoi, personnage.getNom(), personnage.tirerResistance(ListeResistances.ResPoi));
			}
			break;
			
		case lancer_res_poi_all:
			for (int i=0; i<nombrePerso; i++)
			{
				personnage = fenetre.getPersonnageParNom(listeNom[i]) ;
				afficher += genererAffichageConsole(introRPoi, personnage.getNom(), personnage.tirerResistance(ListeResistances.ResPoi));
			}
			break;
			
		case lancer_res_mal:
			if (fenetre.getNomListeSelection() != fenetre.init_liste_value)
			{
				personnage = fenetre.getPersonnageParNom(fenetre.getNomListeSelection()) ;
				afficher += genererAffichageConsole(introRMal, personnage.getNom(), personnage.tirerResistance(ListeResistances.ResMal));
			}
			break;
			
		case lancer_res_mal_all:
			for (int i=0; i<nombrePerso; i++)
			{
				personnage = fenetre.getPersonnageParNom(listeNom[i]) ;
				afficher += genererAffichageConsole(introRMal, personnage.getNom(), personnage.tirerResistance(ListeResistances.ResMal));
			}
			break;
			
		case magieDebug:
			voie oo = new voie(10) ;
			afficher += oo.getNom() + "\n" ;
			break;
			
		default:
			afficher = "Code de commande inconnu\n" ;
			break;
		}
		
		return afficher ;
	}
	
}
