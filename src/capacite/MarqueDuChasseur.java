package capacite;

import carte.Serviteur;
import exception.HearthstoneException;

public class MarqueDuChasseur extends Capacite {

	public MarqueDuChasseur(String nom) {
		super(nom, "Réduit la vie d'un serviteur à 1");
	}
	
	public void executerAction(Object cible) throws HearthstoneException {
		if ( !(cible instanceof Serviteur) )
			throw new HearthstoneException("Cette capacité ne s'utilise que sur un serviteur !");
		
		Serviteur s = (Serviteur) cible;
		s.setVie(1);
	}
	
	public String toString() {
		return super.toString() + " -> MarqueDuChasseur []";
	}

}
