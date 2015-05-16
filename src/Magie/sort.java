package Magie;


/*
 * Classe qui permet de déterminer un sort.
 */


public class sort
{
	// Nom du sort
	private String nom ;
	
	// Voie de magie du sort : 
	private voie laVoie;
	
	// Niveau entre 2 et 100
	private int niveau ;
	private boolean hauteMagie; 
	private boolean magieDivine; 
	
	// Type du sort : actif ou pas (donc passif)
	private boolean sortActif; 

	private boolean type_effet;
	private boolean type_defense;
	private boolean type_animique;
	private boolean type_attaque;

	// Contenu du sort proprement dit
	private String description;

	private String[] effet;				// string ; string ; string ; string
	private int[] intReq;				// int ; int ; int ; int
	private int[] cout;					// int ; int ; int ; int 
	private float coefMaintien;			// A multiplier avec "cout" pour obtenir le maintien
	private boolean maintienQuotidien;	// Par défaut à "false"

	
	// ------------------------- //
	// ----- CONSTRUCTEURS ----- //
	// ------------------------- //
	
	public sort()
	{
		nom = "" ;
		niveau = 1 ;
	}
	
	public sort(String nom, int idVoie, int niveau, boolean actif, boolean tEffet,
			boolean tDefense, boolean tAnimique, boolean tAttaque, String description,
			String[] effet, int[] intReq, int[] cout, float coefMaintien,
			boolean maintienQuotidien)
	{
		this.nom = nom;
		this.laVoie = new voie(idVoie);
		this.niveau = niveau;
		gnoseDuSort(niveau);
		this.sortActif = actif;
		this.type_effet = tEffet;
		this.type_defense = tDefense;
		this.type_animique = tAnimique;
		this.type_attaque = tAttaque;
		this.description = description;
		this.effet = effet;
		this.intReq = intReq;
		this.cout = cout;
		this.coefMaintien = coefMaintien;
		this.maintienQuotidien = maintienQuotidien;
	}

	// ------------------------- //
	// ------- GETTER ---------- //
	// ------------------------- //

	public String getNom()
	{return this.nom ;}
	
	public voie getVoie()
	{return this.laVoie ;}

	public int getNiveau()
	{return this.niveau ;}

	public boolean getHauteMagie()
	{return this.hauteMagie ;}

	public boolean getMagieDivine()
	{return this.magieDivine ;}

	public boolean getSortActif()
	{return this.sortActif ;}
	
	public boolean getTypeEffet()
	{return this.type_effet ;}
	
	public boolean getTypeDefense()
	{return this.type_defense ;}
	
	public boolean getTypeAnimique()
	{return this.type_animique ;}
	
	public boolean getTypeAttaque()
	{return this.type_attaque ;}
	
	public String getDescription()
	{return this.description ;}
	
	public String[] getEffet()
	{return this.effet ;}
	
	public int[] getIntReq()
	{return this.intReq ;}
	
	public int[] getCout()
	{return this.cout ;}
	
	public float getCoefMaintien()
	{return this.coefMaintien ;}
	
	public boolean getMaintienQuotidien()
	{return this.maintienQuotidien ;}
	
	
	// ------------------------- //
	// ------- SETTER ---------- //
	// ------------------------- //

	// Pas de setter pour hauteMagie et magieDivine, qui se déterminent tout seuls. 
	
	public void setNom(String nom)
	{this.nom = nom ;}

	// Attention : prend uniquement un id en entrée
	public void setLaVoie(int idVoie)
	{this.laVoie = new voie(idVoie) ;}

	public void setNiveau(int niveau)
	{
		this.niveau = niveau;
		gnoseDuSort(niveau);
	}

	public void setSortActif(boolean sortActif)
	{this.sortActif = sortActif ;}

	public void setType_effet(boolean type_effet)
	{this.type_effet = type_effet ;}

	public void setType_defense(boolean type_defense)
	{this.type_defense = type_defense ;}

	public void setType_animique(boolean type_animique) 
	{this.type_animique = type_animique ;}

	public void setType_attaque(boolean type_attaque) 
	{this.type_attaque = type_attaque;}
	
	public void setDescription(String description)
	{this.description = description ;}

	public void setEffet(String[] effet)
	{
		if (effet.length == 4)
			this.effet = effet;
		else
			this.effet = new String[]{"Rien","Rien","Rien","Rien"};
	}

	public void setIntReq(int[] intReq)
	{
		if (intReq.length == 4)
			this.intReq = intReq;
		else
			this.intReq = new int[]{-1,-1,-1,-1};
	}

	public void setCout(int[] cout) 
	{
		if (cout.length == 40)
			this.cout = cout;
		else
			this.cout = new int[]{-1,-1,-1,-1};
	}

	public void setCoefMaintien(float coefMaintien)
	{this.coefMaintien = coefMaintien ;}

	public void setMaintienQuotidien(boolean maintienQuotidien)
	{this.maintienQuotidien = maintienQuotidien ;}
	

	// ------------------------- //
	// ------ Applicatif ------- //
	// ------------------------- //


	/*
	 * Set les qualités du sort à partir du niveau
	 */
	private void gnoseDuSort(int valeur)
	{	
		this.hauteMagie = (valeur < 91 && valeur > 81) ? true : false;
		this.magieDivine = (valeur < 101 && valeur > 91) ? true : false;
	}

}


