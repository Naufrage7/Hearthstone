package capacite;

import carte.ICarte;
import carte.ICible;
import exception.HearthstoneException;
import joueur.IJoueur;
import plateau.IPlateau;
import plateau.Plateau;

public class AttaqueTotale extends AttaqueCiblee {
	
	public AttaqueTotale(String nom, String description, int degats) {
		super(nom, description, degats);
	}
	
	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		IPlateau plateau = Plateau.getInstance();
		IJoueur joueurAdverse = plateau.getAdversaire(plateau.getJoueurCourant());
		
		for ( ICarte carte : joueurAdverse.getJeu() ) {
			if ( !(carte instanceof ICible) )
				continue;
			
			ICible cibleVisee = (ICible) cible;
			cibleVisee.recevoirDegats(getDegats());
		}
	}

}
