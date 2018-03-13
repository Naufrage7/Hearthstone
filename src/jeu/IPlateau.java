package jeu;

public interface IPlateau {

	void ajouterJoueur(IJoueur joueur);
	void demarrerPartie();
	void finTour(IJoueur joueur);
	void gagnePartie(IJoueur joueur);
	void setJoueurCourant(IJoueur joueur);
	
	IJoueur getAdversaire(IJoueur joueur);
	IJoueur getJoueurCourant();
	
	boolean estDemarree();
	
}
