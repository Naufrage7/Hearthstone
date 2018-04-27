package capacite;

import carte.Serviteur;
import exception.HearthstoneException;

public class Charge extends Capacite {

	/**
	 * Instancie une classe charge
	 * 
	 * @param nom
	 *            de la carte
	 * @param description
	 *            de la carte
	 */
	public Charge(String nom, String description) {
		super(nom, description);
	}

	/**
	 * Permet au serviteur d'attaquer s'il dispose de la capacité charge
	 */
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		Serviteur s = (Serviteur) cible;
		s.setPeutAttaquer(true);
	}

	/**
	 * Fonction toString de Charge
	 */
	public String toString() {
		return super.toString() + " -> Charge []";
	}
}
