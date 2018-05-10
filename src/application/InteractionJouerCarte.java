package application;

import java.util.Scanner;

import carte.ICarte;
import cible.ICible;
import exception.CibleInvalideException;
import exception.HearthstoneException;
import joueur.IJoueur;
import plateau.Plateau;

public class InteractionJouerCarte extends Interaction {

	@Override
	public String getMessage() {
		return super.getMessage() + "Jouer une carte de ma main";
	}
	
	@Override
	protected boolean traitementSpecialise(int choix) {
		if ( choix != getChoix() )
			return false;
		
		System.out.println("JOUER CARTE MAIN");
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Nom de la carte : ");
		String nomCarte = sc.nextLine();
		
		Plateau plateau = Plateau.getInstance();
		IJoueur joueurCourant = plateau.getJoueurCourant();
		IJoueur joueurAdverse = null;
		try {
			joueurAdverse = plateau.getAdversaire(joueurCourant);
		} catch (HearthstoneException e) {
			e.printStackTrace();
		}
		
		ICarte carte = joueurCourant.getCarteEnMain(nomCarte);
		if ( carte == null )
			return true;
		
		try {
			joueurCourant.jouerCarte(carte);
		} catch ( CibleInvalideException e ) {
			ICible cible = Main.demanderCible();
			try {
				joueurCourant.jouerCarte(carte, cible);
			} catch (HearthstoneException e2) {
				e2.printStackTrace();
			}
		} catch ( HearthstoneException e ) {
			e.printStackTrace();
		}
	
		
		return true;
	}
	
	@Override
	public int getChoix() {
		return 2;
	}

}
