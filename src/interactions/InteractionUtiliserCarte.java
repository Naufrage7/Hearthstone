package interactions;

import java.util.Scanner;

import application.Main;
import carte.ICarte;
import exception.CibleInvalideException;
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

	}

	@Override
	public int getChoix() {
		return 3;
	}

}
