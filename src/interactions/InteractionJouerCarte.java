package interactions;

import java.util.InputMismatchException;
import java.util.Scanner;

import application.Main;
import carte.ICarte;
import carte.ICible;
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
	
	private ICible getCible() {
		Scanner sc = new Scanner(System.in);
		IJoueur joueurCourant = Plateau.getInstance().getJoueurCourant();
		ICible cible = null;
		IJoueur joueurAdverse = null;
		
		try {
			joueurAdverse = Plateau.getInstance().getAdversaire(Plateau.getInstance().getJoueurCourant());
		} catch (HearthstoneException e) {
			e.printStackTrace();
		}
		
		System.out.println("Qui vises-tu ?");
		System.out.println(" - 1. Le héros adverse");
		System.out.println(" - 2. Un serviteur adverse");
		System.out.println(" - 3. Un serviteur allié");
		System.out.println(" - 4. Personne ...");

		boolean choixValide = false;
		String nomCarte = "";
		int choix = 0;
		
		while ( !choixValide ) {
			try {
				System.out.print("Ton choix : ");
				choix = sc.nextInt();
				choixValide = true;
			} catch ( InputMismatchException e ) {
				System.out.println("Un nombre, je veux un nombre !");
			}
		}
		
		sc.nextLine();
		
		Object carte = null;
		switch ( choix ) {
			case 1:
				cible = joueurAdverse.getHeros();
				break;
			case 2:
				System.out.print("Nom du serviteur : ");
				nomCarte = sc.nextLine();
				carte = joueurAdverse.getCarteEnJeu(nomCarte);
				if ( carte instanceof ICible )
					cible = (ICible) carte;
				break;
			case 3:
				System.out.print("Nom du serviteur : ");
				nomCarte = sc.nextLine();
				carte = joueurCourant.getCarteEnJeu(nomCarte);
				if ( carte instanceof ICible )
					cible = (ICible) carte;
				break;
		}
		
		return cible;
	}
	
	private ICarte getCarte() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nom de la carte : ");
		String nomCarte = sc.nextLine();
		
		if ( nomCarte.length() == 0 )
			return null;

		IJoueur joueurCourant = Plateau.getInstance().getJoueurCourant();
		
		return joueurCourant.getCarteEnMain(nomCarte);
	}
	
	@Override
	protected void traitementSpecialise() {		
		ICarte carte = getCarte();
		ICible cible = getCible();
		System.out.println(cible);
		
		try {
			Plateau.getInstance().getJoueurCourant().jouerCarte(carte, cible);
		} catch (HearthstoneException e) {
			e.printStackTrace();
		}
	}

}
