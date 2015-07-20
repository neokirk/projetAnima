package animaJDR;

import java.io.Serializable;

public class Personnage implements Serializable
{
	// indicateur JAVA pour la sauvegarde des objets
	private static final long serialVersionUID = 128;
	
	// elements de base pour le personnage
	private String nom ;
	private int niveau ;
	
	//
	private int initiative ;
	private int attaque ;
	private int defense ;
	
	//
	private int observation ;
	
	//
	private Resistances resistances ;
	
	
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
		
		// ce constructeur ne creer pas de resistances
		this.resistances = null ;
	}
	
	public Personnage(String nom , int niveau , int initiative , int attaque , int defense, int observation, Resistances resistances)
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
		
		this.resistances = resistances ;
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
	
	public int getResistance(ListeResistances resistance)
	{
		int retour = 0 ;

		// l'objet resistances n'est pas forcement defini, il faut le tester avant
		if (this.resistances != null)
		{
			switch (resistance)
			{
			case ResPhy:
				retour = this.resistances.getRPhy() ;
				break;
			case ResMal:
				retour = this.resistances.getRMal() ;
				break;
			case ResPoi:
				retour = this.resistances.getRPoi() ;
				break;
			case ResPsy:
				retour = this.resistances.getRPsy() ;
				break;
			case ResMys:
				retour = this.resistances.getRMys() ;
				break;
			default:
				break;
			}
		}

		return retour ;
	}
	
	public boolean possedeResistance()
	{
		return (this.resistances != null) ;
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
	public ResultatDes tirerInitiative()
	{
		return Des.lancerDesInitiative(true, this.getInitiative()) ;
	}
	
	/*
	 * Methode de lancement de des pour une competence parmi :
	 * Attaque, defense, observation
	 */
	public ResultatDes tirerCompetence(ListeCompetences comp)
	{
		ResultatDes retour = Des.lancerDesClassique(true) ;
		
		if (comp == ListeCompetences.observation)
		{
			retour.ajouterValeur(observation);
		}
		else if (comp == ListeCompetences.attaque)
		{
			retour.ajouterValeur(attaque) ;
		}
		else if (comp == ListeCompetences.defense)
		{
			retour.ajouterValeur(defense) ;
		}
		
		return retour ;
	}
	
	/*
	 * 
	 */
	public ResultatDes tirerResistance(ListeResistances res)
	{
		int jet = Des.d100() ;
		ResultatDes retour ;
		
		if (jet == 1)
			retour = new ResultatDes(jet+this.getResistance(res), -1) ;
		else if (jet == 100)
			retour = new ResultatDes(jet+this.getResistance(res), 1) ;
		else
			retour = new ResultatDes(jet+this.getResistance(res), 0) ;
		
		return retour ;
	}
}
