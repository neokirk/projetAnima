package Magie;



/*
 * Classe qui permet de déterminer comment construire un sort.
 */

public class sort
{

	// Type du sort : actif ou pas (donc passif)
	private boolean sortActif; 
	
	// Voie de magie du sort : 
	// - un identifiant entre 1 et 26
	// - est-ce que c'est une voie Primaire (0) ou secondaire (1)
	// - est-ce que c'est une voie Majeure (0) ou Mineure (1)
	private int[] voie;
	
	// Entre 2 et 100
	private int niveau ;
	
	private String nom ;
	private int defense ;
	
	private int observation ;
	
	
	// ------------------------- //
	// ----- CONSTRUCTEURS ----- //
	// ------------------------- //
	
	public sort()
	{
		nom = "" ;
		niveau = 1 ;

		defense = 0 ;
		observation = 0 ;
	}
	
	
	// Retourne un resultat aleatoire uniforme entre 1 et 100
	public static int d100()
	{
		return (int)(Math.random()* 100)  + 1 ;
	}
	
	
	
}


