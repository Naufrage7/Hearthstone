package carte;

import capacite.Capacite;
import joueur.IJoueur;

public class Sort extends Carte {

	private Capacite capacite;
	
	/**
	 * Instancie un nouveau Sort
	 * 
	 * @param nom le nom de la carte
	 * @param cout le cout de la carte
	 * @param proprietaire le propriétaire de la carte
	 * @param capacite la capacité représentant le sort
	 */
	public Sort(String nom, int cout, IJoueur proprietaire, Capacite capacite) {
		super(nom, cout, proprietaire);
		this.setCapacite(capacite);
	}

	/**
	 * Attribue la capacité au sort
	 * 
	 * @param capacite la capacité
	 */
	private void setCapacite(Capacite capacite) {
		if ( capacite == null )
			throw new IllegalArgumentException("La capacité ne peut pas être nulle !");
		this.capacite = capacite;
	}
	
	@Override
	public Capacite getCapacite() {
		return this.capacite;
	}
	
}
