package capacite;

import carte.Serviteur;
import joueur.IJoueur;

public class InvocationDesChiens extends ImageMiroir {
	
	public InvocationDesChiens(String nom, String description, IJoueur joueur) {
		super(nom, description);
		ajouterServiteur(new Serviteur(1, 1, "Chien de Rexxar", 0, new Charge(), joueur));
		ajouterServiteur(new Serviteur(1, 1, "Chien de Rexxar", 0, new Charge(), joueur));
	}
	
}
