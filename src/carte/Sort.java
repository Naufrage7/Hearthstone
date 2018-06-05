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
	
	@Override
	public void executerEffetDebutMiseEnJeu(Object cible) throws HearthstoneException {
		if ( super.getCapacite() != null )
			super.getCapacite().executerEffetMiseEnJeu(cible);
		
		getProprietaire().perdreCarte(this);
	}
	
	@Override
	public String toString() {
		return this.getNom() + " ( " + this.getCapacite().getDescription() + " )";
	}

	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		super.getCapacite().executerAction(cible);
	}

	@Override
	public void executerEffetDebutTour(Object cible) throws HearthstoneException {
		if ( super.getCapacite() != null )
			super.getCapacite().executerEffetDebutTour();
	}

	@Override
	public void executerEffetFinTour(Object cible) throws HearthstoneException {
		if ( super.getCapacite() != null )
			super.getCapacite().executerEffetFinTour();		
	}

	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		if ( super.getCapacite() != null )
			super.getCapacite().executerEffetDisparition(cible);
	}

	@Override
	public boolean disparait() {
		return true;
	}

	public String getDescription() {
		return super.capacite.getDescription();
	}
	
}
