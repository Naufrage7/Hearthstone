package interactions;

import carte.ICarte;
import carte.ICible;
import exception.HearthstoneException;
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
		ICarte carte = getCarteEnJeu();
		if ( carte == null )
			System.err.println("La carte choisie n'existe pas !");
		ICible cible = getCible();
		
		System.out.println(cible);
		System.out.println(carte);
		
		try {
			Plateau.getInstance().getJoueurCourant().utiliserCarte(carte, cible);
		} catch (HearthstoneException e) {
			System.err.println(e.getMessage());
		}
	}

	@Override
	public int getChoix() {
		return 3;
	}

}
