package capacite;

import carte.ICarte;
import exception.CibleInvalideException;
import exception.HearthstoneException;

public class Charge extends Capacite {

	public Charge(String nom, String description) {
		super(nom, description);
	}
	
	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		this.executerAction(cible);
	}

	@Override
	public void executerEffetDebutTour() throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executerEffetFinTour() throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		if ( cible == null )
			throw new CibleInvalideException("La cible ne peut pas être nulle !");
			
		if ( !(cible instanceof ITemporisable) ) 
			throw new CibleInvalideException("La cible doit être un serviteur !");
		
		ITemporisable temporisable = (ITemporisable) cible;
		
		if ( temporisable.peutJouer() )
			throw new CibleInvalideException("La cible peut déjà jouer ...");
		
		temporisable.setPeutJouer(true);
	}

	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean seLanceSurServiteurProprietaire() {
		return true;
	}

}
