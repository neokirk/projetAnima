package animaJDR;

public class ResultatDes
{
	private int resultat ;		// donne le resultat du jet de des
	private int critique ;		// indique le nombre de succes ou d'echec
	
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
	
	public void ajouterValeur(int valeur)
	{
		this.resultat += valeur ;
	}
}
