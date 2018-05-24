package capacite;
import java.util.ArrayList;

import carte.Serviteur;
import exception.HearthstoneException;
import joueur.IJoueur;
import plateau.IPlateau;
import plateau.Plateau;

public class ImageMiroir extends Capacite {
	
	public ImageMiroir(String nom, String description) {
		super(nom, description);
	}

	@Override
	public boolean necessiteCible() {
		return false;
	}

}
