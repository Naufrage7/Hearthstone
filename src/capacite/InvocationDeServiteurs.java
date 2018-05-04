package capacite;

import carte.Serviteur;
import exception.HearthstoneException;
import joueur.IJoueur;
import plateau.IPlateau;
import plateau.Plateau;

public class InvocationDeServiteurs extends Capacite {
	private Serviteur serviteur;
	
	public InvocationDeServiteurs(String nom, String description, Serviteur serviteur) {
		super(nom, description);
		this.setServiteur(serviteur);
	}
	
	private void setServiteur(Serviteur serviteur) {
		this.serviteur = serviteur;
	}
	
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		IPlateau plateau = Plateau.getInstance();
		IJoueur joueurCourant = plateau.getJoueurCourant();	
		joueurCourant.getJeu().add(this.serviteur);
	}
}
