package carte;

import capacite.Capacite;
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
	public Sort(String nom, int cout, IJoueur proprietaire, Capacite capacite) {
		super(nom, cout, capacite, proprietaire);
	}
	
}
