package capacite;

import carte.Serviteur;
import exception.CibleInvalideException;
import exception.HearthstoneException;

public class Charge extends Capacite {

	public Charge() {
		super("Charge", "Cette capacité permet à un serviteur de ne pas attendre avant d'attaquer");
	}
	
	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {		
		executerAction(cible);
	}
	
	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		if ( cible == null )
			throw new CibleInvalideException("La cible ne peut pas être nulle !");
			
		if ( !(cible instanceof Serviteur) ) 
			throw new CibleInvalideException("La cible ne peut être qu'un serviteur !");
		
		Serviteur s = (Serviteur) cible;
		s.setPeutAttaquer(true);
	}

}
