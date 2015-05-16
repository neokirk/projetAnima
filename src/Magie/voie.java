package Magie;


/*
 * Classe qui permet de determiner la voie d'un sort et ses proprietes.
 */


public class voie 
{
	// Proprietes : 
	// - un nom
	// - un identifiant entre 1 et 26, avec "0" comme valeur exceptionnelle
	// - est-ce que c'est une voie Primaire (T) ou Secondaire (F)
	// - est-ce que c'est une voie Majeure (T) ou Mineure (F)
	//
	// Les termes "acces libres" et "secondaire" sont synonymes. 
	
	private String nom ;
	private int id ;
	private boolean primaire;
	private boolean majeure ;
	
	
	// ------------------------- //
	// ----- CONSTRUCTEURS ----- //
	// ------------------------- //
	
	// Celui-ci ne devrait jamais être utilisé à priori
	public voie()
	{
		nom = "nul" ;
		id = 0 ;
		primaire = true ;
		majeure= true ;
	}
	
	public voie(int id)
	{
		this.id = id;
		this.nom = convertirIdNom(id);
		convertirIdType(id);
	}
	
	// ------------------------- //
	// ------- GETTER ---------- //
	// ------------------------- //
	
	public int getId()
	{
		return this.id ;
	}
	
	public String getNom()
	{
		return this.nom ;
	}
	
	public boolean getPrimaire()
	{
		return this.primaire ;
	}
	
	public boolean getMajeure()
	{
		return this.majeure ;
	}
	
	// ------------------------- //
	// ------- SETTER ---------- //
	// ------------------------- //
	
	public void setId(int id)
	{
		this.id = id ;
		convertirIdType(id);
	}
	
	// ------------------------- //
	// ------ Applicatif ------- //
	// ------------------------- //
	
	/*
	 * Donne le nom à partir de l'id
	 */
	private String convertirIdNom(int valeur)
	{
		String monString;

		switch (valeur) {
		case 0:  monString = "nul";
		break;
		case 1:  monString = "Lumiere";
		break;
		case 2:  monString = "Obscurite";
		break;
		case 3:  monString = "Creation";
		break;
		case 4:  monString = "Destruction";
		break;
		case 5:  monString = "Air";
		break;
		case 6:  monString = "Eau";
		break;
		case 7:  monString = "Feu";
		break;
		case 8:  monString = "Terre";
		break;
		case 9:  monString = "Essence";
		break;
		case 10: monString = "Illusion";
		break;
		case 11: monString = "Necromancie";
		break;
		case 12: monString = "Acces Libre";
		break;
		case 13: monString = "Chaos";
		break;
		case 14: monString = "Connaissance";
		break;
		case 15: monString = "Guerre";
		break;
		case 16: monString = "Literae";
		break;
		case 17: monString = "Mort";
		break;
		case 18: monString = "Musique";
		break;
		case 19: monString = "Noblesse";
		break;
		case 20: monString = "Paix";
		break;
		case 21: monString = "Peche";
		break;
		case 22: monString = "Reve";
		break;
		case 23: monString = "Sang";
		break;
		case 24: monString = "Seuil";
		break;
		case 25: monString = "Temps";
		break;
		case 26: monString = "Vide";
		break;
		default: monString = "Valeur invalide";
		break;
		}

		return monString ;
	}
	
	/*
	 * Set le type de voie (secondaire, primaire majeure ou primaire mineure) 
	 * à partir de l'id.
	 */
	private void convertirIdType(int valeur)
	{	
		this.majeure = (valeur <= 4 || valeur == 11) ? true : false;
		this.primaire = valeur < 12;
	}
}