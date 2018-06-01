package interactions;

import carte.ICarte;
import carte.ICible;
import exception.HearthstoneException;
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
		ICarte carte = getCarteEnMain();
		ICible cible = getCible();
		System.out.println(cible);
		
		try {
			Plateau.getInstance().getJoueurCourant().jouerCarte(carte, cible);
		} catch (HearthstoneException e) {
			System.out.println(e.getMessage());
		}
	}

}
