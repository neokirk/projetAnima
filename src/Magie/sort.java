package Magie;



/*
 * Classe qui permet de déterminer comment construire un sort.
 */

public class sort
{

	// Retourne un resultat aleatoire uniforme entre 1 et 100
	public static int d100()
	{
		return (int)(Math.random()* 100)  + 1 ;
	}
	
	// Retourne un resultat aleatoire uniforme entre 1 et 10
	public static int d10()
	{
		return (int)(Math.random() * 10) + 1 ;
	}
	
	public static int lancerDesClassique(boolean ouvert)
	{
		int resultat ;
		
		// Lance du des
		resultat = d100() ;
		
		// Echec critique
		if (resultat <= 3)
		{
			resultat = - d100() ;
		}
		
		// Reussite critique
		if (ouvert == true)
		{
			int seuil = 90 ;
			int jet = resultat ;
			while (jet >= seuil)
			{
				jet = d100() ;
				seuil++ ;
				resultat += jet ;
			}
		}
		
		return resultat ;
	}
	
	
}