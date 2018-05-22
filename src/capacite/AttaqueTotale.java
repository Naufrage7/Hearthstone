package capacite;

import carte.ICarte;
import carte.Serviteur;
import exception.HearthstoneException;
import joueur.IJoueur;
import plateau.IPlateau;
import plateau.Plateau;

public class AttaqueTotale extends AttaqueCiblee {
	
	public AttaqueTotale(String nom, int degats) {
		super("Infligde " + degats + " de dégats à tous les serviteurs adverses", degats);
	}
	
	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		IPlateau plateau = Plateau.getInstance();
		IJoueur joueurCourant = plateau.getJoueurCourant();
		IJoueur joueurAdverse = plateau.getAdversaire(joueurCourant);
		
		for ( ICarte carte : joueurAdverse.getJeu() ) {
			if ( carte instanceof Serviteur ) {
				Serviteur serviteur = (Serviteur) carte;
				serviteur.setVie(serviteur.getVie() - getDegats());
			}
		}
	}

}
