package capacite;

import carte.ICarte;
import carte.Serviteur;
import exception.HearthstoneException;
import joueur.IJoueur;
import plateau.IPlateau;
import plateau.Plateau;

public class AttaqueTotale extends Capacite {
	private int degats;
	
	public AttaqueTotale(String nom, int degats) {
		super("Attaque totale", "Infligde " + degats + " de dégats à tous les serviteurs adverses");
		this.setDegats(degats);
	}
	
	private void setDegats(int degats) {
		this.degats = degats;
	}
	
	public void executerAction(Object cible) throws HearthstoneException {
		IPlateau plateau = Plateau.getInstance();
		IJoueur joueurCourant = plateau.getJoueurCourant();
		IJoueur joueurAdverse = plateau.getAdversaire(joueurCourant);
		for ( ICarte c : joueurAdverse.getJeu() ) {
			if ( c instanceof Serviteur ) {
				Serviteur s = (Serviteur) c;
				s.setVie(s.getVie() - this.degats);
			}
		}
	}
}
