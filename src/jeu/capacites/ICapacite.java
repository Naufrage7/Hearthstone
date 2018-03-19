package jeu.capacites;

public interface ICapacite {

	void executerAction(Object cible);
	void executerEffetDebutTour();
	void executerEffetDisparition();
	void executerEffetFinTour();
	void executerEffetMiseEnJeu(Object cible);
	
	String getDescription();
	String getNom();
	
}
