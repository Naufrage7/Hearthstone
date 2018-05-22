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
		
		Plateau plateau = Plateau.getInstance();
		IJoueur joueurCourant = plateau.getJoueurCourant();
		
		ICarte carte = joueurCourant.getCarteEnMain(nomCarte);
		if ( carte == null )
			return;
		
		try {
			joueurCourant.jouerCarte(carte);
		} catch ( CibleInvalideException e ) {
			Object cible = Main.demanderCible();
			try {
				joueurCourant.jouerCarte(carte, cible);
			} catch (HearthstoneException e2) {
				e2.printStackTrace();
			}
		} catch ( HearthstoneException e ) {
			e.printStackTrace();
		}
	}

}
