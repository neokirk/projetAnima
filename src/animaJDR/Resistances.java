package animaJDR;

import java.io.Serializable;

public class Resistances implements Serializable
{
	private static final long serialVersionUID = 5 ;
	private int RPhy ;
	private int RMal ;
	private int RPoi ;
	private int RPsy ;
	private int RMys ;
	
	public Resistances(int RPhy, int RMal, int RPoi, int RPsy, int RMys)
	{
		this.RPhy = RPhy ;
		this.RMal = RMal ;
		this.RPoi = RPoi ;
		this.RPsy = RPsy ;
		this.RMys = RMys ;
	}
	
	
	// ---------------------------- //
	// ---------- GETTER ---------- //
	// ---------------------------- //	
	
	int getRPhy()
	{
		return this.RPhy ;
	}
	
	int getRMal()
	{
		return this.RMal ;
	}
	
	int getRPoi()
	{
		return this.RPoi ;
	}
	
	int getRPsy()
	{
		return this.RPsy ;
	}
	
	int getRMys()
	{
		return this.RMys ;
	}
}
