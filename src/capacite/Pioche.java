package capacite;

import carte.ICarte;
import exception.HearthstoneException;
import joueur.IJoueur;
import plateau.IPlateau;
import plateau.Plateau;

public class Pioche extends Capacite {
	
	private int quantite;
	
	public Pioche(String nom, String description, int quantite) {
		super(nom, description);
		this.quantite = quantite;
	}
	
	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		IPlateau plateau = Plateau.getInstance();
		IJoueur joueurCourant = plateau.getJoueurCourant();
		
		for ( int i = 0; i < this.quantite; i++ )
			joueurCourant.piocher();
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
