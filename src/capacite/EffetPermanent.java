package capacite;

import carte.ICarte;
import carte.Serviteur;
import exception.HearthstoneException;
import joueur.IJoueur;
import plateau.IPlateau;
import plateau.Plateau;

public class EffetPermanent extends Capacite {
	public EffetPermanent(String nom, String description) {
		super(nom, description);
	}
	
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		IPlateau plateau = Plateau.getInstance();
		IJoueur joueurCourant = plateau.getJoueurCourant();
		
		for ( ICarte c : joueurCourant.getJeu() ) {
			if ( c instanceof Serviteur ) {
				Serviteur s = (Serviteur) c;
				s.setAttaque(s.getAttaque() + 1);
				s.setVie(s.getVie() + 1);
			}
		}
	}
	
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		IPlateau plateau = Plateau.getInstance();
		IJoueur joueurCourant = plateau.getJoueurCourant();
		
		for ( ICarte c : joueurCourant.getJeu() ) {
			if ( c instanceof Serviteur ) {
				Serviteur s = (Serviteur) c;
				s.setAttaque(s.getAttaque() - 1);
				s.setVie(s.getVie() - 1);
			}
		}
	}
}
