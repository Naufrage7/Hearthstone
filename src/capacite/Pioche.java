package capacite;

import exception.HearthstoneException;
import joueur.IJoueur;
import plateau.IPlateau;
import plateau.Plateau;

public class Pioche extends Capacite {
	
	private int quantite;
	
	public Pioche(String nom, String description, int quantite) {
		super(nom, description);
		this.quantite = quantite;
	}
	
	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		IPlateau plateau = Plateau.getInstance();
		IJoueur joueurCourant = plateau.getJoueurCourant();
		
		for ( int i = 0; i < this.quantite; i++ )
			joueurCourant.piocher();
	}
	
}
