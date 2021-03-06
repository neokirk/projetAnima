package animaJDR;

/*
 * Classe qui permet d'effectuer des lances de des suivant les regles d'Anima
 */
public class Des
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
	
	public static ResultatDes lancerDesClassique(boolean ouvert)
	{
		int resultat ;
		int nombreSucces = 0 ;
		
		// Lance du des
		resultat = d100() ;
		
		// Echec critique
		if (resultat <= 3)
		{
			resultat = - d100() ;
			nombreSucces-- ;
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
				nombreSucces++ ;
			}
		}
		
		return new ResultatDes(resultat, nombreSucces) ;
	}
	
	/*
	 * Lance un jet de des pour un test d'initiative
	 */
	public static ResultatDes lancerDesInitiative(boolean ouvert, int base)
	{
		int resultat = d100() ;
		int succes = 0 ;
		
		// Echec critique
		if (resultat <= 3)
			succes-- ;
		if (resultat == 1)
			resultat = Valeurs.malusInit01 + base ;
		else if (resultat == 2)
			resultat = Valeurs.malusInit02 + base ;
		else if (resultat == 3)
			resultat = Valeurs.malusInit03 + base ;
			

		// Reussite critique
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
				succes++ ;
			}
		}

		return new ResultatDes(resultat, succes) ;
	}
	
}
