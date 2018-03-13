package jeu;

public interface ICarte {
	
	void executerAction(Object cible);
	void executerEffetDebutMiseEnJeu(Object cible);
	void executerEffetDebutTour(Object cible);
	void executerEffetDisparition(Object cible);
	void executerEffetFinTour(Object cible);
	
	int getCout();
	
	boolean disparait();
	
	String getNom();
	
	IJoueur getProprietaire();

}
