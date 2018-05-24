package capacite;

import carte.Serviteur;
import exception.CibleInvalideException;
import exception.HearthstoneException;

public class MarqueDuChasseur extends Capacite {

	public MarqueDuChasseur(String nom, String description) {
		super(nom, description);
	}
	
	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		if ( cible == null )
			throw new CibleInvalideException("La cible ne peut pas être nulle !");
		
		if ( !(cible instanceof Serviteur) )
			throw new CibleInvalideException("Cette capacité s'utilise sur un serviteur uniquement !");
		
		Serviteur serviteur = (Serviteur) cible;
		serviteur.setVie(1);
	}

	@Override
	public boolean necessiteCible() {
		return true;
	}

}
