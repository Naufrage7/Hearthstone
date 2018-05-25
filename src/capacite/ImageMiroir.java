package capacite;
import carte.Serviteur;
import exception.HearthstoneException;
import plateau.Plateau;

public class ImageMiroir extends Capacite {
	
	public ImageMiroir(String nom, String description) {
		super(nom, description);
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
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		for ( int i = 0; i < 2; i++ ) 
			Plateau.getInstance().getJoueurCourant().jouerCarte(new Serviteur(0, 2, "Protecteur de Jaina", 0, new Provocation("Provocation", "Oblige l'adversaire a cibler ce joueur"), Plateau.getInstance().getJoueurCourant()));
	}

	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean seLanceSurServiteurProprietaire() {
		return false;
	}


}
