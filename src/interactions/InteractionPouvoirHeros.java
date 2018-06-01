package interactions;

import java.util.InputMismatchException;
import java.util.Scanner;

import application.Main;
import carte.ICible;
import exception.HearthstoneException;
import joueur.IJoueur;
import plateau.Plateau;

public class InteractionPouvoirHeros extends Interaction {

	public InteractionPouvoirHeros(int choix) {
		super(choix);
	}

	@Override
	public String getMessage() {
		return super.getMessage() + "Utiliser le pouvoir du héros";
	}

	@Override
	protected void traitementSpecialise() {
		Plateau plateau = Plateau.getInstance();
		IJoueur joueurCourant = plateau.getJoueurCourant();
		IJoueur joueurAdverse = null;
		
		try {
			if ( joueurCourant.getHeros().getCapaciteUtilisee() == false ) {
				joueurCourant.getHeros().getPouvoir().executerAction(getCible());
				joueurCourant.getHeros().setCapaciteUtilisee(true);
			} else {
				System.err.println("La capacité du héros a déjà été utilisée ce tour");
			}
		} catch (HearthstoneException e) {
			System.out.println(e.getMessage());
		}
	}

}
