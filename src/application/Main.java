package application;

import capacite.Capacite;
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
		} catch ( HearthstoneException e ) {
			e.printStackTrace();
		}
		
		System.out.println(plateau);
	}

}
