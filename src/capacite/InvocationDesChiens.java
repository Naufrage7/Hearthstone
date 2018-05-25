package capacite;

import carte.Serviteur;
import exception.HearthstoneException;
import joueur.IJoueur;
import plateau.Plateau;

public class InvocationDesChiens extends Capacite {
	
	public InvocationDesChiens(String nom, String description, IJoueur joueur) {
		super(nom, description);
	}
	
	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		int nombreServiteursAdverse = Plateau.getInstance().getAdversaire(Plateau.getInstance().getJoueurCourant()).getJeu().size();
		for ( int i = 0; i < nombreServiteursAdverse; i++ )
			Plateau.getInstance().getJoueurCourant().jouerCarte(new Serviteur(1, 1, "Chien de Rexxar", 0, new Charge("Charge", "Permet de jouer de suite"), Plateau.getInstance().getJoueurCourant()));
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
