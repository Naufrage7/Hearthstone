package application;

import exception.HearthstoneException;
import joueur.IJoueur;
import plateau.Plateau;

public class InteractionFinirTour extends Interaction {

	@Override
	public String getMessage() {
		return super.getMessage() + "Finir le tour";
	}
	
	@Override
	protected boolean traitementSpecialise(int choix) {
		if ( choix != getChoix() )
			return false;
		
		try {
			Plateau plateau = Plateau.getInstance();
			IJoueur joueurCourant = plateau.getJoueurCourant();
			joueurCourant.finirTour();
			plateau.setJoueurCourant(plateau.getAdversaire(joueurCourant));
			plateau.getJoueurCourant().prendreTour();
		} catch ( HearthstoneException e ) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	@Override
	public int getChoix() {
		return 1;
	}

}
