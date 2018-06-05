package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import capacite.AttaqueCiblee;
import carte.ICarte;
import carte.Serviteur;
import carte.Sort;
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
import plateau.Plateau;

public class Main {
	
	private static Interaction interactions;
	
	public static void afficherTerrain() {
		IJoueur joueurCourant = Plateau.getInstance().getJoueurCourant();
		IJoueur joueurAdverse = null;
		
		try {
			joueurAdverse = Plateau.getInstance().getAdversaire(joueurCourant);
		} catch (HearthstoneException e) {
			e.printStackTrace();
		}
		
		System.out.println("\nVotre plateau de jeu ( " + joueurCourant.getJeu().size() + " carte(s) )");
		for ( ICarte carte : joueurCourant.getJeu() ) {
			Serviteur serviteur = (Serviteur) carte;
			System.out.println("    - " + serviteur.getNom() + " " + serviteur.getAttaque() + "/" + serviteur.getVie() + (serviteur.getCapacite() != null ? serviteur.getCapacite().getNom() + " : " + serviteur.getCapacite().getDescription() : ""));
		}
		
		System.out.println("\nLe héros de votre adversaire a " + joueurAdverse.getHeros().getVie() + " point(s) de vie.");
		System.out.println("Plateau de jeu de l'adversaire ( " + joueurAdverse.getJeu().size() + " carte(s) )");
		for ( ICarte carte : joueurAdverse.getJeu() ) {
			Serviteur serviteur = (Serviteur) carte;
			System.out.println("    - " + serviteur.getNom() + " " + serviteur.getAttaque() + "/" + serviteur.getVie() + (serviteur.getCapacite() != null ? serviteur.getCapacite().getNom() + " : " + serviteur.getCapacite().getDescription() : ""));
		}
	}
	
	public static void afficherHud() {
		IJoueur joueurCourant = Plateau.getInstance().getJoueurCourant();
		
		System.out.println(joueurCourant.getPseudo() + ", votre héros a " + joueurCourant.getHeros().getVie() + " point(s) de vie.");
		System.out.println("Vous avez " + joueurCourant.getStockMana() + " point(s) de mana à votre disposition.");
	}
	
	public static void afficherMain() {
		IJoueur joueurCourant = Plateau.getInstance().getJoueurCourant();
		
		System.out.println("\nVotre main ( " + joueurCourant.getMain().size() + " carte(s) )");

		for ( ICarte carte : joueurCourant.getMain() ) {
			if ( carte instanceof Serviteur ) {
				Serviteur serviteur = (Serviteur) carte;
				System.out.println("  " + carte.getCout() + "  - " + serviteur.getNom() + " " + serviteur.getAttaque() + "/" + serviteur.getVie() + (serviteur.getCapacite() != null ? " ( " + serviteur.getCapacite().getNom() + " : " + serviteur.getCapacite().getDescription() + " )" : ""));
			} else {
				Sort sort = (Sort) carte;
				System.out.println("  " + carte.getCout() + "  - " + sort.getNom() + " : " + sort.getDescription());
			}
		}
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
		@SuppressWarnings("resource")
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
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Nom du joueur 1 : ");
		String nomJoueur1 = sc.nextLine();
		System.out.print("Nom du joueur 2 : ");
		String nomJoueur2 = sc.nextLine();
		
		IJoueur j1 = new Joueur(nomJoueur1, new Heros("Jaina", 15, new AttaqueCiblee("Boule de feu", "Inflige 1 point de dégat", 1)));
		IJoueur j2 = new Joueur(nomJoueur2, new Heros("Rexxar", 15, new AttaqueCiblee("Petite flèche", "Inflige 1 point de dégat", 1)));
		
		try {
			plateau.ajouterJoueur(j1);
			plateau.ajouterJoueur(j2);
			
			plateau.demarrerPartie();
			System.out.print("\033[H\033[2J");
			
			while ( plateau.estDemarree() && Plateau.getInstance().getJoueurCourant().getHeros().getVie() > 0 ) {
				afficherHud();
				afficherMain();				
				afficherTerrain();
				afficherMenu(interactions);
				System.out.print("\033[H\033[2J");
			}
			
			System.out.println(Plateau.getInstance().getAdversaire(Plateau.getInstance().getJoueurCourant()).getPseudo() + " gagne la partie !");
		} catch ( HearthstoneException e ) {
			e.printStackTrace();
		}
	}

}
