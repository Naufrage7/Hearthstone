package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import capacite.AttaqueCiblee;
import carte.ICarte;
import carte.Serviteur;
import exception.HearthstoneException;
import heros.Heros;
import interactions.Interaction;
import interactions.InteractionChoixNonValideException;
import interactions.InteractionFinirTour;
import interactions.InteractionJouerCarte;
import interactions.InteractionPouvoirHeros;
import interactions.InteractionUtiliserCarte;
import joueur.IJoueur;
import joueur.Joueur;
import capacite.ITemporisable;
import plateau.Plateau;

public class Main {
	
	private static Interaction interactions;
	
	public static void afficherCarteMain(ICarte carte) {	
		System.out.print("## ");
		
		if ( carte.getProprietaire() == Plateau.getInstance().getJoueurCourant() ) {
			String jouable = ".......";
			
			if ( carte.getCout() <= Plateau.getInstance().getJoueurCourant().getStockMana() )
				jouable = "JOUABLE";
			
			System.out.print(jouable + " (" + carte.getCout() + ") ");
		}
		
		System.out.println(carte);
	}
	
	public static void afficherCarteJeu(ICarte carte) {
		if ( carte.getProprietaire() == Plateau.getInstance().getJoueurCourant() ) {
			if ( carte instanceof ITemporisable ) {
				String jouable = ".......";

				if ( ((ITemporisable)carte).peutJouer() )
					jouable = "JOUABLE";
				
				System.out.print(jouable + " (" + carte.getCout() + ") ");
			}
		}
		
		System.out.println(carte);
	}
	
	public static void afficherTerrain() {
		IJoueur joueurCourant = Plateau.getInstance().getJoueurCourant();
		IJoueur joueurAdverse = null;
		
		try {
			joueurAdverse = Plateau.getInstance().getAdversaire(joueurCourant);
		} catch (HearthstoneException e) {
			e.printStackTrace();
		}
		
		System.out.println("\n==================================");
		for ( ICarte carte : joueurCourant.getJeu() )
			afficherCarteJeu(carte);
		System.out.println("==================================");
		System.out.println("----------------------------------");
		System.out.println("==================================");
		for ( ICarte carte : joueurAdverse.getJeu() )
			afficherCarteJeu(carte);
		System.out.println("==================================");
	}
	
	public static void afficherHud() {
		IJoueur joueurCourant = Plateau.getInstance().getJoueurCourant();
		
		System.out.println("**************************************************");
		System.out.println(joueurCourant.getPseudo() + " joue " + joueurCourant.getHeros().getNom() + " ( " + joueurCourant.getHeros().getVie() + " points de vie restants )");
		System.out.println("Stock de mana : " + joueurCourant.getStockMana());
		System.out.println("Pouvoir du héros : " + joueurCourant.getHeros().getPouvoir().getNom() + " ( " + joueurCourant.getHeros().getPouvoir().getDescription() + " )");
	}
	
	public static void afficherMain() {
		IJoueur joueurCourant = Plateau.getInstance().getJoueurCourant();
		
		System.out.println("\n### Contenu de ma main ###");
		System.out.println("##########################");
		
		if ( joueurCourant.getMain().isEmpty() ) {
			System.out.println("## VIDE");
		} else {
			for ( ICarte carte : joueurCourant.getMain() )
				afficherCarteMain(carte);
		}
		
		System.out.println("##########################");
	}
	
	public static void afficherMenu(Interaction interactions) {
		System.out.println("\nActions disponibles :");
		
		for ( String message : interactions.getMessages() )
			System.out.println(" - " + message);
		
		System.out.print("\n");

		try {
			interactions.traiter(recupererChoix());
		} catch ( InteractionChoixNonValideException e ) {
			System.out.println(e.getMessage());
		}
	}
	
	public static int recupererChoix() {
		Scanner sc = new Scanner(System.in);
		boolean choixValide = false;
		int choix = 0;
		
		while ( !choixValide ) {
			try {
				System.out.print("Ton choix : ");
				choix = sc.nextInt();
				choixValide = true;
			} catch ( InputMismatchException e ) {
				System.out.println("Un nombre, je veux un nombre !");
				sc.nextLine();
			}
		}
		
		return choix;
	}

	public static void creerInteractions() {
		interactions = new InteractionFinirTour(1);
		Interaction interactionJouerCarte = new InteractionJouerCarte(2);
		Interaction interactionUtiliserCarte = new InteractionUtiliserCarte(3);
		Interaction interactionPouvoirHeros = new InteractionPouvoirHeros(4);
		
		interactions.setSuivant(interactionJouerCarte)
		.setSuivant(interactionUtiliserCarte)
		.setSuivant(interactionPouvoirHeros);
	}
	
	public static void main(String[] args) {
		creerInteractions();
		Plateau plateau = Plateau.getInstance();
		
		IJoueur j1 = new Joueur("Théo", new Heros("Jaina", 15, new AttaqueCiblee("Boule de feu", "Inflige 1 de dégats", 1)));
		IJoueur j2 = new Joueur("Alex", new Heros("Rexxar", 15, new AttaqueCiblee("Petite flèche", "Inflige 1 de dégats", 1)));
		
		try {
			plateau.ajouterJoueur(j1);
			plateau.ajouterJoueur(j2);
			
			plateau.demarrerPartie();
			
			for ( int i = 0; i < 10 * 2; i++ ) {
				try {
					IJoueur joueurCourant = plateau.getJoueurCourant();
					
					joueurCourant.finirTour();
					
					plateau.setJoueurCourant(plateau.getAdversaire(joueurCourant));
					plateau.getJoueurCourant().prendreTour();
				} catch ( HearthstoneException e ) {
					e.printStackTrace();
				}
			}
			
			while ( plateau.estDemarree() ) {
				afficherHud();
				afficherMain();				
				afficherTerrain();
				afficherMenu(interactions);
			}
		} catch ( HearthstoneException e ) {
			e.printStackTrace();
		}
	}

}
