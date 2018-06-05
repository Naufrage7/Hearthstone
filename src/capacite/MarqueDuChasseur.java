package capacite;

import carte.ICarte;
import carte.Serviteur;
import exception.CibleInvalideException;
import exception.HearthstoneException;

public class MarqueDuChasseur extends Capacite {

	public MarqueDuChasseur(String nom, String description) {
		super(nom, description);
	}
	
	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		executerAction(cible);
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
		
		if ( !(cible instanceof Serviteur) )
			throw new CibleInvalideException("Cette capacité s'utilise sur un serviteur uniquement !");
		
		Serviteur serviteur = (Serviteur) cible;
		serviteur.setVie(1);
	}

	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean seLanceSurServiteurProprietaire() {
		// TODO Auto-generated method stub
		return false;
	}

}
