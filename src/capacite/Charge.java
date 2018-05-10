package capacite;

import carte.ICarte;
import carte.Serviteur;
import exception.CibleInvalideException;
import exception.HearthstoneException;
import plateau.Plateau;

public class Charge extends Capacite {

	public Charge() {
		super("Charge", "Cette capacité permet à un serviteur de ne pas attendre avant d'attaquer");
	}
	
	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		for ( ICarte carte : Plateau.getInstance().getJoueurCourant().getMain() ) {
			if ( carte instanceof Serviteur ) {
				Serviteur serviteur = (Serviteur) carte;
				if ( serviteur.getCapacite() == this )
					executerAction(serviteur);
				return;
			}
		}
		executerAction(cible);
	}
	
	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		if ( !(cible instanceof Serviteur) ) 
			throw new CibleInvalideException("La cible ne peut être qu'un serviteur !");
		
		Serviteur s = (Serviteur) cible;
		s.setPeutAttaquer(true);
	}

	public String toString() {
		return super.toString() + " -> Charge []";
	}
}
