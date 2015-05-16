package animaJDR;

/*
 * Classe qui permet d'effectuer des lanc�s de d�s suivant les r�gles d'Anima
 */
public class Des
{

	// Retourne un r�sultat al�atoire uniforme entre 1 et 100
	public static int d100()
	{
		return (int)(Math.random()* 100)  + 1 ;
	}
	
	// Retourne un r�sultat al�atoire uniforme entre 1 et 10
	public static int d10()
	{
		return (int)(Math.random() * 10) + 1 ;
	}
	
	public static int lancerDesClassique(boolean ouvert)
	{
		int resultat ;
		
		// Lanc� du d�s
		resultat = d100() ;
		
		// Echec critique
		if (resultat <= 3)
		{
			resultat = - d100() ;
		}
		
		// R�ussite critique
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
	 * Lance un jet de d�s pour un test d'initiative
	 */
	public static int lancerDesInitiative(boolean ouvert, int base)
	{
		int resultat = d100() ;
		
		// �chec critique
		if (resultat == 1)
			resultat = Valeurs.malusInit01 + base ;
		else if (resultat == 2)
			resultat = Valeurs.malusInit02 + base ;
		else if (resultat == 3)
			resultat = Valeurs.malusInit03 + base ;

		// R�ussite critique
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
