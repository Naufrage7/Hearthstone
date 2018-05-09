package application;

import java.util.Scanner;

import carte.ICarte;
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
			System.out.println("Qui vises-tu ?");
			System.out.println(" - 1. Le héros adverse");
			System.out.println(" - 2. Un serviteur adverse");
			choix = sc.nextInt();
			switch ( choix ) {
				case 1:
					try {
						joueurCourant.jouerCarte(carte, joueurAdverse.getHeros());
					} catch ( HearthstoneException e2 ) {
						System.out.println(e2.getMessage());
					}
					break;
				case 2:
					System.out.println("Nom du serviteur : ");
					nomCarte = sc.nextLine();
					try {
						joueurCourant.jouerCarte(carte, joueurAdverse.getCarteEnJeu(nomCarte));
					} catch ( HearthstoneException e2 ) {
						System.out.println(e2.getMessage());
					}
					break;
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
