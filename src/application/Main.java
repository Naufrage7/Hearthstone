package application;

import capacite.Capacite;
import carte.ICarte;
import exception.HearthstoneException;
import heros.Heros;
import joueur.Joueur;
import plateau.Plateau;

public class Main {

	public static void main(String[] args) {
		Plateau plateau = Plateau.getInstance();
		
		Joueur j1 = new Joueur("Théo", new Heros("Jaina", 15, new Capacite("Ne rien faire", "Ne fait rien")));
		Joueur j2 = new Joueur("Alex", new Heros("Rexxor", 15, new Capacite("Ne rien faire", "Ne fait rien")));
		
		try {
			plateau.ajouterJoueur(j1);
			plateau.ajouterJoueur(j2);
			
			plateau.demarrerPartie();
			
			for ( int i = 0; i < 6; i++ ) {	
				Joueur joueurCourant = (Joueur) plateau.getJoueurCourant();
				System.out.println("==== " + joueurCourant.getPseudo() + " ====");
				
				joueurCourant.prendreTour();
				
				ICarte carteAJouer = joueurCourant.getMain().get(0);
				if ( carteAJouer.getCout() <= joueurCourant.getMana() )
					joueurCourant.jouerCarte(carteAJouer);
				
				System.out.println("Carte(s) en main :");
				if ( joueurCourant.getMain().isEmpty() ) {
					System.out.println(" - ( Rien )");
				} else {
					for ( ICarte c : joueurCourant.getMain() ) {
						System.out.println(" - ( " + c.getCout() + " ) " + c.getNom());
					}
				}
				
				System.out.println("Carte(s) en jeu :");
				if ( joueurCourant.getJeu().isEmpty() ) {
					System.out.println(" - ( Rien )");
				} else {
					for ( ICarte c : joueurCourant.getJeu() ) {
						System.out.println(" - " + c.getNom());
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
