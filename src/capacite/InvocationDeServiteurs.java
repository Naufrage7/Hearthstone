package capacite;

import carte.Serviteur;
import exception.HearthstoneException;
import joueur.IJoueur;
import plateau.IPlateau;
import plateau.Plateau;

public class InvocationDeServiteurs extends ImageMiroir {
	
	public InvocationDeServiteurs(String nom, String description, Serviteur serviteur) {
		super(nom, description);
		ajouterServiteur(serviteur);
	}
	
}
