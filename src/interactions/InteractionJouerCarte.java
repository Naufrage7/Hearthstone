package interactions;

import carte.ICarte;
import carte.ICible;
import exception.HearthstoneException;
import joueur.IJoueur;
import plateau.Plateau;

public class InteractionJouerCarte extends Interaction {

	public InteractionJouerCarte(int choix) {
		super(choix);
	}

	@Override
	public String getMessage() {
		return super.getMessage() + "Jouer une carte de ma main";
	}
	
	@Override
	protected void traitementSpecialise() {	
		IJoueur joueurCourant = Plateau.getInstance().getJoueurCourant();
		IJoueur joueurAdverse = null;
		try {
			joueurAdverse = Plateau.getInstance().getAdversaire(Plateau.getInstance().getJoueurCourant());
		} catch ( HearthstoneException e ) {
			System.err.println(e.getMessage());
		}
		
		
		ICarte carte = getCarteEnMain();
		if ( carte == null ) {
			System.err.println("La carte choisie n'existe pas");
			return;
		}
		
		ICible cible = getCible();
		
		try {
			joueurCourant.jouerCarte(carte, cible);
		} catch (HearthstoneException e) {
			System.err.println(e.getMessage());
		}
		
		for ( int i = 0; i < joueurCourant.getJeu().size(); i++ ) {
			ICarte c = joueurCourant.getJeu().get(i);
			if ( c.disparait() ) {
				try {
					joueurCourant.perdreCarte(c);
				} catch (HearthstoneException e) {
					e.printStackTrace();
				}
				i--;
			}
		}
		
		for ( int i = 0; i < joueurAdverse.getJeu().size(); i++ ) {
			ICarte c = joueurAdverse.getJeu().get(i);
			if ( c.disparait() ) {
				try {
					joueurAdverse.perdreCarte(c);
				} catch (HearthstoneException e) {
					e.printStackTrace();
				}
				i--;
			}
		}
	}

}
