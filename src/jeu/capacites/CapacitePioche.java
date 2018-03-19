package jeu.capacites;

import jeu.IJoueur;

public class CapacitePioche implements ICapacite {
	
	private IJoueur joueur;
	
	public CapacitePioche(IJoueur joueur) {
		this.setJoueur(joueur);
	}
	
	private void setJoueur(IJoueur joueur) {
		if ( joueur != null ) {
			this.joueur = joueur;
		} else {
			// Exception
		}
	}
	
	@Override
	public void executerAction(Object cible) {
		this.joueur.piocher();
	}

	@Override
	public void executerEffetDebutTour() {
		// Rien à faire
	}

	@Override
	public void executerEffetDisparition() {
		// Rien à faire
	}

	@Override
	public void executerEffetFinTour() {
		// Rien à faire
	}

	@Override
	public void executerEffetMiseEnJeu(Object cible) {
		this.executerAction(cible);
	}

	@Override
	public String getDescription() {
		return "Permet de piocher une ou plusieurs cartes";
	}

	@Override
	public String getNom() {
		return "Pioche";
	}

}
