package capacite;

import exception.HearthstoneException;
import joueur.IJoueur;
import plateau.IPlateau;
import plateau.Plateau;

public class Pioche extends Capacite {
	public Pioche() {
		super("Pioche", "La carte qui possède cette capacité permet de piocher une ou plusieurs cartes.");
	}
	
	public void executerAction(Object cible) throws HearthstoneException {
		IPlateau plateau = Plateau.getInstance();
		IJoueur joueurCourant = plateau.getJoueurCourant();
		
		for ( int i = 0; i < 2; i++ )
			joueurCourant.piocher();
	}
}
