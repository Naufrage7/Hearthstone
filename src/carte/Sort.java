package carte;

import capacite.Capacite;
import exception.HearthstoneException;
import joueur.IJoueur;

public class Sort extends Carte {
	
	/**
	 * Instancie un nouveau Sort
	 * 
	 * @param nom le nom de la carte
	 * @param cout le cout de la carte
	 * @param proprietaire le propriétaire de la carte
	 * @param capacite la capacité représentant le sort
	 */
	public Sort(String nom, int cout, Capacite capacite, IJoueur proprietaire) {
		super(nom, cout, capacite, proprietaire);
	}
	
	public String toString() {
		return this.getNom() + " ( " + this.getCapacite().getDescription() + " )";
	}
	
	@Override
	public void executerEffetDebutMiseEnJeu(Object cible) throws HearthstoneException {
		super.executerEffetDebutMiseEnJeu(cible);
		IJoueur joueurProprietaire = getProprietaire();
		joueurProprietaire.perdreCarte(this);
	}

	@Override
	public boolean disparait() {
		return true;
	}
	
}
