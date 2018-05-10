package capacite;

import java.util.ArrayList;

import carte.ICarte;
import carte.Serviteur;
import exception.HearthstoneException;
import joueur.IJoueur;
import plateau.IPlateau;
import plateau.Plateau;

public class EffetPermanent extends Capacite {
	private ArrayList<Serviteur> serviteursAffectes;
	
	public EffetPermanent(String nom, int attaque, int defense) {
		super(nom, "Effet permanent sur les autres serviteurs alliés donnant un bonus " + attaque + "/" + defense);
		this.setServiteursAffectes(new ArrayList<Serviteur>());
	}
	
	private void setServiteursAffectes(ArrayList<Serviteur> serviteursAffectes) {
		this.serviteursAffectes = serviteursAffectes;
	}
	
	public void executerEffetDebutTour(Object cible) throws HearthstoneException {
		executerAction(null);
	}
	
	public void executerEffetFinTour() throws HearthstoneException {
		executerAction(null);
	}

	public void executerAction(Object cible) throws HearthstoneException {
		IPlateau plateau = Plateau.getInstance();
		IJoueur joueurCourant = plateau.getJoueurCourant();
		
		for ( ICarte c : joueurCourant.getJeu() ) {
			if ( c instanceof Serviteur ) {
				Serviteur s = (Serviteur) c;
				if ( s.getCapacite() != this && !this.serviteursAffectes.contains(s) ) {
					s.setAttaque(s.getAttaque() + 1);
					s.setVie(s.getVie() + 1);
					this.serviteursAffectes.add(s);
				}
			}
		}
	}
	
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		executerAction(null);
	}
	
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		IPlateau plateau = Plateau.getInstance();
		IJoueur joueurCourant = plateau.getJoueurCourant();
		
		for ( ICarte c : joueurCourant.getJeu() ) {
			if ( c instanceof Serviteur ) {
				Serviteur s = (Serviteur) c;
				if ( this.serviteursAffectes.contains(s) ) {
					s.setAttaque(s.getAttaque() - 1);
					s.setVie(s.getVie() - 1);
				}
			}
		}
	}
}
