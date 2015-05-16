package animaJDR;

/*
 * Classe qui permet d'effectuer des lancés de dés suivant les règles d'Anima
 */
public class Des
{

	// Retourne un résultat aléatoire uniforme entre 1 et 100
	public static int d100()
	{
		return (int)(Math.random()* 100)  + 1 ;
	}
	
	// Retourne un résultat aléatoire uniforme entre 1 et 10
	public static int d10()
	{
		return (int)(Math.random() * 10) + 1 ;
	}
	
	public static int lancerDesClassique(boolean ouvert)
	{
		int resultat ;
		
		// Lancé du dés
		resultat = d100() ;
		
		// Echec critique
		if (resultat <= 3)
		{
			resultat = - d100() ;
		}
		
		// Réussite critique
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
	
	/*
	 * Lance un jet de dés pour un test d'initiative
	 */
	public static int lancerDesInitiative(boolean ouvert, int base)
	{
		int resultat = d100() ;
		
		// Echec critique
		if (resultat == 1)
			resultat = Valeurs.malusInit01 + base ;
		else if (resultat == 2)
			resultat = Valeurs.malusInit02 + base ;
		else if (resultat == 3)
			resultat = Valeurs.malusInit03 + base ;

		// Réussite critique
		if (ouvert == true)
		{
			int seuil = 90 ;
			int jet = resultat ;
			resultat += base ;
			
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
