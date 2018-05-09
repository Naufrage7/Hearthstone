package application;

import java.util.Scanner;

import capacite.AttaqueCiblee;
import carte.ICarte;
import carte.Serviteur;
import carte.Sort;
import exception.HearthstoneException;
import heros.Heros;
import joueur.IJoueur;
import joueur.Joueur;
import plateau.Plateau;

public class Main {
	
	public static final int FINIR_TOUR = 1;
	public static final int JOUER_CARTE = 2;
	public static final int UTILISER_CARTE = 3;
	public static final int UTILISER_POUVOIR_HEROS = 4;
	
	public static void afficherCarte(String prefixe, ICarte carte) {
		String jouable = ".......";
		if ( carte.getCout() <= Plateau.getInstance().getJoueurCourant().getStockMana() ) {
			jouable = "JOUABLE";
		}
		
		System.out.println(jouable + " " + carte);	
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
			afficherCarte("", carte);
		System.out.println("==================================");
		System.out.println("----------------------------------");
		System.out.println("==================================");
		for ( ICarte carte : joueurAdverse.getJeu() )
			afficherCarte("", carte);
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
				afficherCarte("## ", carte);
		}
		
		System.out.println("##########################");
	}
	
	public static void afficherMenu(Interaction interactions) {
		System.out.println("\nActions disponibles :");
		for ( String message : interactions.getMessages() )
			System.out.println(" - " + message);
		System.out.print("\nTon choix : ");
	}
	
	public static int recupererChoix() {
		Scanner sc = new Scanner(System.in);
		int choix = sc.nextInt();
		return choix;
	}

	public static Interaction creerInteractions() {
		Interaction interactionFinirTour = new InteractionFinirTour();
		Interaction interactionJouerCarte =new InteractionJouerCarte();
		Interaction interactionUtiliserCarte = new InteractionUtiliserCarte();
		Interaction interactionPouvoirHeros = new InteractionPouvoirHeros();
		
		interactionFinirTour.setSuivant(interactionJouerCarte);
		interactionJouerCarte.setSuivant(interactionUtiliserCarte);
		interactionUtiliserCarte.setSuivant(interactionPouvoirHeros);
		
		return interactionFinirTour;
	}
	
	public static void main(String[] args) {
		Interaction interactions = creerInteractions();
		Plateau plateau = Plateau.getInstance();
		
		Joueur j1 = new Joueur("Théo", new Heros("Jaina", 15, new AttaqueCiblee("Boule de feu", 1)));
		Joueur j2 = new Joueur("Alex", new Heros("Rexxar", 15, new AttaqueCiblee("Petite flèche", 1)));
		
		try {
			plateau.ajouterJoueur(j1);
			plateau.ajouterJoueur(j2);
			
			plateau.demarrerPartie();
		} catch ( HearthstoneException e ) {
			e.printStackTrace();
		}
			
		while ( plateau.estDemarree() ) {
			afficherHud();
			afficherMain();				
			afficherTerrain();
			afficherMenu(interactions);
			
			int choix = recupererChoix();
			interactions.traiter(choix);
		}
	}

}
