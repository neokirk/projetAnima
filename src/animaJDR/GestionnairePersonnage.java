package animaJDR;


import java.util.ArrayList;

import animaJDR.Personnage;

public class GestionnairePersonnage
{
	
	////////////////////////////////////////////////
	/////////////// ATTRIBUTS //////////////////////
	////////////////////////////////////////////////	
	
	private ArrayList<Personnage> listePersonnages ;
	
	
	////////////////////////////////////////////////
	//////////////// METHODES //////////////////////
	////////////////////////////////////////////////	
	
	/*
	 * Constructeur
	 */
	public GestionnairePersonnage()
	{
		listePersonnages = new ArrayList<Personnage>() ;
	}
	
	
	/*
	 * Retourne le nombre de personnages stockés dans la liste
	 */
	public int getNombrePersonnages()
	{
		return listePersonnages.size() ;
	}
	
	/*
	 * Retourne une liste contenant les noms des personnages enregistrés
	 */
	public String[] getListeNoms()
	{
		String[] liste = new String[listePersonnages.size()] ;
		for (int i=0 ; i<listePersonnages.size() ; i++)
		{
			liste[i] = listePersonnages.get(i).getNom() ;
		}
		
		return liste ;
	}
	
	/*
	 * 
	 */
	public void ajouterPersonnage(Personnage perso)
	{
		this.listePersonnages.add(perso) ;
	}
	
	/*
	 * 
	 */
	public Personnage getPersoParNom(String nom)
	{
		int tailleListe = listePersonnages.size() ;
		int i = 0 ;
		boolean trouve = false ;
		Personnage PersoRetour = null ;
		
		while (i<tailleListe && !trouve)
		{
			if (listePersonnages.get(i).getNom() == nom)
			{
				trouve = true ;
				PersoRetour = listePersonnages.get(i) ;
			}
			i++ ;
		}
		
		return PersoRetour ;
	}
	
	/*
	 * Supprime un personnage de la liste à partir de son nom
	 */
	public boolean supprimerPersonnage(String nom)
	{
		int tailleListe = listePersonnages.size() ;
		Personnage p ;
		int i = 0 ;
		boolean trouve = false ;
		
		while (!trouve && i<tailleListe)
		{
			p = this.listePersonnages.get(i) ;
			if (p.getNom() == nom)
			{
				this.listePersonnages.remove(i) ;
				trouve = true ;
			}
			
			i++ ;
		}
		
		return trouve ;
	}
}
