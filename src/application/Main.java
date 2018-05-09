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
	
	public static void afficherCarte(String prefixe, ICarte carte) {
		String jouable = ".......";
		if ( carte.getCout() <= Plateau.getInstance().getJoueurCourant().getStockMana() ) {
			jouable = "JOUABLE";
		}
		
		System.out.println(jouable + " " + carte);	
	}
	
	public static void afficherTerrain() throws HearthstoneException {
		IJoueur joueurCourant = Plateau.getInstance().getJoueurCourant();
		IJoueur joueurAdverse = Plateau.getInstance().getAdversaire(joueurCourant);
		
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
	
	public static void afficherHud() throws HearthstoneException {
		IJoueur joueurCourant = Plateau.getInstance().getJoueurCourant();
		IJoueur joueurAdverse = Plateau.getInstance().getAdversaire(joueurCourant);
		
		System.out.println("**************************************************");
		System.out.println(joueurCourant.getPseudo() + " joue " + joueurCourant.getHeros().getNom() + " ( " + joueurCourant.getHeros().getVie() + " points de vie restants )");
		System.out.println("Stock de mana : " + joueurCourant.getStockMana());
		System.out.println("Pouvoir du héros : " + joueurCourant.getHeros().getPouvoir().getNom() + " ( " + joueurCourant.getHeros().getPouvoir().getDescription() + " )");
	}
	
	public static void afficherMain() throws HearthstoneException {
		IJoueur joueurCourant = Plateau.getInstance().getJoueurCourant();
		IJoueur joueurAdverse = Plateau.getInstance().getAdversaire(joueurCourant);
		
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

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Plateau plateau = Plateau.getInstance();
		
		Joueur j1 = new Joueur("Théo", new Heros("Jaina", 15, new AttaqueCiblee("Boule de feu", 1)));
		Joueur j2 = new Joueur("Alex", new Heros("Rexxar", 15, new AttaqueCiblee("Petite flèche", 1)));
		
		try {
			plateau.ajouterJoueur(j1);
			plateau.ajouterJoueur(j2);
			
			plateau.demarrerPartie();
			
			while ( true ) {
				Joueur joueurCourant = (Joueur) plateau.getJoueurCourant();
				joueurCourant.prendreTour();

				int choix = 0;
				while ( choix != 1 ) {
					choix = 0;
					
					afficherHud();
					afficherMain();				
					afficherTerrain();
	
					System.out.println("\nActions disponibles :");
					System.out.println(" - 1. Finir le tour");
					System.out.println(" - 2. Jouer une carte de ma main");
					System.out.println(" - 3. Utiliser une carte en jeu");
					System.out.println(" - 4. Utiliser le pouvoir du héros");
					System.out.print("\nTon choix : ");
					
					try {
						choix = sc.nextInt();
					} catch ( Exception e ) {
						System.out.println("Erreur lors de la lecture de l'entrée clavier");
					}
					
					if ( choix == 2 ) {
						System.out.print("Nom de la carte : ");
						String nomCarte = "";
						try {
							sc.nextLine();
							nomCarte = sc.nextLine();
						} catch ( Exception e ) {
							System.out.println("Erreur lors de la lecture de l'entrée clavier");
						}
						ICarte carte = joueurCourant.getCarteEnMain(nomCarte);
						if ( carte != null ) {
							try {
							joueurCourant.jouerCarte(carte);
							} catch ( HearthstoneException e ) {
								System.out.println(e.getMessage());
							}
						}
					} else if ( choix == 3 ) {
						System.out.print("Nom de la carte : ");
						String nomCarte = "";
						try {
							sc.nextLine();
							nomCarte = sc.nextLine();
						} catch ( Exception e ) {
							System.out.println("Erreur lors de la lecture de l'entrée clavier");
						}
						ICarte carte = joueurCourant.getCarteEnJeu(nomCarte);
						if ( carte != null ) {
							try {
							joueurCourant.jouerCarte(carte);
							} catch ( HearthstoneException e ) {
								System.out.println(e.getMessage());
							}
						}
					}
				}
				
				joueurCourant.finirTour();

				plateau.setJoueurCourant(plateau.getAdversaire(joueurCourant));
				System.out.println("");
			}
		} catch ( HearthstoneException e ) {
			e.printStackTrace();
		}
		
		System.out.println(plateau);
	}

}
