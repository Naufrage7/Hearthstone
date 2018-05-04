package application;

import capacite.AttaqueCiblee;
import capacite.Capacite;
import carte.ICarte;
import carte.Serviteur;
import carte.Sort;
import exception.HearthstoneException;
import heros.Heros;
import joueur.Joueur;
import plateau.Plateau;

public class Main {

	public static void main(String[] args) {
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
				
				
				System.out.println("**************************************************");
				System.out.println(joueurCourant.getPseudo() + " joue " + joueurCourant.getHeros().getNom() + " ( " + joueurCourant.getHeros().getVie() + " points de vie restants )");
				System.out.println("Stock de mana : " + joueurCourant.getStockMana());
				System.out.println("Pouvoir du héros : " + joueurCourant.getHeros().getPouvoir().getNom() + " ( " + joueurCourant.getHeros().getPouvoir().getDescription() + " )");
				System.out.println("\n### Contenu de ma main ###");
				System.out.println("##########################");
				
				if ( joueurCourant.getMain().isEmpty() ) {
					System.out.println("## VIDE");
				} else {
					for ( ICarte carte : joueurCourant.getMain() ) {
						if ( carte instanceof Serviteur ) {
							Serviteur serviteur = (Serviteur) carte;
							System.out.println("## SERVITEUR " + serviteur.getNom() + " " + serviteur.getAttaque() + "/" + serviteur.getVie());
							
						} else if ( carte instanceof Sort ) {
							Sort sort = (Sort) carte;
							System.out.println("## SORT      " + sort.getNom() + " : " + sort.getCapacite().getDescription());
						}
					}
				}
				
				System.out.println("##########################");

				System.out.println("\nActions disponibles :");
				System.out.println(" - 1. Finir le tour");
				System.out.println(" - 2. Jouer une carte de ma main");
				System.out.println(" - 3. Utiliser une carte en jeu");
				System.out.println(" - 4. Utiliser le pouvoir du héros");
				
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
