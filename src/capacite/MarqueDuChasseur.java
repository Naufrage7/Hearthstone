package capacite;

import carte.Serviteur;
import exception.CibleInvalideException;
import exception.HearthstoneException;

public class MarqueDuChasseur extends Capacite {

	public MarqueDuChasseur(String nom, String description) {
		super(nom, description);
	}
	
	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		if ( cible == null )
			throw new CibleInvalideException("La cible ne peut pas être nulle !");
		
		if ( !(cible instanceof Serviteur) )
			throw new CibleInvalideException("Cette capacité ne s'utilise que sur un serviteur !");
		
		Serviteur s = (Serviteur) cible;
		s.setVie(1);
	}

}
