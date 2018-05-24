package interactions;

import java.util.Scanner;

import application.Main;
import carte.ICarte;
import exception.CibleInvalideException;
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
		Scanner sc = new Scanner(System.in);
		System.out.print("Nom de la carte : ");
		String nomCarte = sc.nextLine();
		
		if ( nomCarte.length() == 0 )
			return;

		IJoueur joueurCourant = Plateau.getInstance().getJoueurCourant();
		
		ICarte carte = joueurCourant.getCarteEnMain(nomCarte);
		if ( carte == null )
			return;
		
		if ( carte.necessiteCible() ) {
			Object cible = Main.demanderCible();
			try {
				joueurCourant.jouerCarte(carte, cible);
			} catch (HearthstoneException e) {
				e.printStackTrace();
			}
		} else {
			try {
				joueurCourant.jouerCarte(carte);
			} catch (HearthstoneException e) {
				e.printStackTrace();
			}
		}
	}

}
