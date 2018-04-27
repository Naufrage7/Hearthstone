package capacite;

import carte.Serviteur;
import exception.HearthstoneException;

public class MarqueDuChasseur extends Capacite {

	public MarqueDuChasseur() {
		super("Marque du chasseur", "Cette capacité vise un serviteur et réduit à 1 son nombre de points de vie ( quel que soit le nombre de points de vie qu'il avait au départ )");
	}
	
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		if ( cible == null ) 
			throw new HearthstoneException("La cible ne peut pas être nulle !");
		if ( !(cible instanceof Serviteur) )
			throw new HearthstoneException("Cette capacité ne s'utilise que sur un serviteur !");
		
		Serviteur s = (Serviteur) cible;
		s.setVie(1);
	}
	
	public String toString() {
		return super.toString() + " -> MarqueDuChasseur []";
	}

}
