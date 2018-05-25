package capacite;

import carte.Serviteur;
import exception.HearthstoneException;
import plateau.Plateau;

public class InvocationDeServiteurs extends Capacite {
	
	private Serviteur serviteur;
	
	public InvocationDeServiteurs(String nom, String description, Serviteur serviteur) {
		super(nom, description);
		this.serviteur = serviteur;
	}
	
	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		Plateau.getInstance().getJoueurCourant().jouerCarte(serviteur, cible);
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
