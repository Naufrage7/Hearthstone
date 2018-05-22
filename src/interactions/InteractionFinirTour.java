package interactions;

import exception.HearthstoneException;
import joueur.IJoueur;
import plateau.Plateau;

public class InteractionFinirTour extends Interaction {

	public InteractionFinirTour(int choix) {
		super(choix);
	}

	@Override
	public String getMessage() {
		return super.getMessage() + "Finir le tour";
	}
	
	@Override
	protected void traitementSpecialise() {		
		try {
			Plateau plateau = Plateau.getInstance();
			IJoueur joueurCourant = plateau.getJoueurCourant();
			joueurCourant.finirTour();
			plateau.setJoueurCourant(plateau.getAdversaire(joueurCourant));
			plateau.getJoueurCourant().prendreTour();
		} catch ( HearthstoneException e ) {
			e.printStackTrace();
		}
	}
	
}
