package interactions;

import carte.ICarte;
import carte.ICible;
import exception.HearthstoneException;
import joueur.IJoueur;
import plateau.Plateau;

public class InteractionUtiliserCarte extends Interaction {

	public InteractionUtiliserCarte(int choix) {
		super(choix);
	}

	@Override
	public String getMessage() {
		return getChoix() + ". Utiliser une carte en jeu";
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
		
		ICarte carte = getCarteEnJeu();
		if ( carte == null ) {
			System.err.println("La carte choisie n'existe pas !");
			return;
		}
		ICible cible = getCible();
		
		System.out.println(cible);
		System.out.println(carte);
		
		try {
			joueurCourant.utiliserCarte(carte, cible);
		} catch (HearthstoneException e) {
			System.err.println(e.getMessage());
		}
		
		for ( ICarte c : joueurCourant.getJeu() ) {
			if ( c.disparait() ) {
				try {
					joueurCourant.perdreCarte(c);
				} catch (HearthstoneException e) {
					e.printStackTrace();
				}
			}
		}
		
		for ( ICarte c : joueurAdverse.getJeu() ) {
			if ( c.disparait() ) {
				try {
					joueurAdverse.perdreCarte(c);
				} catch (HearthstoneException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public int getChoix() {
		return 3;
	}

}
