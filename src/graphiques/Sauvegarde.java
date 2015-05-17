package graphiques;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import metaProjet.Debug;
import animaJDR.GestionnairePersonnage;

/*
 * Classe qui permet la sauvegarde des personnages dans la memoire durable
 */
public class Sauvegarde
{
	public static void sauvegarder(GestionnairePersonnage liste)
	{
		// creation d'un objet qui gere des flux d'octets
		ObjectOutputStream streamSortie = null ;
		
		try
		{
			// creation d'un objet qui connecte un flux a un fichier
			final FileOutputStream fichierSauv = new FileOutputStream("saveAnima.anm",false) ;
			streamSortie = new ObjectOutputStream(fichierSauv) ;
		}
		catch (java.io.IOException e)
		{
			Debug.afficher("erreur lors de l'acces fichier") ;
		}
		finally
		{
			try
			{
				streamSortie.writeObject(liste) ;
				streamSortie.close() ;
			}
			catch (IOException e)
			{
				Debug.afficher("erreur lors de l'ecriture du fichier") ;
				e.printStackTrace();
			}
		}
	}
	
	public static GestionnairePersonnage charger()
	{
		GestionnairePersonnage liste = null ;
		ObjectInputStream streamEntree = null ;
		
		try
		{
			final FileInputStream fichierSauv = new FileInputStream("saveAnima.anm") ;
			streamEntree = new ObjectInputStream(fichierSauv) ;
		}
		catch (java.io.IOException e)
		{
			Debug.afficher("erreur lors de l'acces fichier") ;
		}
		finally
		{
			try
			{
				if (streamEntree != null)
				{
					liste = (GestionnairePersonnage) streamEntree.readObject() ;
					streamEntree.close() ;
				}
				
			}
			catch (IOException e)
			{
				Debug.afficher("Erreur de lecture du fichier") ;
			}
			catch (ClassNotFoundException e)
			{
				Debug.afficher("Format du fichier non lisible") ;
			}
		}
		
		return liste ;
	}
}
