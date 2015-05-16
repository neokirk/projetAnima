package animaJDR;

public class ResultatDes
{
	private int resultat ;		// donne le résultat du jet de dés
	private int critique ;		// indique le nombre de succés ou d'échec
	
	/*
	 * Constructeur
	 */
	public ResultatDes(int resultat, int critique)
	{
		this.resultat = resultat ;
		this.critique = critique ;
	}
	
	/*
	 * Getters
	 */
	
	public int getResultat()
	{
		return this.resultat ;
	}
	
	public int getCritique()
	{
		return this.critique ;
	}
}
