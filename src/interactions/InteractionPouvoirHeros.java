package interactions;

import java.util.Scanner;

import application.Main;
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
	}

}
