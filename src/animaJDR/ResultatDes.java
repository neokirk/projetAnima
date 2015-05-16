package animaJDR;

public class ResultatDes
{
	private int resultat ;		// donne le r�sultat du jet de d�s
	private int critique ;		// indique le nombre de succ�s ou d'�chec
	
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
