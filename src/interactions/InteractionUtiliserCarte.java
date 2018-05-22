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
		Scanner sc = new Scanner(System.in);
		System.out.print("Nom de la carte : ");
		String nomCarte = sc.nextLine();
		
		Plateau plateau = Plateau.getInstance();
		IJoueur joueurCourant = plateau.getJoueurCourant();
		
		ICarte carte = joueurCourant.getCarteEnJeu(nomCarte);
		if ( carte == null )
			return;
		
		try {
			joueurCourant.utiliserCarte(carte, null);
		} catch ( CibleInvalideException e ) {
			Object cible = Main.demanderCible();
			try {
				joueurCourant.utiliserCarte(carte, cible);
			} catch (HearthstoneException e2) {
				e2.printStackTrace();
			}
		} catch ( HearthstoneException e ) {
			e.printStackTrace();
		}
	}

	@Override
	public int getChoix() {
		return 3;
	}

}
