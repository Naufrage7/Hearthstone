package capacite;

import carte.Serviteur;
import exception.HearthstoneException;

public class Charge extends Capacite {

	/**
	 * Instancie une classe charge
	 */
	public Charge() {
		super("Charge", "Cette capacité permet à un serviteur de ne pas attendre avant d'attaquer");
	}

	/**
	 * Permet au serviteur d'attaquer s'il dispose de la capacité charge
	 */
	public void executerAction(Object cible) throws HearthstoneException {
		if ( !(cible instanceof Serviteur) ) 
			throw new HearthstoneException("La cible ne peut être qu'un serviteur !");
		
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
