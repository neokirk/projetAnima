package animaJDR;

public class Personnage
{
	private String nom ;
	private int niveau ;
	private int initiative ;
	private int attaque ;
	private int defense ;
	
	private int observation ;
	
	
	// ------------------------- //
	// ----- CONSTRUCTEURS ----- //
	// ------------------------- //
	
	public Personnage()
	{
		nom = "" ;
		niveau = 1 ;
		initiative = 0 ;
		attaque = 0 ;
		defense = 0 ;
		observation = 0 ;
	}
	
	public Personnage(String nom , int niveau , int initiative , int attaque , int defense, int observation)
	{
		this.nom = nom ;
		
		if (niveau >= 0 && niveau < 99)
			this.niveau = niveau ;
		else
			this.niveau = 1 ;

		this.initiative = initiative ;
		this.attaque = attaque ;
		this.defense = defense ;
		this.observation = observation ;
	}
	
	// ------------------------- //
	// ------- GETTER ---------- //
	// ------------------------- //
	
	public int getInitiative()
	{
		return this.initiative ;
	}
	
	public int getNiveau()
	{
		return this.niveau ;
	}
	
	public String getNom()
	{
		return this.nom ;
	}
	
	public int getAttaque()
	{
		return this.attaque ;
	}
	
	public int getDefense()
	{
		return this.defense ;
	}
	
	public int getObservation()
	{
		return this.observation ;
	}
	
	// ------------------------- //
	// ------- SETTER ---------- //
	// ------------------------- //
	
	public void setNom(String nom)
	{
		this.nom = nom ;
	}
	
	// ------------------------- //
	// ------ Applicatif ------- //
	// ------------------------- //
	
	/*
	 * 
	 */
	public int tirerInitiative()
	{
		return Des.lancerDesInitiative(true, this.getInitiative()) ;
	}
	
	/*
	 * 
	 */
	public int tirerCompetense(listeCompetences comp)
	{
		int retour = Des.lancerDesClassique(true) ;
		
		if (comp == listeCompetences.observation)
		{
			retour += observation ;
		}
		else if (comp == listeCompetences.attaque)
		{
			retour += attaque ;
		}
		else if (comp == listeCompetences.defense)
		{
			retour += defense ;
		}
		
		return retour ;
	}
}
