package capacite;

import exception.CibleInvalideException;
import exception.HearthstoneException;

public class Charge extends Capacite {

	public Charge(String nom, String description) {
		super(nom, description);
	}
	
	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		if ( cible == null )
			throw new CibleInvalideException("La cible ne peut pas être nulle !");
			
		if ( !(cible instanceof ITemporisable) ) 
			throw new CibleInvalideException("La cible doit être un serviteur !");
		
		ITemporisable temporisable = (ITemporisable) cible;
		temporisable.setPeutJouer(true);
	}

	@Override
	public boolean necessiteCible() {
		return true;
	}

}
