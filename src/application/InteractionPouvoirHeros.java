package application;

import java.util.Scanner;

import exception.HearthstoneException;
import joueur.IJoueur;
import plateau.Plateau;

public class InteractionPouvoirHeros extends Interaction {

	@Override
	public String getMessage() {
		return super.getMessage() + "Utiliser le pouvoir du héros";
	}

	@Override
	protected boolean traitementSpecialise(int choix) {
		if ( choix != getChoix() )
			return false;
		
		Plateau plateau = Plateau.getInstance();
		IJoueur joueurCourant = plateau.getJoueurCourant();
		IJoueur joueurAdverse = null;
		try {
			joueurAdverse = plateau.getAdversaire(joueurCourant);
		} catch (HearthstoneException e) {
			e.printStackTrace();
		}
		
		System.out.println("Qui vises-tu ?");
		System.out.println(" - 1. Le héros adverse");
		System.out.println(" - 2. Un serviteur adverse");
		Scanner sc = new Scanner(System.in);
		choix = sc.nextInt();
		switch ( choix ) {
			case 1:
				try {
					joueurCourant.getHeros().getPouvoir().executerAction(joueurAdverse.getHeros());
				} catch ( HearthstoneException e2 ) {
					System.out.println(e2.getMessage());
				}
				break;
			case 2:
				System.out.println("Nom du serviteur : ");
				String nomCarte = sc.nextLine();
				try {
					joueurCourant.getHeros().getPouvoir().executerAction(joueurAdverse.getCarteEnJeu(nomCarte));
				} catch ( HearthstoneException e2 ) {
					System.out.println(e2.getMessage());
				}
				break;
		}
		
		return true;
	}

	@Override
	public int getChoix() {
		return 4;
	}

}
